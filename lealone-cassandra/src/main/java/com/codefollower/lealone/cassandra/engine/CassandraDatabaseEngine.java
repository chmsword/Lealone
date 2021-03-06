/*
 * Copyright 2011 The Apache Software Foundation
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.codefollower.lealone.cassandra.engine;

import com.codefollower.lealone.engine.DatabaseEngineBase;
import com.codefollower.lealone.engine.DatabaseEngineManager;

public class CassandraDatabaseEngine extends DatabaseEngineBase {
    public static final String NAME = "CASSANDRA";
    private static final CassandraDatabaseEngine INSTANCE = new CassandraDatabaseEngine();
    static {
        DatabaseEngineManager.registerDatabaseEngine(INSTANCE);
    }

    public static CassandraDatabaseEngine getInstance() {
        return INSTANCE;
    }

    @Override
    public CassandraDatabase createDatabase() {
        return new CassandraDatabase(this);
    }

    @Override
    public String getName() {
        return NAME;
    }

}
