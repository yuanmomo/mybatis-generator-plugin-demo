package com.github.yuanmomo.mybatis.generator.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;


/**
 *
 */
public class BaseTest {

    protected static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void setUp() throws Exception {
        // create an SqlSessionFactory
        InputStream inputStream = null;
        try {
            System.out.println(String.format(" Current run path: %s ",new File(".").getAbsoluteFile()));
            inputStream = new FileInputStream("src/main/resources/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            Assert.fail();
            throw e;
        } finally {
            inputStream.close();
        }
    }

}

