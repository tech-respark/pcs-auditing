package com.relfor.pcs.auditing.service;

import com.relfor.pcs.auditing.models.RequestResponseAudit;
import com.relfor.pcs.auditing.repository.RequestResponseAuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class RequestResponseAuditService {

    @Autowired
    RequestResponseAuditRepository requestResponseAuditRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RequestResponseAudit postAuditLog(RequestResponseAudit requestResponseAudit) {
        try {
            requestResponseAudit = requestResponseAuditRepository.save(requestResponseAudit);
            return requestResponseAudit;
        } catch (Exception e) {
            logger.error("Error saving audit log: {}", requestResponseAudit, e);
            throw e;
        }
    }
}
