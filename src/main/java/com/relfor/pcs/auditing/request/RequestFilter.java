package com.relfor.pcs.auditing.request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.devourin.pcs.common.constants.CommonConstants;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.*;

@Component
public class RequestFilter implements Filter {

    @Autowired
    private RequestContext requestContext;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        // Allowed HTTP methods
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);
        Instant reqTime = Instant.now();
        long startTime = System.currentTimeMillis();
        final String[] allowedMethods = new String[]{"PUT", "POST", "GET", "DELETE", "OPTIONS"};
        if (Arrays.stream(allowedMethods).noneMatch(x -> x.equals(httpServletRequest.getMethod()))) {
            httpServletResponse.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else {
            // Internal Trace ID setup
            String traceId = httpServletRequest.getHeader(CommonConstants.INTERNAL_TRACE_ID);
            if (traceId != null) {
                MDC.put(CommonConstants.INTERNAL_TRACE_ID, traceId);
            } else {
                MDC.put(CommonConstants.INTERNAL_TRACE_ID, String.valueOf(System.nanoTime()));
            }
            filterChain.doFilter(requestWrapper, responseWrapper);
            responseWrapper.copyBodyToResponse();
        }
        responseWrapper.copyBodyToResponse();
        MDC.clear();
    }


    public String getStringValue(byte[] contentAsByteArray, String characterEncoding) {
        try {
            Charset charset = Charset.forName(characterEncoding);
            return new String(contentAsByteArray, charset);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "";
    }

    public Map<String, Object> getRequestHeaders(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        Enumeration<String> keys = request.getHeaderNames();
        if (keys != null) {
            Iterator<String> key = keys.asIterator();
            while (key.hasNext()) {
                String header = keys.nextElement();
                map.put(header, request.getHeader(header));
            }
        }
        return map;
    }

    public Map<String, Object> getResponseHeaders(HttpServletResponse response) {
        Collection<String> keys = response.getHeaderNames();
        Map<String, Object> map = new HashMap<>();
        if (keys != null) {
            for (String key : keys)
                map.put(key, response.getHeader(key));
        }
        return map;
    }
}
