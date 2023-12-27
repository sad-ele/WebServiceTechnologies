package org.example;

import org.apache.juddi.api_v3.AccessPointType;
import org.apache.juddi.v3.client.UDDIConstants;
import org.apache.juddi.v3.client.config.UDDIClient;
import org.apache.juddi.v3.client.transport.Transport;
import org.uddi.api_v3.*;
import org.uddi.v3_service.UDDIInquiryPortType;
import org.uddi.v3_service.UDDIPublicationPortType;
import org.uddi.v3_service.UDDISecurityPortType;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.juddi.v3.client.transport.TransportException;
import org.example.client.Client;


public class
Juddi {
    private static UDDISecurityPortType security = null;
    private static UDDIInquiryPortType inquiry = null;
    private static UDDIPublicationPortType publish = null;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, input your jUDDI admin username (default: uddiadmin)?");
        String userName = scanner.nextLine();
        if (userName.trim().isEmpty()) {
            userName = "uddiadmin";
        }
        System.out.println("Please, input your jUDDI admin password (default: da_password1)?");
        String userPass = scanner.nextLine();
        if (userPass.trim().isEmpty()) {
            userPass = "da_password1";
        }

        Juddi app = new Juddi();

        String token = app.getUDDIToken(userName, userPass);

        System.out.println("Do you want to register a new Business/Service? (y -> yes, other -> no)");
        String agree = scanner.nextLine();
        if (agree.equals("y")) {

            System.out.println("What jUDDI Business Name will we use (default: Songs Business)?");
            String businessName = scanner.nextLine();
            if (businessName.trim().isEmpty()) {
                businessName = "Songs Business";
            }

            System.out.println("What jUDDI Service Name will we use (default: PlaylistService)?");
            String registeredServiceName = scanner.nextLine();
            if (registeredServiceName.trim().isEmpty()) {
                registeredServiceName = "PlaylistService";
            }

            System.out.println("What jUDDI Service Access Point (default: http://localhost:8090/PlaylistService?wsdl)?");
            String registeredServiceURL = scanner.nextLine();
            if (registeredServiceURL.trim().isEmpty()) {
                registeredServiceURL = "http://localhost:8090/PlaylistService?wsdl";
            }
            app.registerNewService(token, businessName, registeredServiceName, registeredServiceURL);
        }

        System.out.println("Do you want to search and request some Service? (y -> yes, other -> no)");
        agree = scanner.nextLine();
        if (agree.equals("y")) {

            System.out.println("What jUDDI Service Name will we search (default: PlaylistService)?");
            String searchServiceName = scanner.nextLine();
            if (searchServiceName.trim().isEmpty()) {
                searchServiceName = "PlaylistService";
            }

            String accessPoint;
            try {
                accessPoint = app.searchService(app.GetBusinessList(inquiry, token).getBusinessInfos(), inquiry, token, searchServiceName);
                System.out.println("Do you want to request this service now? (y -> yes, other -> no)");
                agree = scanner.nextLine();
                if (agree.equals("y")) {
                    Client serviceClient = new Client();
                    serviceClient.serviceRequest(accessPoint);
                }
            } catch (Exception ex) {
                Logger.getLogger(Juddi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            security.discardAuthToken(new DiscardAuthToken(token));
        } catch (RemoteException ex) {
            Logger.getLogger(Juddi.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            scanner.close();
        }

    }


    public Juddi() {
        try {
            UDDIClient client = new UDDIClient("META-INF/service_search.xml");
            Transport transport = client.getTransport("default");

            security = transport.getUDDISecurityService();
            inquiry = transport.getUDDIInquiryService();
            publish = transport.getUDDIPublishService();

        } catch (ConfigurationException | TransportException e) {
        }
    }

    private String getUDDIToken(String jUDDIUserName, String jUDDIUSerPass) {
        String token = null;
        GetAuthToken getAuthToken = new GetAuthToken();
        getAuthToken.setUserID(jUDDIUserName);
        getAuthToken.setCred(jUDDIUSerPass);
        try {
            AuthToken authToken = security.getAuthToken(getAuthToken);
            token =  authToken.getAuthInfo();
        } catch (RemoteException ex) {
            Logger.getLogger(Juddi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return token;
    }

    private void registerNewService(
            String token,
            String businessName,
            String registeredServiceName,
            String registeredServiceURL) {

        BusinessEntity myBusEntity = new BusinessEntity();
        Name myBusName = new Name();
        myBusName.setValue(businessName);
        myBusEntity.getName().add(myBusName);

        SaveBusiness sb = new SaveBusiness();
        sb.getBusinessEntity().add(myBusEntity);
        sb.setAuthInfo(token);

        try {
            BusinessDetail bd = publish.saveBusiness(sb);
            String myBusKey = bd.getBusinessEntity().get(0).getBusinessKey();
//            System.out.println("myBusiness key:  " + myBusKey);

            BusinessService myService = new BusinessService();
            myService.setBusinessKey(myBusKey);
            Name myServName = new Name();
            myServName.setValue(registeredServiceName);
            myService.getName().add(myServName);

            BindingTemplate myBindingTemplate = new BindingTemplate();
            AccessPoint accessPoint = new AccessPoint();
            accessPoint.setUseType(AccessPointType.WSDL_DEPLOYMENT.toString());
            accessPoint.setValue(registeredServiceURL);
            myBindingTemplate.setAccessPoint(accessPoint);
            BindingTemplates myBindingTemplates = new BindingTemplates();

            myBindingTemplate = UDDIClient.addSOAPtModels(myBindingTemplate);
            myBindingTemplates.getBindingTemplate().add(myBindingTemplate);

            myService.setBindingTemplates(myBindingTemplates);

            SaveService ss = new SaveService();
            ss.getBusinessService().add(myService);
            ss.setAuthInfo(token);
            ServiceDetail sd = publish.saveService(ss);
            String myServKey = sd.getBusinessService().get(0).getServiceKey();
            System.out.println("myService key:  " + myServKey);

            System.out.println("New service successfully registered!");

        } catch (RemoteException ex) {
            Logger.getLogger(Juddi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private BusinessList GetBusinessList(
            UDDIInquiryPortType inquiry,
            String token)
            throws Exception {
        FindBusiness fb = new FindBusiness();
        fb.setAuthInfo(token);
        org.uddi.api_v3.FindQualifiers fq = new org.uddi.api_v3.FindQualifiers();
        fq.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
        fb.setFindQualifiers(fq);
        Name searchName = new Name();
        searchName.setValue(UDDIConstants.WILDCARD);
        fb.getName().add(searchName);

        return inquiry.findBusiness(fb);
    }


    private String searchService(
            BusinessInfos businessInfos,
            UDDIInquiryPortType inquiry,
            String token,
            String serviceName)
            throws Exception {

        for (int i = 0; i < businessInfos.getBusinessInfo().size(); i++) {
            GetServiceDetail gsd = new GetServiceDetail();
            try {
                for (int k = 0; k < businessInfos.getBusinessInfo().get(i).getServiceInfos().getServiceInfo().size(); k++) {
                    gsd.getServiceKey().add(businessInfos.getBusinessInfo().get(i).getServiceInfos().getServiceInfo().get(k).getServiceKey());
                }
                gsd.setAuthInfo(token);
                ServiceDetail serviceDetail = inquiry.getServiceDetail(gsd);
                for (int k = 0; k < serviceDetail.getBusinessService().size(); k++) {
                    BusinessService get = serviceDetail.getBusinessService().get(k);

                    if (ListToString(get.getName()).equals(serviceName)) {
                        System.out.println("Fetching Service Access Point for Business " + businessInfos.getBusinessInfo().get(i).getBusinessKey());
                        System.out.println("We find this service in jUDDI register!");
                        return  getServiceAccessPoint(get.getBindingTemplates());
                    }
                }
            } catch (NullPointerException ex) {
                System.out.println("That's it! We get a " + ex);
                return null;
            }
        }
        return null;
    }

    private String getServiceAccessPoint(BindingTemplates bindingTemplates) {
        if (bindingTemplates == null) {
            return null;
        }
        String serviceAccessPoint = null;
        for (int i = 0; i < bindingTemplates.getBindingTemplate().size(); i++) {
            if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint() != null) {
                if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType() != null) {
                    if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType().equalsIgnoreCase(AccessPointType.WSDL_DEPLOYMENT.toString())) {
                        serviceAccessPoint = bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getValue();
                    }
                }
            }
        }
        return serviceAccessPoint;
    }

    private String ListToString(List<Name> name) {
        StringBuilder sb = new StringBuilder();
        for (Name value : name) {
            sb.append(value.getValue());
        }
        return sb.toString();
    }

}