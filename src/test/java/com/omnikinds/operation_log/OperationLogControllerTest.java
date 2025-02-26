package com.omnikinds.operation_log;

import com.omnikinds.operation_log.model.ActionType;
import com.omnikinds.operation_log.model.OperationLog;
import com.omnikinds.operation_log.service.OperationLogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationLogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OperationLogService service;

    @Test
    public void testCreateLog() throws Exception {
        mockMvc.perform(post("/api/logs")
                .param("username", "testuser")
                .param("operation", "test operation")
                .param("actionType", "CREATE")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.operation").value("test operation"))
                .andExpect(jsonPath("$.actionType").value("CREATE"));
    }

    @Test
    public void testGetAllLogs() throws Exception {
        service.saveLog("testuser", "test operation", ActionType.CREATE);
        mockMvc.perform(get("/api/logs")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("testuser"))
                .andExpect(jsonPath("$[0].operation").value("test operation"))
                .andExpect(jsonPath("$[0].actionType").value("CREATE"));
    }

    @Test
    public void testGetLogsByUsername() throws Exception {
        service.saveLog("testuser", "test operation", ActionType.CREATE);
        mockMvc.perform(get("/api/logs/user")
                .param("username", "testuser")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("testuser"))
                .andExpect(jsonPath("$[0].operation").value("test operation"))
                .andExpect(jsonPath("$[0].actionType").value("CREATE"));
    }

    @Test
    public void testGetLogsByOperation() throws Exception {
        service.saveLog("testuser", "test operation", ActionType.CREATE);
        mockMvc.perform(get("/api/logs/operation")
                .param("operation", "test operation")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("testuser"))
                .andExpect(jsonPath("$[0].operation").value("test operation"))
                .andExpect(jsonPath("$[0].actionType").value("CREATE"));
    }

    @Test
    public void testGetLogsByTimestampBetween() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        service.saveLog("testuser", "test operation", ActionType.CREATE);
        mockMvc.perform(get("/api/logs/time")
                .param("start", now.minusDays(1).toString())
                .param("end", now.plusDays(1).toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("testuser"))
                .andExpect(jsonPath("$[0].operation").value("test operation"))
                .andExpect(jsonPath("$[0].actionType").value("CREATE"));
    }

    @Test
    public void testGetLogsByPage() throws Exception {
        service.saveLog("testuser", "test operation", ActionType.CREATE);
        mockMvc.perform(get("/api/logs/page")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].username").value("testuser"))
                .andExpect(jsonPath("$.content[0].operation").value("test operation"))
                .andExpect(jsonPath("$.content[0].actionType").value("CREATE"));
    }
}
