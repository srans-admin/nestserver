##--no-cache 
git checkout -f $1
git pull 
docker build -t nest-server .
docker stop nest-server-container
docker rm nest-server-container
docker run -p 8080:8080 -d -it  --name nest-server-container nest-server