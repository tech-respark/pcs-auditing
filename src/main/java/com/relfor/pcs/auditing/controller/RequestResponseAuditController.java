package com.relfor.pcs.auditing.controller;

import com.relfor.pcs.auditing.models.RequestResponseAudit;
import com.relfor.pcs.auditing.service.RequestResponseAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RequestResponseAuditController {

    @Autowired
    RequestResponseAuditService requestResponseAuditService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/auditlog")
    public ResponseEntity<?> postAuditLog(@RequestBody RequestResponseAudit requestResponseAudit) {
        try {
            requestResponseAudit = requestResponseAuditService.postAuditLog(requestResponseAudit);
            return ResponseEntity.ok(requestResponseAudit);
        } catch (Exception e) {
            logger.error("Error saving audit log: {}", requestResponseAudit, e);
            return ResponseEntity.status(500).body("Failed to save audit log.");
        }
    }


}
