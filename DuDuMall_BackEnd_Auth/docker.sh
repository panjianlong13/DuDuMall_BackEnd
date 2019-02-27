docker build -t dudumall-backend-auth .
docker run -d --name dudumall-backend-auth -p 8090:8090 dudumall-backend-auth