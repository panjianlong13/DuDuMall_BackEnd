docker build -t dudumall-backend-merchandise .
docker run -d --name dudumall-backend-merchandise -p 8081:8081 dudumall-backend-merchandise