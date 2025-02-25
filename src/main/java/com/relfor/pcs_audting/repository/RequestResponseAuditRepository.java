package com.relfor.pcs_audting.repository;

import com.relfor.pcs_audting.models.RequestResponseAudit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface RequestResponseAuditRepository extends JpaRepository<RequestResponseAudit, Long> {

}