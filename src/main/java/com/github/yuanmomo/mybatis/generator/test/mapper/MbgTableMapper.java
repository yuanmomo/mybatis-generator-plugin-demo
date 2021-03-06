package com.github.yuanmomo.mybatis.generator.test.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import com.github.yuanmomo.mybatis.generator.test.bean.MbgTable;

public interface MbgTableMapper {

    @Select({ "select count(id) from mbg_table" })
    int count();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mbg_table
     *
     * @mbg.generated
     */
    @Delete({ "delete from mbg_table", "where id = #{id,jdbcType=BIGINT}" })
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mbg_table
     *
     * @mbg.generated
     */
    @InsertProvider(type = MbgTableSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Long.class)
    int insertSelective(MbgTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mbg_table
     *
     * @mbg.generated
     */
    @Select({ "select", "id, int_value, long_value, string_value, text_value", "from mbg_table", "where id = #{id,jdbcType=BIGINT}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true), @Result(column = "int_value", property = "intValue", jdbcType = JdbcType.INTEGER), @Result(column = "long_value", property = "longValue", jdbcType = JdbcType.BIGINT), @Result(column = "string_value", property = "stringValue", jdbcType = JdbcType.VARCHAR), @Result(column = "text_value", property = "textValue", jdbcType = JdbcType.LONGVARCHAR) })
    MbgTable selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mbg_table
     *
     * @mbg.generated
     */
    @UpdateProvider(type = MbgTableSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(MbgTable record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mbg_table
     *
     * @mbg.generated
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert({ "<script>", "insert into mbg_table (int_value, ", "long_value, string_value, ", "text_value)", "values<foreach collection=\"list\" item=\"detail\" index=\"index\" separator=\",\">(#{detail.intValue,jdbcType=INTEGER}, ", "#{detail.longValue,jdbcType=BIGINT}, #{detail.stringValue,jdbcType=VARCHAR}, ", "#{detail.textValue,jdbcType=LONGVARCHAR})</foreach></script>" })
    int batchInsert(java.util.List<MbgTable> list);
}
