package com.omnikinds.operation_log.service;

import com.omnikinds.operation_log.model.ActionType;
import com.omnikinds.operation_log.model.OperationLog;
import com.omnikinds.operation_log.repository.OperationLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OperationLogService {
    @Autowired
    private OperationLogRepository repository;

    public OperationLog saveLog(String username, String operation, ActionType actionType) {
        OperationLog log = new OperationLog();
        log.setUsername(username);
        log.setOperation(operation);
        log.setTimestamp(LocalDateTime.now());
        log.setActionType(actionType);
        return repository.save(log);
    }

    public List<OperationLog> getAllLogs() {
        return repository.findAll();
    }

    public List<OperationLog> getLogsByUsername(String username) {
        return repository.findByUsername(username);
    }

    public List<OperationLog> getLogsByOperation(String operation) {
        return repository.findByOperation(operation);
    }

    public List<OperationLog> getLogsByTimestampBetween(LocalDateTime start, LocalDateTime end) {
        return repository.findByTimestampBetween(start, end);
    }

    public Page<OperationLog> getLogsByPage(int page, int size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}
