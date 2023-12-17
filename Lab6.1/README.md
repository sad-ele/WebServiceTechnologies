before docker build
```bash
sudo rm -rf ~/.docker/config.json
```
After run commands
```bash
sudo docker build -t pgimage .
sudo docker run -d --name pgcontainer -p 5432:5432 pgimage
```

To generate classes for client
```bash
export JAVA_HOME=/Users/sadelja/Library/Java/JavaVirtualMachines/corretto-1.8.0_382/Contents/Home

$JAVA_HOME/bin/wsimport -s src/main/java/ -keep -p com.foreach.soap.ws.client.generated "http://localhost:8080/PlaylistService?wsdl"
```