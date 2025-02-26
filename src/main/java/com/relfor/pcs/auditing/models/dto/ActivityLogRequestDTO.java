package com.relfor.pcs.auditing.models.dto;

public class ActivityLogRequestDTO {
    private String staffName;
    private String entity;
    private Long tenantId;
    private Long storeId;
    private String startTime;
    private String endTime;
    private int page = 0;
    private int size = 10;

    public String getStaffName() { return staffName; }
    public void setStaffName(String staffName) { this.staffName = staffName; }

    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }

    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }

    public Long getStoreId() { return storeId; }
    public void setStoreId(Long storeId) { this.storeId = storeId; }

    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }

    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }
}

