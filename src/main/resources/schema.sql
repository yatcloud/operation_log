-- 創建操作紀錄表
CREATE TABLE operation_log (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 自增主鍵
    username VARCHAR(32) NOT NULL, -- 用戶名，長度為32
    operation VARCHAR(255) NOT NULL, -- 操作描述
    timestamp TIMESTAMP NOT NULL, -- 操作時間戳
    actionType ENUM('CREATE', 'READ', 'UPDATE', 'DELETE') NOT NULL -- 操作類型，使用ENUM
);
