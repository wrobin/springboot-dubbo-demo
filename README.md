# springboot-dubbo-demo

本demo是利用dubbo官方的[dubbo-spring-boot-starter](alibaba/dubbo-spring-boot-starter) 来实现微服务的一种尝试。
涉及的技术框架有springboot,dubbo,ehcache,redis,swagger-ui,分布式Id.
全链路追踪用了[zipkin](jessyZu/dubbo-zipkin-spring-starter)


demo简单的模拟了一个用户登录获取token的过程。其中为了测试方便，用户数据是我预先创建好的。数据库初始化脚本见工程中的testdb.sql
demo主要服务模块：
1. common(公共基础模块)

2. parent(maven的依赖配置，除common外的所有其他模块都寄存此parent)<br/>
    parent包含2个服务模块的基本组成<br/>
    2.1 parent-api：rpc服务api<br/>
    2.2 parent-provider：
        2.2.1 config 服务的配置信息
        2.2.2 dao mapper或cache
        2.2.3 provider: rpc接口的实现
        2.2.4 proxy: 服务的代理层（对其他的服务依赖）可以在此层增加熔断、限流等措施，不过本demo还未实现
        2.25 service: 核心的业务实现层
   
3. auth-module 认证模块

4. user-module 用户模块

5. gateway 网关，不涉及db的操作。


每一个服务最终会打包成可执行jar,如user-module用spring-boot-maven-plugin编译生成userApp.jar，可直接运行


