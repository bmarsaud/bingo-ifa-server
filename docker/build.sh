#!/bin/bash -e

# ========================
#      CONFIGURATION
# ========================
# Definition
CONF_DOCKERFILE=Dockerfile
CONF_REGISTRY=registry.mouseover.fr:5000
CONF_IMAGE="bm/bingo-ifa/server"
CONF_IMAGE_TAG=latest
CONF_HOST_SCHEME=http
CONF_HOST_NAME=0.0.0.0
CONF_HOST_PORT=80
CONF_HOST_BASE_URI=
CONF_DB_HOST=bingo-data
CONF_DB_PORT=3306
CONF_DB_USER=bingo-ifa
CONF_DB_PASSWORD=bingo-ifa
CONF_DB_NAME=bingo-ifa

# Environment resolve
[[ -z $DOCKERFILE ]] && DOCKERFILE=$CONF_DOCKERFILE
[[ -z $REGISTRY ]] && REGISTRY=$CONF_REGISTRY
[[ -z $IMAGE ]] && IMAGE=$CONF_IMAGE
[[ -z $IMAGE_TAG ]] && IMAGE_TAG=$CONF_IMAGE_TAG
[[ -z $HOST_SCHEME ]] && HOST_SCHEME=$CONF_HOST_SCHEME
[[ -z $HOST_NAME ]] && HOST_NAME=$CONF_HOST_NAME
[[ -z $HOST_PORT ]] && HOST_PORT=$CONF_HOST_PORT
[[ -z $HOST_BASE_URI ]] && HOST_BASE_URI=$CONF_HOST_BASE_URI
[[ -z $DB_HOST ]] && DB_HOST=$CONF_DB_HOST
[[ -z $DB_PORT ]] && DB_PORT=$CONF_DB_PORT
[[ -z $DB_USER ]] && DB_USER=$CONF_DB_USER
[[ -z $DB_PASSWORD ]] && DB_PASSWORD=$CONF_DB_PASSWORD
[[ -z $DB_NAME ]] && DB_NAME=$CONF_DB_NAME

# Environment logging
echo "=== ENVIRONMENT ==="
echo "-> Docker"
echo "IMAGE=$IMAGE"
echo "IMAGE_TAG=$IMAGE_TAG"
echo "REGISTRY=$REGISTRY"
echo "-> Host"
echo "HOST_SCHEME=$HOST_SCHEME"
echo "HOST_NAME=$HOST_NAME"
echo "HOST_PORT=$HOST_PORT"
echo "HOST_BASE_URI=$HOST_BASE_URI"
echo "-> Database"
echo "DB_HOST=$DB_HOST"
echo "DB_PORT=$DB_PORT"
echo "DB_USER=$DB_USER"
echo "DB_NAME=$DB_NAME"
echo "==================="

# ========================
#    Application tasks
# ========================
echo "===> Package application"
mvn -f ../ -Dmaven.test.skip package
cp ../target/bingo-ifa-server.jar bingo-ifa-server.jar
echo "Done."

echo "===> Generate configuration"
mkdir -p config

cat <<EOT >> config/host.properties
host.scheme=$HOST_SCHEME
host.name=$HOST_NAME
host.port:$HOST_PORT
host.baseUri=$HOST_BASE_URI
EOT

cat <<EOT >> config/database.properties
dataSourceClassName=org.mariadb.jdbc.MariaDbDataSource
dataSource.user=$DB_USER
dataSource.password=$DB_PASSWORD
dataSource.databaseName=$DB_NAME
dataSource.portNumber=$DB_PORT
dataSource.serverName=$DB_HOST
EOT
echo "Done."

# ========================
#    Docker tasks
# ========================
echo "===> Building image"
docker build --no-cache -t $IMAGE -f $DOCKERFILE --build-arg HOST_PORT=$HOST_PORT .
echo "Done."

echo "===> Exporting image to registry"
docker tag $IMAGE $REGISTRY/$IMAGE:$IMAGE_TAG
docker push $REGISTRY/$IMAGE:$IMAGE_TAG
docker rmi $REGISTRY/$IMAGE:$IMAGE_TAG || true
docker rmi $IMAGE || true
echo "Done."

echo "===> Cleaning"
rm -r config
rm bingo-ifa-server.jar
echo "Done."
