# contacts
## Spring Boot实现的Web应用程序
### 简化Spring Web开发
### Spring Boot Starter
#### 自动管理依赖、版本号
### 自动配置
#### 根据类路径加载的类自动创建需要的Bean
#### 如：DataSource、JdbcTemplate、视图解析器等
### Actuator
#### /autoconfig  使用了哪些自动配置（positiveMatches）
#### /beans，包含bean依赖关系
`数据库处理：由starter-jdbc和H2驱动，
自动创建DataSource（内存）和JdbcTemplate等，
并自动发现schema.sql，在数据库中执行。
mvn spring-boot:run
内嵌tomcat运行，默认端口号8080
`
