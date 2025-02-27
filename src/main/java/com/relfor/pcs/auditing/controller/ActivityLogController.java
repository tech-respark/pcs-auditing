package com.relfor.pcs.auditing.controller;
import com.relfor.pcs.auditing.models.ActivityLog;
import com.relfor.pcs.auditing.models.dto.DisplayActivityLogDTO;
import com.relfor.pcs.auditing.models.dto.DisplayActivityLogSearchCriteria;
import com.relfor.pcs.auditing.service.ActivityLogService;
import com.relfor.pcs.auditing.util.ParseLocalDateToInstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;

@RestController
@RequestMapping("/api/v1")
public class ActivityLogController {

    @Autowired
    ActivityLogService activityLogService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/activitylog")
    public void postActivityLog(@RequestBody ActivityLog activityLog) {
        try {
            activityLog = activityLogService.postActivityLog(activityLog);
            logger.debug("Activity log successfully saved: {}", activityLog);
        } catch (Exception e) {
            logger.error("Error saving activity log", e);
        }
    }

    @PostMapping("/fetchActivitylog")
    public ResponseEntity<Page<DisplayActivityLogDTO>> getActivityLogs(@RequestBody DisplayActivityLogSearchCriteria displayActivityLogSearchCriteria) {
        try {
            Instant startInstant = ParseLocalDateToInstant.parseInstant(displayActivityLogSearchCriteria.getStartDate(), true);
            Instant endInstant = ParseLocalDateToInstant.parseInstant(displayActivityLogSearchCriteria.getEndDate(), false);
            Page<DisplayActivityLogDTO> activityLogs = activityLogService.getActivityLog(
                    displayActivityLogSearchCriteria.getStaffName(),
                    displayActivityLogSearchCriteria.getEntity(),
                    displayActivityLogSearchCriteria.getTenantId(),
                    displayActivityLogSearchCriteria.getStoreId(),
                    startInstant,
                    endInstant,
                    displayActivityLogSearchCriteria.getPage(),
                    displayActivityLogSearchCriteria.getSize(),
                    displayActivityLogSearchCriteria.getApplicationName()
            );
            return ResponseEntity.ok(activityLogs);
        } catch (Exception e) {
            logger.error("Error fetching activity logs", e);
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
