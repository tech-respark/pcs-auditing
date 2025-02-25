package com.relfor.pcs_audting.controller;
import com.relfor.pcs_audting.models.ActivityLog;
import com.relfor.pcs_audting.models.dto.ActivityLogDTO;
import com.relfor.pcs_audting.models.dto.ActivityLogRequestDTO;
import com.relfor.pcs_audting.service.ActivityLogService;
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

    @PostMapping("/activitylog")
    public ResponseEntity<?> postActivityLog(@RequestBody ActivityLog activityLog){
        System.out.println("Action : " + activityLog.getEntity());
        activityLog = activityLogService.postActivityLog(activityLog);
        return ResponseEntity.ok(activityLog);
    }

    @PostMapping("/fetchActivitylog")
    public ResponseEntity<Page<ActivityLogDTO>> getActivityLogs(@RequestBody ActivityLogRequestDTO request) {
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
