/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.ambari.view.huetoambarimigration.datasource.queryset.huequeryset.pig.savedscriptqueryset;


public class OracleQuerySetHueDb extends QuerySetHueDb {

  @Override
  protected String fetchuserIdfromUsernameSql() {
    return "select id from auth_user where username=?";
  }
  @Override
  protected String fetchHueQueriesNoStartdateNoEnddateSql() {
    return "select pig_script,title,date_created,saved,arguments from pig_pigscript where saved=1 AND user_id =?";
  }
  @Override
  protected String fetchHueQueriesNoStartdateYesEnddateSql() {
    return "select pig_script,title,date_created,saved,arguments from pig_pigscript where saved=1 AND user_id =? AND  date_created <= date(?)";
  }
  @Override
  protected String fetchHueQueriesYesStartdateNoEnddateSql() {
    return "select pig_script,title,date_created,saved,arguments from pig_pigscript where saved=1 AND user_id =? AND date_created >= date(?)";
  }
  @Override
  protected String fetchHueQueriesYesStartdateYesEnddateSql() {
    return "select pig_script,title,date_created,saved,arguments from pig_pigscript where saved=1 AND user_id =? AND date_created >= date(?) AND date_created <= date(?)";
  }
  @Override
  protected String fetchHueQueriesNoStartdateNoEnddateYesallUserSql() {
    return "select pig_script,title,date_created,saved,arguments from pig_pigscript where saved=1 ";
  }
  @Override
  protected String fetchHueQueriesNoStartdateYesEnddateYesallUserSql() {
    return "select pig_script,title,date_created,saved,arguments from pig_pigscript where saved=1  AND  date_created <= date(?)";
  }
  @Override
  protected String fetchHueQueriesYesStartdateNoEnddateYesallUserSql() {
    return "select pig_script,title,date_created,saved,arguments from pig_pigscript where saved=1  AND date_created >= date(?)";
  }
  @Override
  protected String fetchHueQueriesYesStartdateYesEnddateYesallUserSql() {
    return "select pig_script,title,date_created,saved,arguments from pig_pigscript where saved=1  AND date_created >= date(?) AND date_created <= date(?)";
  }
  @Override
  protected String fetchUserNameSql() {
    return "select username from auth_user where id = ?;";
  }

}
