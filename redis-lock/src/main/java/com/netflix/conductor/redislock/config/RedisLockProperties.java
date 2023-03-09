/*
 * Copyright 2020 Netflix, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.netflix.conductor.redislock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("conductor.redis-lock")
public class RedisLockProperties {

    /** The redis server configuration to be used. */
    private REDIS_SERVER_TYPE serverType = REDIS_SERVER_TYPE.SINGLE;

    /** The address of the redis server following format -- host:port */
    private String serverAddress = "redis://127.0.0.1:6379";

    /** Size of parallel resolution of dns address in each sequential dns resolution bulk */
    private int sequentialDnsResolverBulkSize = 8;

    /** Initial connections number issued to slave nodes */
    private int slaveConnectionMinimumIdleSize = 4;

    /** connections pool max size issued to slave nodes */
    private int slaveConnectionPoolSize = 8;

    /** Initial connections number issued to master nodes */
    private int masterConnectionMinimumIdleSize = 4;

    /** connections pool max size issued to master nodes */
    private int masterConnectionPoolSize = 8;

    /** The username for redis authentication */
    private String serverUsername = null;

    /** The password for redis authentication */
    private String serverPassword = null;

    /** The master server name used by Redis Sentinel servers and master change monitoring task */
    private String serverMasterName = "master";

    /** The namespace to use to prepend keys used for locking in redis */
    private String namespace = "";

    /**
     * Enable to otionally continue without a lock to not block executions until the locking service
     * becomes available
     */
    private boolean ignoreLockingExceptions = false;

    public REDIS_SERVER_TYPE getServerType() {
        return serverType;
    }

    public void setServerType(REDIS_SERVER_TYPE serverType) {
        this.serverType = serverType;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public int getSequentialDnsResolverBulkSize() {
        return sequentialDnsResolverBulkSize;
    }

    public void setSequentialDnsResolverBulkSize(int sequentialDnsResolverBulkSize) {
        this.sequentialDnsResolverBulkSize = sequentialDnsResolverBulkSize;
    }

    public int getSlaveConnectionMinimumIdleSize() {
        return slaveConnectionMinimumIdleSize;
    }

    public void setSlaveConnectionMinimumIdleSize(int slaveConnectionMinimumIdleSize) {
        this.slaveConnectionMinimumIdleSize = slaveConnectionMinimumIdleSize;
    }

    public int getSlaveConnectionPoolSize() {
        return slaveConnectionPoolSize;
    }

    public void setSlaveConnectionPoolSize(int slaveConnectionPoolSize) {
        this.slaveConnectionPoolSize = slaveConnectionPoolSize;
    }

    public int getMasterConnectionMinimumIdleSize() {
        return masterConnectionMinimumIdleSize;
    }

    public void setMasterConnectionMinimumIdleSize(int masterConnectionMinimumIdleSize) {
        this.masterConnectionMinimumIdleSize = masterConnectionMinimumIdleSize;
    }

    public int getMasterConnectionPoolSize() {
        return masterConnectionPoolSize;
    }

    public void setMasterConnectionPoolSize(int masterConnectionPoolSize) {
        this.masterConnectionPoolSize = masterConnectionPoolSize;
    }

    public String getServerUsername() {
        return serverUsername;
    }

    public void setServerUsername(String serverUsername) {
        this.serverUsername = serverUsername;
    }

    public String getServerPassword() {
        return serverPassword;
    }

    public void setServerPassword(String serverPassword) {
        this.serverPassword = serverPassword;
    }

    public String getServerMasterName() {
        return serverMasterName;
    }

    public void setServerMasterName(String serverMasterName) {
        this.serverMasterName = serverMasterName;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public boolean isIgnoreLockingExceptions() {
        return ignoreLockingExceptions;
    }

    public void setIgnoreLockingExceptions(boolean ignoreLockingExceptions) {
        this.ignoreLockingExceptions = ignoreLockingExceptions;
    }

    public enum REDIS_SERVER_TYPE {
        SINGLE,
        CLUSTER,
        SENTINEL
    }
}
