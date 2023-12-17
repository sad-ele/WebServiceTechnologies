before docker build
```bash
sudo rm -rf ~/.docker/config.json
```
After run commands
```bash
sudo docker build -t pgimage .
sudo docker run -d --name pgcontainer -p 5432:5432 pgimage
```
