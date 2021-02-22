package com.github.yuanmomo.mybatis.generator.test;

import static org.apache.ibatis.io.Resources.getResourceAsReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 */
public class BaseTest {

    protected static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void setUp() {
        // create an SqlSessionFactory
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            System.out.println(String.format(" Current run path: %s ", new File(".").getAbsoluteFile()));
            inputStream = new FileInputStream("src/main/resources/mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            // init db
            sqlSession = sqlSessionFactory.openSession();
            ScriptRunner runner = new ScriptRunner(sqlSession.getConnection());
            runner.setAutoCommit(true);
            runner.setStopOnError(true);
            runner.runScript(getResourceAsReader("mbg-init.sql"));
            sqlSession.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Optional.of(inputStream).ifPresent((input) -> {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            Optional.of(sqlSession).ifPresent((session) -> session.close());
        }
    }

    @Test
    public void testCreationOfDb() {
        System.out.println("Creation of db OK.");
    }

}

