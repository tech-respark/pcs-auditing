package com.relfor.pcs.auditing.models;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "display_activity_logs")
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Instant requestTimestamp;

    @Column
    private Long traceId;

    @Column
    private Long tenantId;

    @Column
    private Long storeId;

    @Column
    private Long loggedInStaffId;

    @Column
    private String transactionId;

    private String action;

    private String entity;

    private String details;

    private String username;

    private String loggedInStaffName;

    private String guestName;

    private String guestNumber;

    private String invoiceId;

    private String updatedField;

    private String applicationName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Instant requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public Long getTraceId() {
        return traceId;
    }

    public void setTraceId(Long traceId) {
        this.traceId = traceId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getLoggedInStaffId() {
        return loggedInStaffId;
    }

    public void setLoggedInStaffId(Long loggedInStaffId) {
        this.loggedInStaffId = loggedInStaffId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoggedInStaffName() {
        return loggedInStaffName;
    }

    public void setLoggedInStaffName(String loggedInStaffName) {
        this.loggedInStaffName = loggedInStaffName;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestNumber() {
        return guestNumber;
    }

    public void setGuestNumber(String guestNumber) {
        this.guestNumber = guestNumber;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getUpdatedField() {
        return updatedField;
    }

    public void setUpdatedField(String updatedField) {
        this.updatedField = updatedField;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
