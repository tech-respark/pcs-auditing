CREATE TABLE pcs_auditing.display_activity_logs (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `action` text,
  `details` text,
  `entity` text,
  `guest_name` text,
  `guest_number` text,
  `invoice_id` text,
  `logged_in_staff_id` bigint DEFAULT NULL,
  `logged_in_staff_name` text,
  `request_timestamp` datetime(6) DEFAULT NULL,
  `store_id` bigint DEFAULT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `trace_id` bigint DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  `updated_field` text,
  `username` text,
  `application_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE pcs_auditing.request_response_audit (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_ip` varchar(45) DEFAULT NULL,
  `error_details` text,
  `geolocation_latitude` varchar(50) DEFAULT NULL,
  `geolocation_longitude` varchar(50) DEFAULT NULL,
  `query_parameters` json DEFAULT NULL,
  `request_body` mediumtext,
  `request_headers` json DEFAULT NULL,
  `request_id` varchar(255) DEFAULT NULL,
  `request_method` varchar(255) DEFAULT NULL,
  `request_timestamp` datetime(6) DEFAULT NULL,
  `request_url` varchar(255) DEFAULT NULL,
  `response_body` mediumtext,
  `response_headers` json DEFAULT NULL,
  `response_status_code` int DEFAULT NULL,
  `server_url` varchar(50) DEFAULT NULL,
  `stack_trace` mediumtext,
  `store_id` bigint DEFAULT NULL,
  `tenant_id` bigint DEFAULT NULL,
  `time_taken` bigint DEFAULT NULL,
  `user_agent` varchar(1024) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `username` mediumtext,
  `application_name` varchar(255) DEFAULT NULL,
  `trace_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);