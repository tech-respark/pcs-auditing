package com.relfor.pcs.auditing.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.relfor.pcs.auditing.models.ActivityLog;
import com.relfor.pcs.auditing.models.dto.ActivityLogDTO;
import com.relfor.pcs.auditing.repository.ActivityLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActivityLogService {

    @Autowired
    ActivityLogRepository activityLogRepository;
    @PersistenceContext
    private EntityManager entityManager;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ActivityLog postActivityLog(ActivityLog activityLog) {
        try {
            activityLog = activityLogRepository.save(activityLog);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return activityLog;
    }


    public Page<ActivityLogDTO> getActivityLog(String staffName, String entity, Long tenantId, Long storeId,
                                               Instant startTime, Instant endTime, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("requestTimestamp").descending());

        StringBuilder queryBuilder = new StringBuilder("FROM activity_logs WHERE tenant_id = ?");
        List<Object> params = new ArrayList<>();
        params.add(tenantId);

        if (storeId != 0) {
            queryBuilder.append(" AND store_id = ?");
            params.add(storeId);
        }

        if (!"All".equalsIgnoreCase(staffName)) {
            queryBuilder.append(" AND staff_name LIKE ?");
            params.add("%" + staffName + "%");
        }
        if (!"All".equalsIgnoreCase(entity)) {
            queryBuilder.append(" AND entity LIKE ?");
            params.add("%" + entity + "%");
        }

        queryBuilder.append(" AND request_timestamp BETWEEN ? AND ?");
        params.add(startTime);
        params.add(endTime);

        // Query to fetch total count
        String countQueryStr = "SELECT COUNT(*) " + queryBuilder;
        Query countQuery = entityManager.createNativeQuery(countQueryStr);
        for (int i = 0; i < params.size(); i++) {
            countQuery.setParameter(i + 1, params.get(i));
        }
        Long totalElements = ((Number) countQuery.getSingleResult()).longValue();

        queryBuilder.append(" ORDER BY request_timestamp DESC LIMIT ? OFFSET ?");
        params.add(size);
        params.add(page * size);

        Query dataQuery = entityManager.createNativeQuery("SELECT * " + queryBuilder, ActivityLog.class);
        for (int i = 0; i < params.size(); i++) {
            dataQuery.setParameter(i + 1, params.get(i));
        }

        List<ActivityLog> logs = dataQuery.getResultList();

        List<ActivityLogDTO> processedLogs = logs.stream()
                .map(log -> {
                    ActivityLogDTO activityLog = new ActivityLogDTO();
                    activityLog.setId(log.getId());
                    activityLog.setRequestTimestamp(log.getRequestTimestamp());
                    activityLog.setTraceId(log.getTraceId());
                    activityLog.setTenantId(log.getTenantId());
                    activityLog.setStoreId(log.getStoreId());
                    activityLog.setAction(log.getAction());
                    activityLog.setEntity(log.getEntity());
                    activityLog.setDetails(log.getDetails());
                    activityLog.setUsername(log.getUsername());
                    activityLog.setLoggedInStaffName(log.getLoggedInStaffName());
                    activityLog.setGuestName(log.getGuestName());
                    activityLog.setGuestNumber(log.getGuestNumber());
                    activityLog.setInvoiceId(log.getInvoiceId());
                    activityLog.setLoggedInStaffId(log.getLoggedInStaffId());
                    activityLog.setUpdatedField(processUpdatedFields(log.getUpdatedField(),objectMapper));

                    return activityLog;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(processedLogs, pageable, totalElements);
    }



    private String processUpdatedFields(String updatedFieldJson, ObjectMapper objectMapper) {
        if (updatedFieldJson == null || updatedFieldJson.isEmpty()) {
            return updatedFieldJson;
        }

        try {
            List<String> fields = objectMapper.readValue(updatedFieldJson, new TypeReference<List<String>>() {
            });

            Map<String, String> fieldMappings = new HashMap<>();
            fieldMappings.put("statuses", "Order Status");
            fieldMappings.put("payments", "Payment Details");
            fieldMappings.put("products", "Billing Items");
            fieldMappings.put("payMode", "Payment Mode");
            fieldMappings.put("guest", "Guest Name");
            fieldMappings.put("phone", "Guest Phone Number");
            fieldMappings.put("total", "Total Amount");
            fieldMappings.put("orderDay", "Order Date");
            fieldMappings.put("discount", "Discount Details");
            fieldMappings.put("couponCode", "Coupon Code Applied");
            fieldMappings.put("giftCardNo", "Gift Card Used");
            fieldMappings.put("newSharedMembers", "Members Added");
            fieldMappings.put("toDate", "Expiry Date");
            fieldMappings.put("balanceMinutes", "Remaining Minutes");
            fieldMappings.put("balanceAmount", "Remaining Balance");
            fieldMappings.put("appointmentDay", "Appointment Date");
            fieldMappings.put("appointmentTime", "Appointment Time");
            fieldMappings.put("guestName", "Guest Name");
            fieldMappings.put("guestMobile", "Guest Phone Number");
            fieldMappings.put("expertAppointments", "Appointment Items");
            fieldMappings.put("mobileNo", "Guest Phone Number");
            fieldMappings.put("gender", "Gender");
            fieldMappings.put("firstName", "Guest Name");
            List<String> processedFields = fields.stream()
                    .filter(fieldMappings::containsKey)
                    .map(fieldMappings::get)
                    .collect(Collectors.toList());

            return objectMapper.writeValueAsString(processedFields);
        } catch (Exception e) {
            e.printStackTrace();
            return "[]";
        }

    }
}
