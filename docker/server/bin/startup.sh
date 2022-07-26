#!/bin/sh
#
#  Copyright 2021 Netflix, Inc.
#  <p>
#  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
#  the License. You may obtain a copy of the License at
#  <p>
#  http://www.apache.org/licenses/LICENSE-2.0
#  <p>
#  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
#  an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
#  specific language governing permissions and limitations under the License.
#

# startup.sh - startup script for the server docker image

echo "Starting Conductor server"

# Start the server
cd /app/libs
echo "Property file: $CONFIG_PROP"
echo $CONFIG_PROP
export config_file=

if [ -z "$CONFIG_PROP" ];
  then
    echo "Using an in-memory instance of conductor";
    export config_file=/app/config/config-local.properties
  else
    echo "Using '$CONFIG_PROP'";
    export config_file=/app/config/$CONFIG_PROP
fi
echo "ELASTICSEARCH_URL: $ELASTICSEARCH_URL"
echo "REDIS_ADDR:    $REDIS_ADDR"
echo "REDIS_PORT:    $REDIS_PORT"
echo "REDIS_MODE:        $REDIS_MODE"
echo "REDIS_ZONE1:       $REDIS_ZONE1"
echo "REDIS_USE_SSL:     $REDIS_USE_SSL"

sed -i "s;##ELASTICSEARCH_URL##;$ELASTICSEARCH_URL;" $config_file
sed -i "s;##REDIS_ADDR##;$REDIS_ADDR;" $config_file 
sed -i "s;##REDIS_PORT##;$REDIS_PORT;" $config_file 
sed -i "s;##DB_PASSWORD##;$DB_PASSWORD;" $config_file
sed -i "s;##REDIS_MODE##;$REDIS_MODE;" $config_file
sed -i "s;##REDIS_ZONE1##;$REDIS_ZONE1;" $config_file
sed -i "s;##REDIS_USE_SSL##;$REDIS_USE_SSL;" $config_file
echo "Using java options config: $JAVA_OPTS"

java ${JAVA_OPTS} -jar -DCONDUCTOR_CONFIG_FILE=$config_file conductor-server-*-boot.jar 2>&1 | tee -a /app/logs/server.log
