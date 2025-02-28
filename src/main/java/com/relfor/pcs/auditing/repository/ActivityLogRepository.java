package com.relfor.pcs.auditing.repository;

import com.relfor.pcs.auditing.models.ActivityLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;


@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}
