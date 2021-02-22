package com.github.yuanmomo.mybatis.generator.test.table;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.yuanmomo.mybatis.generator.test.BaseTest;
import com.github.yuanmomo.mybatis.generator.test.bean.MbgTable;
import com.github.yuanmomo.mybatis.generator.test.mapper.MbgTableMapper;


/**
 *
 */

public class TableMapperTest extends BaseTest {

    public static SqlSession sqlSession = null;

    @Before
    public void before() {
        sqlSession = sqlSessionFactory.openSession(false);
    }

    @After
    public void after() {
        sqlSession.rollback(true);
        sqlSession.close();
    }

    @Test
    public void deleteByPrimaryKey() {
        try {
            MbgTableMapper mapper = sqlSession.getMapper(MbgTableMapper.class);

            MbgTable mbgTable = MbgTable.init(1, 1000001L, "remark1", null);
            Assert.assertTrue(mapper.insertSelective(mbgTable) == 1);

            // get
            Assert.assertTrue(mapper.selectByPrimaryKey(mbgTable.getId()) != null);

            // delete
            Assert.assertTrue(mapper.deleteByPrimaryKey(mbgTable.getId()) == 1);

            // select
            Assert.assertTrue(mapper.selectByPrimaryKey(mbgTable.getId()) == null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void insertSelective() {
        try {
            MbgTableMapper mapper = sqlSession.getMapper(MbgTableMapper.class);

            MbgTable mbgTable = MbgTable.init(1, 1000001L, null, "long_text");

            // assert insert count
            Assert.assertTrue(mapper.insertSelective(mbgTable) == 1);

            MbgTable inserted = mapper.selectByPrimaryKey(mbgTable.getId());

            // assert inserted object
            Assert.assertEquals(inserted.getIntValue(), mbgTable.getIntValue());
            Assert.assertEquals(inserted.getLongValue(), mbgTable.getLongValue());

            Assert.assertTrue(StringUtils.isBlank(inserted.getStringValue()));
            Assert.assertTrue(StringUtils.isBlank(mbgTable.getStringValue()));
            Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(inserted.getTextValue(), mbgTable.getTextValue()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


    @Test
    public void updateByPrimaryKeySelective() {
        try {
            MbgTableMapper mapper = sqlSession.getMapper(MbgTableMapper.class);

            MbgTable mbgTable = MbgTable.init(1, 1000001L, "string_value", "long_text");

            // assert insert count
            Assert.assertTrue(mapper.insertSelective(mbgTable) == 1);


            MbgTable updateBefore = mapper.selectByPrimaryKey(mbgTable.getId());
            updateBefore.setIntValue(2);
            updateBefore.setTextValue("long_text_new_2");

            Assert.assertEquals(mapper.updateByPrimaryKeySelective(updateBefore), 1);

            MbgTable updateAfter = mapper.selectByPrimaryKey(mbgTable.getId());

            Assert.assertEquals(updateAfter.getIntValue(), updateBefore.getIntValue());
            Assert.assertEquals(updateAfter.getLongValue(), updateBefore.getLongValue());

            Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(updateAfter.getStringValue(), updateBefore.getStringValue()));
            Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(updateAfter.getTextValue(), updateBefore.getTextValue()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void batchInsert() {
        try {
            MbgTableMapper mapper = sqlSession.getMapper(MbgTableMapper.class);
            List<MbgTable> mbgTableList = new ArrayList<>();

            mbgTableList.add(MbgTable.init(1, 1000L, "remark1", "long_text1"));
            mbgTableList.add(MbgTable.init(2, 2000L, "remark2", "long_text2"));
            mbgTableList.add(MbgTable.init(3, 3000L, "remark3", "long_text3"));
            mbgTableList.add(MbgTable.init(4, 4000L, "remark4", "long_text4"));

            // insert
            int insertCount = mapper.batchInsert(mbgTableList);

            // select
            long count = mapper.count();

            // assert insert count
            Assert.assertTrue(count == mbgTableList.size());
            Assert.assertTrue(insertCount == mbgTableList.size());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


}