package com.conan.intercept;

import com.conan.entity.Page;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.javassist.tools.rmi.Proxy;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

@Intercepts(value = @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class}))
public class ByPageIntercept implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        // 获取 MetaObject 这个类是mybatis 提供的反射帮助类
        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement"); // 这里访问和 ognl 一样  这里我们访问的是RoutingStatementHandler 下面的
        String id = mappedStatement.getId();

        if (id.matches(".+ByPage$")) {
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();

            Connection connection = (Connection) invocation.getArgs()[0];
            String accountSql = "select count(*) from (" + sql + ") a";
            PreparedStatement statement = connection.prepareStatement(accountSql);
            //因为上述sql拼接 不会改变 mybatis 处理完sql之后的 ? 的格式 ,所以可以直接使用parameterHandler.setParameters(statement) 修改;
            // mybatis 自己处理的 #{ } 自己知道响应的?格式 ;
            ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
            parameterHandler.setParameters(statement);
            //设置完参数之后 执行查询
            ResultSet resultSet = statement.executeQuery();

            Map<String, Object> parameters = (Map<String, Object>) boundSql.getParameterObject();
            Page page = (Page) parameters.get("page");


            if (resultSet.next()) {
                int account = resultSet.getInt(1);
                page.setCurrentPage(account);  //这里操作的是page 在 parameters中的引用
            }

            String pageSql = sql + "limit" + page.getDbIndex() + "," + page.getDbNumber();

            metaObject.setValue("delegate.boundSql.sql", pageSql);
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

        String a = properties.getProperty("a");
        System.out.println("setProperties  a 输出的内容是  " + a);

    }
}
