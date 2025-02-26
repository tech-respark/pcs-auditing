package com.relfor.pcs.auditing.utility;

import com.relfor.pcs.auditing.models.ActivityLog;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ActivityLogSpecification {

    public static Specification<ActivityLog> getFilterSpecification(
            String staffName, String entity, Long tenantId, Long storeId, Instant startTime, Instant endTime) {

        return (Root<ActivityLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (staffName != null && !staffName.isEmpty()) {
                predicates.add(cb.equal(root.get("staffName"), staffName));
            }
            if (entity != null && !entity.isEmpty()) {
                predicates.add(cb.equal(root.get("entity"), entity));
            }
            if (tenantId != null) {
                predicates.add(cb.equal(root.get("tenantId"), tenantId));
            }
            if (storeId != null) {
                predicates.add(cb.equal(root.get("storeId"), storeId));
            }
            if (startTime != null) {
                predicates.add(cb.greaterThanOrEqualTo(root.get("requestTimestamp"), startTime));
            }
            if (endTime != null) {
                predicates.add(cb.lessThanOrEqualTo(root.get("requestTimestamp"), endTime));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

