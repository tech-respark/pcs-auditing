package com.relfor.pcs.auditing.repository;

import com.relfor.pcs.auditing.models.RequestResponseAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestResponseAuditRepository extends JpaRepository<RequestResponseAudit, Long> {

}