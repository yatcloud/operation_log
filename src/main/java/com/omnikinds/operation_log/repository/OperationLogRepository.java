package com.omnikinds.operation_log.repository;

import com.omnikinds.operation_log.model.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationLogRepository extends JpaRepository<OperationLog, Long> {
    List<OperationLog> findByUsername(String username);

    List<OperationLog> findByOperation(String operation);

    List<OperationLog> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
