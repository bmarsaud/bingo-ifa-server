#!/bin/bash -e

# ========================
#      CONFIGURATION
# ========================
# Definition
CONF_HOST_PORT=80
CONF_DB_HOST=data
CONF_DB_PORT=3306
CONF_DB_USER=bingo-ifa
CONF_DB_PASSWORD=bingo-ifa
CONF_DB_NAME=bingo-ifa

# Environment resolve
[[ -z $HOST_PORT ]] && export HOST_PORT=$CONF_HOST_PORT
[[ -z $DB_HOST ]] && export DB_HOST=$CONF_DB_HOST
[[ -z $DB_PORT ]] && export DB_PORT=$CONF_DB_PORT
[[ -z $DB_USER ]] && export DB_USER=$CONF_DB_USER
[[ -z $DB_PASSWORD ]] && export DB_PASSWORD=$CONF_DB_PASSWORD
[[ -z $DB_NAME ]] && export DB_NAME=$CONF_DB_NAME

docker-compose -p bingoifaserver up -d