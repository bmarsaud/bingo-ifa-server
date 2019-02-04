#!/bin/bash

# === CONFIGURATION ===
CONF_CONTAINER_NAME=bingo-ifa-server_test-data
CONF_IMAGE=registry.mouseover.fr:5000/bm/bingo-ifa/test-data:latest
CONF_DB_HOST=127.0.0.1
CONF_DB_PORT=3308
CONF_DB_USER=bingo-ifa
CONF_DB_PASSWORD=bingo-ifa
CONF_DB_NAME=bingo-ifa

# === ENVIRONMENT RESOLVE ===
[[ -z $CONTAINER_NAME ]] && CONTAINER_NAME=$CONF_CONTAINER_NAME
[[ -z $IMAGE ]] && IMAGE=$CONF_IMAGE
[[ -z $DB_HOST ]] && DB_HOST=$CONF_DB_HOST
[[ -z $DB_PORT ]] && DB_PORT=$CONF_DB_PORT
[[ -z $DB_USER ]] && DB_USER=$CONF_DB_USER
[[ -z $DB_PASSWORD ]] && DB_PASSWORD=$CONF_DB_PASSWORD
[[ -z $DB_NAME ]] && DB_NAME=$CONF_DB_NAME

# === ENVIRONMENT DISPLAY ===
echo "=== ENVIRONMENT ==="
echo "CONTAINER_NAME=$CONTAINER_NAME"
echo "IMAGE=$IMAGE"
echo "DB_HOST=$DB_HOST"
echo "DB_USER=$DB_USER"
echo "DB_PASSWORD=$DB_PASSWORD"
echo "DB_NAME=$DB_NAME"
echo "==================="

echo "=> Restarting container..."
docker stop $CONTAINER_NAME | true
docker rm $CONTAINER_NAME | true
docker run -d --name $CONTAINER_NAME \
        -p $DB_PORT:3306 \
        -e MYSQL_DATABASE=$DB_NAME \
        -e MYSQL_USER=$DB_USER \
        -e MYSQL_PASSWORD=$DB_PASSWORD \
        -e MYSQL_RANDOM_ROOT_PASSWORD=yes \
        $IMAGE
echo "Done."

echo "=> Waiting database..."
until mysql --host=$DB_HOST --port=$DB_PORT --user=$DB_USER --password=$DB_PASSWORD --execute "" $DB_DATABASE 2> /dev/null; do
    sleep 1
done
echo "Done."

echo "Tests database context ready !"