# springboot-dubbo-demo

本demo是利用dubbo官方的dubbo-spring-boot-starter 来实现微服务的一种尝试。涉及的技术
有springboot,dubbo,zipkin,ehcache,swagger-ui, 分布式锁等


demo简单的模拟了一个用户登录获取token的过程。其中为了测试方便，用户数据是我预先创建好的。
demo主要服务模块：
1. common(公共基础模块)

2. parent(maven的依赖配置，除common外的所有其他模块都寄存此parent)<br/>
    parent包含2个服务模块的基本组成<br/>
    2.1 parent-api：rpc服务api<br/>
    2.2 parent-provider：rpc服务具体实现模块
   
3. auth-module 认证模块

4. user-module 用户模块


