package com.omnikinds.operation_log.controller;

import com.omnikinds.operation_log.model.ActionType;
import com.omnikinds.operation_log.model.OperationLog;
import com.omnikinds.operation_log.service.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class OperationLogController {
    @Autowired
    private OperationLogService service;

    @PostMapping
    public OperationLog createLog(@RequestParam String username, @RequestParam String operation,
            @RequestParam ActionType actionType) {
        return service.saveLog(username, operation, actionType);
    }

    @GetMapping
    public List<OperationLog> getAllLogs() {
        return service.getAllLogs();
    }

    @GetMapping("/user")
    public List<OperationLog> getLogsByUsername(@RequestParam String username) {
        return service.getLogsByUsername(username);
    }

    @GetMapping("/operation")
    public List<OperationLog> getLogsByOperation(@RequestParam String operation) {
        return service.getLogsByOperation(operation);
    }

    @GetMapping("/time")
    public List<OperationLog> getLogsByTimestampBetween(@RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {
        return service.getLogsByTimestampBetween(start, end);
    }

    @GetMapping("/page")
    public Page<OperationLog> getLogsByPage(@RequestParam int page, @RequestParam int size) {
        return service.getLogsByPage(page, size);
    }
}
