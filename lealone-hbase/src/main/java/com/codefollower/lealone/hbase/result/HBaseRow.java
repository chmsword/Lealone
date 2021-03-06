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
package com.codefollower.lealone.hbase.result;

import java.util.Arrays;

import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.MurmurHash;

import com.codefollower.lealone.hbase.dbobject.table.HBaseTable;
import com.codefollower.lealone.hbase.util.HBaseUtils;
import com.codefollower.lealone.result.Row;
import com.codefollower.lealone.value.Value;

public class HBaseRow extends Row {
    private byte[] regionName;
    private Put put;
    private boolean forUpdate = false;
    private Result result;
    private HBaseTable table;
    private int hashCode = 0;

    public HBaseRow(Value[] data, int memory) {
        super(data, memory);
    }

    public HBaseRow(byte[] regionName, Value rowKey, Value[] data, int memory, Result result) {
        super(rowKey, data, memory);
        this.regionName = regionName;
        this.result = result;
    }

    public byte[] getRegionName() {
        return regionName;
    }

    public void setRegionName(byte[] regionName) {
        this.regionName = regionName;
    }

    public void setPut(Put put) {
        this.put = put;
    }

    public Put getPut() {
        return put;
    }

    public boolean isForUpdate() {
        return forUpdate;
    }

    public void setForUpdate(boolean forUpdate) {
        this.forUpdate = forUpdate;
    }

    public Result getResult() {
        return result;
    }

    public HBaseTable getTable() {
        return table;
    }

    public void setTable(HBaseTable table) {
        this.table = table;
    }

    @Override
    public int hashCode() {
        if (hashCode != 0)
            return hashCode;
        byte[] rowKey = HBaseUtils.toBytes(getRowKey());
        byte[] tableName = table.getTableNameAsBytes();
        byte[] key = Arrays.copyOf(tableName, tableName.length + rowKey.length);
        System.arraycopy(rowKey, 0, key, tableName.length, rowKey.length);
        hashCode = MurmurHash.getInstance().hash(key, 0, key.length, 0xdeadbeef);

        return hashCode;
    }
}
