package com.github.yuanmomo.mybatis.generator.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.yuanmomo.mybatis.generator.test.bean.DynamicTable;
import com.github.yuanmomo.mybatis.generator.test.mapper.DynamicTableMapper;


/**
 *
 */

public class DynamicTableMapperTest extends BaseTest {

    public static final String TABLE_NAME = "mbg_table";
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
            DynamicTableMapper mapper = sqlSession.getMapper(DynamicTableMapper.class);

            DynamicTable dynamicTable = DynamicTable.init(1, 1000001L, "remark1", null);
            dynamicTable.setDynamicTableName(TABLE_NAME);
            Assert.assertTrue(mapper.insertSelective( dynamicTable) == 1);

            // get
            Assert.assertTrue(mapper.selectByPrimaryKey(TABLE_NAME,dynamicTable.getId()) != null);

            // delete
            Assert.assertTrue(mapper.deleteByPrimaryKey(TABLE_NAME,dynamicTable.getId()) == 1);

            // select
            Assert.assertTrue(mapper.selectByPrimaryKey(TABLE_NAME,dynamicTable.getId()) == null);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    @Test
    public void insertSelective() {
        try {
            DynamicTableMapper mapper = sqlSession.getMapper(DynamicTableMapper.class);

            DynamicTable dynamicTable = DynamicTable.init(1, 1000001L, null, "long_text");

            // assert insert count
            dynamicTable.setDynamicTableName(TABLE_NAME);
            Assert.assertTrue(mapper.insertSelective( dynamicTable) == 1);

            DynamicTable inserted = mapper.selectByPrimaryKey(TABLE_NAME,dynamicTable.getId());

            // assert inserted object
            Assert.assertEquals(inserted.getIntValue(), dynamicTable.getIntValue());
            Assert.assertEquals(inserted.getLongValue(), dynamicTable.getLongValue());

            Assert.assertTrue(StringUtils.isBlank(inserted.getStringValue()));
            Assert.assertTrue(StringUtils.isBlank(dynamicTable.getStringValue()));
            Assert.assertTrue(StringUtils.equalsAnyIgnoreCase(inserted.getTextValue(), dynamicTable.getTextValue()));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


    @Test
    public void updateByPrimaryKeySelective() {
        try {
            DynamicTableMapper mapper = sqlSession.getMapper(DynamicTableMapper.class);

            DynamicTable dynamicTable = DynamicTable.init(1, 1000001L, "string_value", "long_text");

            // assert insert count
            dynamicTable.setDynamicTableName(TABLE_NAME);
            Assert.assertTrue(mapper.insertSelective( dynamicTable) == 1);


            DynamicTable updateBefore = mapper.selectByPrimaryKey(TABLE_NAME,dynamicTable.getId());
            updateBefore.setIntValue(2);
            updateBefore.setTextValue("long_text_new_2");

            Assert.assertEquals(mapper.updateByPrimaryKeySelective(TABLE_NAME,updateBefore), 1);

            DynamicTable updateAfter = mapper.selectByPrimaryKey(TABLE_NAME,dynamicTable.getId());

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
            DynamicTableMapper mapper = sqlSession.getMapper(DynamicTableMapper.class);
            List<DynamicTable> dynamicTableList = new ArrayList<>();

            dynamicTableList.add(DynamicTable.init(1, 1000L, "remark1", "long_text1"));
            dynamicTableList.add(DynamicTable.init(2, 2000L, "remark2", "long_text2"));
            dynamicTableList.add(DynamicTable.init(3, 3000L, "remark3", "long_text3"));
            dynamicTableList.add(DynamicTable.init(4, 4000L, "remark4", "long_text4"));

            // insert
            int insertCount = mapper.batchInsert(TABLE_NAME,dynamicTableList);

            // select
            long count = mapper.count(TABLE_NAME);

            // assert insert count
            Assert.assertTrue(count == dynamicTableList.size());
            Assert.assertTrue(insertCount == dynamicTableList.size());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }


}