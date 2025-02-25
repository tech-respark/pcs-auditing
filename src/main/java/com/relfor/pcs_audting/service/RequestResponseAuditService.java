package com.relfor.pcs_audting.service;

import com.relfor.pcs_audting.models.RequestResponseAudit;
import com.relfor.pcs_audting.repository.RequestResponseAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class RequestResponseAuditService {

    @Autowired
    RequestResponseAuditRepository requestResponseAuditRepository;

    public RequestResponseAudit postAuditLog(RequestResponseAudit requestResponseAudit) {
        try {
            requestResponseAudit = requestResponseAuditRepository.save(requestResponseAudit);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return requestResponseAudit;
    }

}
