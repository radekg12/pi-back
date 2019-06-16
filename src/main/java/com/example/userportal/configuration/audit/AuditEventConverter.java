package com.example.userportal.configuration.audit;

import com.example.userportal.domain.PersistentAuditEvent;
import org.springframework.boot.actuate.audit.AuditEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AuditEventConverter {

  public List<AuditEvent> convertToAuditEvent(Iterable<PersistentAuditEvent> persistentAuditEvents) {
    if (persistentAuditEvents == null) {
      return Collections.emptyList();
    }
    List<AuditEvent> auditEvents = new ArrayList<>();
    for (PersistentAuditEvent persistentAuditEvent : persistentAuditEvents) {
      auditEvents.add(convertToAuditEvent(persistentAuditEvent));
    }
    return auditEvents;
  }

  private AuditEvent convertToAuditEvent(PersistentAuditEvent persistentAuditEvent) {
    if (persistentAuditEvent == null) {
      return null;
    }
    return new AuditEvent(persistentAuditEvent.getAuditEventDate(), persistentAuditEvent.getPrincipal(),
            persistentAuditEvent.getAuditEventType(), convertDataToObjects(persistentAuditEvent.getData()));
  }

  private Map<String, Object> convertDataToObjects(Map<String, String> data) {
    Map<String, Object> results = new HashMap<>();

    if (data != null) {
      for (Map.Entry<String, String> entry : data.entrySet()) {
        results.put(entry.getKey(), entry.getValue());
      }
    }
    return results;
  }

  public Map<String, String> convertDataToStrings(Map<String, Object> data) {
    Map<String, String> results = new HashMap<>();

    if (data != null) {
      for (Map.Entry<String, Object> entry : data.entrySet()) {
        if (entry.getValue() instanceof WebAuthenticationDetails) {
          WebAuthenticationDetails authenticationDetails = (WebAuthenticationDetails) entry.getValue();
          results.put("remoteAddress", authenticationDetails.getRemoteAddress());
          results.put("sessionId", authenticationDetails.getSessionId());
        } else {
          results.put(entry.getKey(), Objects.toString(entry.getValue()));
        }
      }
    }
    return results;
  }
}
