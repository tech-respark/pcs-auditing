package com.relfor.pcs.auditing.config;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.relfor.pcs.auditing.request.RequestContext;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.devourin.pcs.common.constants.CommonConstants;

@Component
public class RequestInterceptor implements HandlerInterceptor {

    @Autowired
    protected RequestContext requestContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getHeader(CommonConstants.INTERNAL_TRACE_ID) == null) {
            requestContext.setInternalTraceId(MDC.get(CommonConstants.INTERNAL_TRACE_ID));
        } else {
            requestContext.setInternalTraceId(request.getHeader(CommonConstants.INTERNAL_TRACE_ID));
        }

        requestContext.setRemoteIP(getOrDefault(request.getRemoteAddr(), "UNKNOWN_IP"));
        requestContext.setUserId(getOrDefault(request.getHeader(CommonConstants.USERID), "0"));
        requestContext.setBrowser(getOrDefault(request.getHeader(CommonConstants.USER_AGENT), "UNKNOWN_BROWSER"));
        requestContext.setLatitude(getOrDefault(request.getHeader(CommonConstants.LATITUDE), "0.0"));
        requestContext.setLongitude(getOrDefault(request.getHeader(CommonConstants.LONGITUDE), "0.0"));
        requestContext.setTenantId(getOrDefault(request.getHeader(CommonConstants.TENANTID), "0"));
        requestContext.setStoreId(getOrDefault(request.getHeader(CommonConstants.STOREID), "0"));
        requestContext.setUsername(getOrDefault(request.getHeader(CommonConstants.USERNAME), "UNKNOWN_USER"));

        return true;
    }
    private String getOrDefault(String value, String defaultValue) {
        return (value != null && !value.trim().isEmpty()) ? value : defaultValue;
    }

}
