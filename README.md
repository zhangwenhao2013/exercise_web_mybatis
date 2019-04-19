
第一阶段

1:创建工程.选择web

2:配置local tomcat ,配置depolyment

3:工程配置 源码路径

4:启动测试

第二阶段

1:添加页面

2:添加tomcat servlet 依赖

3:添加Servlet ,并且跳转验证


第三阶段

1:添加jdbc

2:添加mysql connection driver


第四

1:修改jsp 界面

2:使用c foreach/ if 标签 需要引入 jstl

3:要使用es 表达式 ${}   需要引入 jsp-api

4:设置 es 表达式 <%@ page isELIgnored="false" %>

第五

1:使用简易的 mvc 层级结构

2:DAO 执行sql  获取结果  封装结果  返回结果(1:获取能与数据库交互的对象, 2: 执行sql)


第六

1:引入mybatis

2:添加mybatis 配置文件

3:SqlSession 
 
```
1: 可以向sql传入参数  
2:执行sql  
3:获取执行sql语句的结果 
 4:事务控制
```

4:获取sqlsession

    1:通过配置文件获取数据库连接相关的信息
    
    2:通过配置信息构建SqlFactory
    
    3:通过SqlFactory打开数据库会话

第七

1: 层级关系   业务 --  service  -- dao  -|使用接口隔离 impl |- Db层(DbAccess) -- DataBase

2: 配置配置文件(数据库连接文件)  可以参考 http://www.mybatis.org/mybatis-3/zh/getting-started.html  注意configuration配置文件

3: 验证sqlSession

第八
1:配置Sql 映射文件  具体可以参考 http://www.mybatis.org/mybatis-3/zh/getting-started.html  注意mapper

2:mybatis 下面提供了两个配置文件  一个是数据库配置文件  一个是sql映射文件

3:sql 映射文件

    注意点
    1:mapper 需要命名空间  namespace 用来对sql 语句进行唯一化管理
    2:resultMap  主键使用id 标签 ,  非主键使用 result 标签
    3: CURD 分别对应了 insert update select delete 标签
    4: CURD 需要注意 resultMap属性的值 对应resultMap 标签的id;
    5: CURD id属性的值 就是 sqlSession请求的方法
    
4:sql 映射配置文件 需要配置的config文件中.


第九

1:OGNL表达式

```
1.1: String   : _parameter
1.2: 自定义属性名(Message)   :  属性名(command)
1.3: 集合  :  数组 :array
           List : list
           Map : _parameter
```

2.从集合中取出一条数据

```
2.1 数组 : array[ 索引 ](String [])
          array[ 索引 ].属性名(Message [])
  2.2 List : list[ 索引 ](list<String>)
           list[ 索引 ].属性名(list<Message>)
  2.3:map : _parameter.key(Map<String,String>)
           key.属性名(Map<String,Message>)`

```


3: 从foreach标签 从集合中取出数据

< foreacth collection = "array" index = "1" item = "item" >  这个标签不属于ognl
数组.array 中i 是索引
map 中 i 是 key

4:操作符

and or mod ,in ,not in
Java中的都可以 : + - * /  == != && || 等

第十

1:参数

2: 注释 不能使用 /**/ 会报错  得使用<!-- 注释内容 -->
```$xslt
注释 不能使用 /**/ 会报错  得使用<!-- 注释内容 -->
```

```
org.apache.ibatis.exceptions.PersistenceException: 
### Error querying database.  Cause: org.apache.ibatis.builder.BuilderException: Parsing error was found in mapping #{}.  Check syntax #{property|(expression), var1=value1, var2=value2, ...} 
### Cause: org.apache.ibatis.builder.BuilderException: Parsing error was found in mapping #{}.  Check syntax #{property|(expression), var1=value1, var2=value2, ...} 
	org.apache.ibatis.exceptions.ExceptionFactory.wrapException(ExceptionFactory.java:30)
	org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:150)
	org.apache.ibatis.session.defaults.DefaultSqlSession.selectList(DefaultSqlSession.java:141)
	com.conan.dao.MessageBatisDaoImpl.queryMessages(MessageBatisDao.java:27)
	com.conan.service.ListService.queryMessageList(ListService.java:16)
	com.conan.servlet.ListServlet.doGet(ListServlet.java:20)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:635)
	javax.servlet.http.HttpServlet.service(HttpServlet.java:742)
	org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:52)
```
3:  符号

```
1:ognl 支持 Java语法操作
2:#{command} mybatis 会将 #{} 处理成 ? 号   : 只有标签中才属于OEGL  真正的内容是属于mybatis的
3: "".equals(command.trim())  "" 可以用 ''  双引号可以用单引号代替
4:&quot;&quot;  也可以表示 ""
5:&amp; 可以表示 &&

```

第11 

1:修改like 语句的错误;

    sql 映射文件  like 使用 '%' 时  需要在参数前后加 空格
    
   ##### 注意::::  生产上面, 变量前面不要加%，会全表扫描
   
2:增加log4J 日志 ( 1需要引入依赖 , 2:需要配置文件log4j.xml/properties , 3:需要修改mybatis配置文件)

具体可以参考 

配置mybatis 配置log4j  [参考官网配置](http://www.mybatis.org/mybatis-3/zh/logging.html)

[log4j 官方faq](http://logging.apache.org/log4j/1.2/faq.html#sysprops)

注意点

* 1:<setting> 标签 添加在configuration标签下,有顺序要求

* 2:可以不调用 org.apache.ibatis.logging.LogFactory.useLog4JLogging();

* 3:配置文件可以命名为  log4j.properties or log4j.xml

* 4: 配置文件需要放在resource目录下面  ==> 第一阶段 第3点关联;

3: sql 映射文件 传参 使用map方式;


第12
 
1:CUD 区别于 R  是有事务操作的,需要手动commit事务

2:servlet 的任务是接收 页面传值,返回service 给的结果 ,页面跳转
  service 的任务是处理 请求参数,逻辑判断,处理数据结果
  daoimpl  隔离service 对 dao 的依赖
  dao层    的任务是请求数据库,执行sql
  DbAccess 提供链接数据库,返回dao需要的链接
 
 
 第13
 
 1:使用 Filter 处理字符问题
 
 2:复习 下 listener(ContentListener RequestListener SessionListener) 
  filter  filterChain.doFilter(servletRequest, servletResponse); 放行
  filter  可以修改访问的Servlet,jsp , 但是不能直接返回数据;
  
  
第14  批量删除  batch delete

1: js 引入的方式

```$xslt
 <script src="<%= basePath%>resources/js/list.js"></script> 
```

2: 接口的实现才使用 XxxxImpl 命名 , 接口使用Xxx






