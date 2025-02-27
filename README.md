Java 操作日志控制器代码分析
这是一个使用 Spring Boot 框架开发的操作日志控制器（Controller）类，用于处理与操作日志相关的 HTTP 请求。
代码结构分析
这个 OperationLogController 类主要负责：
接收前端发送的 HTTP 请求
调用相应的服务层（Service）方法处理业务逻辑
返回处理结果给前端
主要功能
创建日志：
端点：POST /api/logs
接收参数：用户名、操作内容、操作类型
功能：保存一条新的操作日志
获取所有日志：
端点：GET /api/logs
功能：返回所有操作日志记录
按用户名查询日志：
端点：GET /api/logs/user
接收参数：用户名
功能：返回指定用户的所有操作日志
按操作内容查询日志：
端点：GET /api/logs/operation
接收参数：操作内容
功能：返回包含指定操作内容的所有日志
按时间范围查询日志：
端点：GET /api/logs/time
接收参数：开始时间、结束时间
功能：返回指定时间范围内的所有日志
分页查询日志：
端点：GET /api/logs/page
接收参数：页码、每页大小
功能：返回分页后的日志数据
技术特点
使用 @RestController 注解标记为 REST 控制器，所有方法返回的对象会自动转换为 JSON 格式
使用 @RequestMapping("/api/logs") 设置基础 URL 路径
通过 @Autowired 注入 OperationLogService 服务
使用各种 HTTP 方法注解（@PostMapping、@GetMapping）处理不同类型的请求
使用 @RequestParam 接收请求参数
返回类型包括单个对象、列表和分页对象
这个控制器是典型的 RESTful API 设计，提供了完整的操作日志 CRUD 功能，特别是提供了多种查询方式，方便前端按不同条件获取日志数据。
