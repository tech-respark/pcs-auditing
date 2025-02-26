package com.relfor.pcs.auditing.controller;
import com.relfor.pcs.auditing.models.ActivityLog;
import com.relfor.pcs.auditing.models.dto.ActivityLogDTO;
import com.relfor.pcs.auditing.models.dto.ActivityLogRequestDTO;
import com.relfor.pcs.auditing.service.ActivityLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/api/v1")
public class ActivityLogController {

    @Autowired
    ActivityLogService activityLogService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/activitylog")
    public ResponseEntity<?> postActivityLog(@RequestBody ActivityLog activityLog) {
        try {
            activityLog = activityLogService.postActivityLog(activityLog);
            logger.debug("Activity log successfully saved: {}", activityLog);
            return ResponseEntity.ok(activityLog);
        } catch (Exception e) {
            logger.error("Error saving activity log", e);
            return ResponseEntity.internalServerError().body("Failed to log activity.");
        }
    }

    @PostMapping("/fetchActivitylog")
    public ResponseEntity<Page<ActivityLogDTO>> getActivityLogs(@RequestBody ActivityLogRequestDTO request) {
        try {
            Instant startInstant = parseInstant(request.getStartTime());
            Instant endInstant = parseInstant(request.getEndTime());
            Page<ActivityLogDTO> activityLogs = activityLogService.getActivityLog(
                    request.getStaffName(),
                    request.getEntity(),
                    request.getTenantId(),
                    request.getStoreId(),
                    startInstant,
                    endInstant,
                    request.getPage(),
                    request.getSize()
            );
            return ResponseEntity.ok(activityLogs);
        } catch (Exception e) {
            logger.error("Error fetching activity logs", e);
            return ResponseEntity.internalServerError().body(null);
        }
    }

    private Instant parseInstant(String timestamp) {
        if (timestamp == null || timestamp.isEmpty()) {
            return null;
        }
        try {
            LocalDate localDate = LocalDate.parse(timestamp);
            return localDate.atStartOfDay(ZoneId.of("UTC")).toInstant();
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
