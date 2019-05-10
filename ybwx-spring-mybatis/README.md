## 研究spring hikari 和 mybatis 深度整合的学习项目

### 研究探讨的问题：
* SqlSessionFactory or SqlSessionTemplate 和Mapper的代理类动态生成实现
* 将类的示例注入到spring IOC 中。参考 @Bean注解的实现原理
* mybatis 的mapper注解怎么和spring的自动扫描注解整合注入到spring IOC中.
* spring IOC 扫描注解配置Bean和注入到类实例（Autowired）的原理
* hikari 源码研究