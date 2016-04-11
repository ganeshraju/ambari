/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ambari.server.audit;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ambari.server.audit.event.LoginAuditEvent;
import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class LoginAuditEventTest {

  @Test
  public void testAuditMessage() throws Exception {
    // Given
    String testUserName = "USER1";
    String testRemoteIp = "127.0.0.1";

    Map<String, List<String>> roles = new HashMap<>();
    roles.put("a", Arrays.asList("r1", "r2", "r3"));

    LoginAuditEvent evnt = LoginAuditEvent.builder()
      .withTimestamp(System.currentTimeMillis())
      .withRemoteIp(testRemoteIp)
      .withUserName(testUserName)
      .withRoles(roles)
      .build();

    // When
    String actualAuditMessage = evnt.getAuditMessage();

    String roleMessage = System.lineSeparator() + "    a: r1, r2, r3" + System.lineSeparator();

    // Then
    String expectedAuditMessage = String.format("User(%s), RemoteIp(%s), Operation(User login), Roles(%s), Status(Success)",
      testUserName, testRemoteIp, roleMessage);

    assertThat(actualAuditMessage, equalTo(expectedAuditMessage));

  }

  @Test
  public void testFailedAuditMessage() throws Exception {
    // Given
    String testUserName = "USER1";
    String testRemoteIp = "127.0.0.1";
    String reason = "Bad credentials";

    Map<String, List<String>> roles = new HashMap<>();
    roles.put("a", Arrays.asList("r1", "r2", "r3"));

    LoginAuditEvent evnt = LoginAuditEvent.builder()
      .withTimestamp(System.currentTimeMillis())
      .withRemoteIp(testRemoteIp)
      .withUserName(testUserName)
      .withRoles(roles)
      .withReasonOfFailure(reason)
      .build();

    // When
    String actualAuditMessage = evnt.getAuditMessage();

    String roleMessage = System.lineSeparator() + "    a: r1, r2, r3" + System.lineSeparator();

    // Then
    String expectedAuditMessage = String.format("User(%s), RemoteIp(%s), Operation(User login), Roles(%s), Status(Failed), Reason(%s)",
      testUserName, testRemoteIp, roleMessage, reason);

    assertThat(actualAuditMessage, equalTo(expectedAuditMessage));

  }

  @Test
  public void testTimestamp() throws Exception {
    // Given
    long testTimestamp = System.currentTimeMillis();
    LoginAuditEvent evnt = LoginAuditEvent.builder()
      .withTimestamp(testTimestamp)
      .build();

    // When
    long actualTimestamp = evnt.getTimestamp();

    // Then
    assertThat(actualTimestamp, equalTo(testTimestamp));

  }

  @Test
  public void testEquals() throws Exception {
    EqualsVerifier.forClass(LoginAuditEvent.class)
      .verify();
  }
}