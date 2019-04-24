
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
	com.conan.service.QueryService.queryMessageList(ListService.java:16)
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


第14 更改表结构 使用双表

1:如果未添加到 mybatis config xml 下的sql 映射文件; 执行对应的sql 语句会抛出非法参数异常 

Mapped Statement collection does not contain value for xxx.xxx;

2:常用标签  
```
<where > 1: 可以替代 jdbc 中的 where 1=1   2: 可以去掉去掉and
<sql> 可以服用 表字段  配合 <include> 使用
<sql id ="columns" >x1 ,x2,x3</sql> 
<include refid ="columns"/>

<set> 类似where 标签 , 1:可以去掉 ',';
<update>
    <set>
        <if></if>
        <if></if>
        <if></if>
    </set>
</update>

<trim> 标签 可以替代where set

<trim prefix ="where" //如果trim中间的内容有内容输出 就在前面加上where
       suffixOverides ="test" //如果trim中间的内容有内容输出 就在后面加上test
       prefixOver ="and/or"  //去掉 and 和 or

</trim>

<choose>
    <when></when>
    <when></when>
    <when></when>
    <otherwise></otherwise>
</choose>

配置关系标签
<collection property ="" resultMap =""/> 一对多关系中 主表关联子表 查询主表时带出字表的内容

<association property ="" resultMap =""/> 一对多关系中 子表关联主表 查询子表时带出主表的内容

<insert useGeneraterdKeys ="true"  //使用自增属性 主键
         keyProperty =" 对应的属性名"
></insert>

```

3:column : 数据库的列名,不是数据库表中的列名,而是Sql语句查询的结果集的列名;  也就是sql语句中的名字 有别名的时候是别名;
   

4: 易混概念

resultMap: Id   resultType : java类名;
parameterMap: id(官方不推荐使用)  parameterType: java类名
```$xslt
#{} --> mybatis 会预翻译成 ? 
${} --> mybatis  没有预编译效果  会直接被替换成内容;  配合order by 使用  :
```

第15 拦截器  intercept

1: 拦截 需要实现 ibatis 下的 intercept 接口; (创建)

2: 在mybatis config xml 中配置 <plugins><plugin></plugin></plugins> (注册)

3: 实现 Intercepte ,下面是执行顺序

    3.1 : setProperties () :获取配置文件中配置的<property>
    
    3.2 : plugin() : 根据注解的内容判断是否要拦截; 简易判断,是否拦截还需要进一步判断;
    
    3.3 : intercept() :具体的拦截逻辑,使用动态代理的方式,在目标方法之前执行一系列操作(拦截),
          最终在通过invocation.proced()方法调用真正的方法;
    
        3.3.1 :Invocation 类可以提供注解标注的类的实例,要拦截的方法,传入方法的参数;
        
        3.3.2 :MateObject mybatis 提供的反射帮助类,可以访问注解标注类的所有内容;
        
        3.3.3 :MateObject 访问具体对象内容的方式类似 OGNL ;
        
        3.3.4 :intercept()中,执行完拦截的操作,最终需要调用invocation.proceed();
        
        3.3.5 : intercept() 操作应该就是AOP中 切入一个点执行了一系列操作之后,在回到原来的程序继续执行;
       
    3.4 :  使用MateObject 需要根据源码逻辑具体分析怎么调用参数;
    
            
            
       
 






