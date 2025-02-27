package com.relfor.pcs.auditing.models;

import com.relfor.pcs.auditing.util.MapToJsonConverter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "request_response_audit")
public class RequestResponseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String traceId;

    @Column
    private Long tenantId;

    @Column
    private Long storeId;

    @Column
    private Instant requestTimestamp;

    @Column(length = 45)
    private String clientIp;

    @Column(length = 1024)
    private String userAgent;

    @Column
    private String requestMethod;

    @Column
    private String requestUrl;

    @Column(columnDefinition = "JSON")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Object> requestHeaders;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String requestBody;

    @Column(columnDefinition = "JSON")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Object> queryParameters;

    @Column
    private Integer responseStatusCode;

    @Column(columnDefinition = "JSON")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Object> responseHeaders;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String responseBody;

    @Column
    private Long timeTaken;

    @Column
    private String userId;

    @Column(length = 50)
    private String serverUrl;

    @Column(columnDefinition = "TEXT")
    private String errorDetails;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String stackTrace;
    @Column(length = 50)
    private String geolocationLatitude;
    @Column(length = 50)
    private String geolocationLongitude;
    private String username;
    private String applicationName;

    public RequestResponseAudit(Long id, String traceId, Long tenantId, Long storeId, Instant requestTimestamp, String clientIp, String userAgent, String requestMethod, String requestUrl, Map<String, Object> requestHeaders, String requestBody, Map<String, Object> queryParameters, Integer responseStatusCode, Map<String, Object> responseHeaders, String responseBody, Long timeTaken, String userId, String serverUrl, String errorDetails, String stackTrace, String geolocationLatitude, String geolocationLongitude, String username, String applicationName) {
        this.id = id;
        this.traceId = traceId;
        this.tenantId = tenantId;
        this.storeId = storeId;
        this.requestTimestamp = requestTimestamp;
        this.clientIp = clientIp;
        this.userAgent = userAgent;
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
        this.queryParameters = queryParameters;
        this.responseStatusCode = responseStatusCode;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
        this.timeTaken = timeTaken;
        this.userId = userId;
        this.serverUrl = serverUrl;
        this.errorDetails = errorDetails;
        this.stackTrace = stackTrace;
        this.geolocationLatitude = geolocationLatitude;
        this.geolocationLongitude = geolocationLongitude;
        this.username = username;
        this.applicationName = applicationName;
    }

    public String getGeolocationLongitude() {
        return geolocationLongitude;
    }

    public void setGeolocationLongitude(String geolocationLongitude) {
        this.geolocationLongitude = geolocationLongitude;
    }

    public String getGeolocationLatitude() {
        return geolocationLatitude;
    }

    public void setGeolocationLatitude(String geolocationLatitude) {
        this.geolocationLatitude = geolocationLatitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Instant getRequestTimestamp() {
        return requestTimestamp;
    }

    public void setRequestTimestamp(Instant requestTimestamp) {
        this.requestTimestamp = requestTimestamp;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Map<String, Object> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, Object> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public Map<String, Object> getQueryParameters() {
        return queryParameters;
    }

    public void setQueryParameters(Map<String, Object> queryParameters) {
        this.queryParameters = queryParameters;
    }

    public Integer getResponseStatusCode() {
        return responseStatusCode;
    }

    public void setResponseStatusCode(Integer responseStatusCode) {
        this.responseStatusCode = responseStatusCode;
    }

    public Map<String, Object> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Map<String, Object> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public Long getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = String.valueOf(userId);
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    @Override
    public String toString() {
        return "RequestResponseAudit{" +
                "id=" + id +
                ", traceId='" + traceId + '\'' +
                ", tenantId=" + tenantId +
                ", storeId=" + storeId +
                ", requestTimestamp=" + requestTimestamp +
                ", clientIp='" + clientIp + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestUrl='" + requestUrl + '\'' +
                ", requestHeaders=" + requestHeaders +
                ", requestBody='" + requestBody + '\'' +
                ", queryParameters=" + queryParameters +
                ", responseStatusCode=" + responseStatusCode +
                ", responseHeaders=" + responseHeaders +
                ", responseBody='" + responseBody + '\'' +
                ", timeTaken=" + timeTaken +
                ", userId='" + userId + '\'' +
                ", serverUrl='" + serverUrl + '\'' +
                ", errorDetails='" + errorDetails + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", geolocationLatitude='" + geolocationLatitude + '\'' +
                ", geolocationLongitude='" + geolocationLongitude + '\'' +
                ", username='" + username + '\'' +
                ", applicationName='" + applicationName + '\'' +
                '}';
    }
}



