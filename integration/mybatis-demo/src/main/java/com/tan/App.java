package com.tan;

import com.tan.mapper.BlogMapper;
import com.tan.model.Blog;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        // ------------ 使用 XML 构建 SqlSessionFactory
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // ------------ 不使用 XML 构建 SqlSessionFactory

//        String driver = "com.mysql.cj.jdbc.Driver";
//        String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&useSSL=false";
//        String username = "root";
//        String password = "root";
//        DataSource dataSource = new PooledDataSource(driver,url,username,password);
//        TransactionFactory transactionFactory = new JdbcTransactionFactory();
//        Environment environment = new Environment("development", transactionFactory, dataSource);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(BlogMapper.class);
//
//        String resource = "mapper/BlogMapper.xml";
//        InputStream inputStream = Resources.getResourceAsStream(resource);
//        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
//        xmlMapperBuilder.parse();
//        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);

        // 从 SqlSessionFactory 中获取 SqlSession
        try (SqlSession session = sqlSessionFactory.openSession()) {
//            Blog blog = (Blog) session.selectOne("org.mybatis.example.BlogMapper.selectBlog", 101);

//            BlogMapper mapper = session.getMapper(BlogMapper.class);
//            Blog blog1 = mapper.selectOne("111");

            Blog blog = (Blog) session.selectOne("com.tan.mapper.BlogMapper.selectBlog", 101);

        }


    }
}
