package com.relfor.pcs_audting.controller;

import com.relfor.pcs_audting.models.RequestResponseAudit;
import com.relfor.pcs_audting.service.RequestResponseAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RequestResponseAuditController {

    @Autowired
    RequestResponseAuditService requestResponseAuditService;

    @PostMapping("/auditlog")
    public ResponseEntity<?> postAuditLog(@RequestBody RequestResponseAudit requestResponseAudit){
        requestResponseAudit = requestResponseAuditService.postAuditLog(requestResponseAudit);
        return ResponseEntity.ok(requestResponseAudit);
    }

}
