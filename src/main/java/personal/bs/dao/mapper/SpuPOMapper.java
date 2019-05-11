package personal.bs.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import personal.bs.domain.po.SpuPO;
import personal.bs.domain.po.SpuPOExample;

public interface SpuPOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    long countByExample(SpuPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    int deleteByExample(SpuPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    @Delete({
        "delete from spu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    @Insert({
        "insert into spu (name, type1_id, ",
        "description, store_id, ",
        "sale_num, upload_date, ",
        "status, type2_id, ",
        "pic, spec_template_id, ",
        "caption, default_sku_id, ",
        "price, is_enable_spec, ",
        "is_delete)",
        "values (#{name,jdbcType=VARCHAR}, #{type1Id,jdbcType=INTEGER}, ",
        "#{description,jdbcType=VARCHAR}, #{storeId,jdbcType=INTEGER}, ",
        "#{saleNum,jdbcType=INTEGER}, #{uploadDate,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=VARCHAR}, #{type2Id,jdbcType=INTEGER}, ",
        "#{pic,jdbcType=VARCHAR}, #{specTemplateId,jdbcType=INTEGER}, ",
        "#{caption,jdbcType=VARCHAR}, #{defaultSkuId,jdbcType=INTEGER}, ",
        "#{price,jdbcType=DECIMAL}, #{isEnableSpec,jdbcType=VARCHAR}, ",
        "#{isDelete,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true,keyProperty="id")
    int insert(SpuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    int insertSelective(SpuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    List<SpuPO> selectByExample(SpuPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "id, name, type1_id, description, store_id, sale_num, upload_date, status, type2_id, ",
        "pic, spec_template_id, caption, default_sku_id, price, is_enable_spec, is_delete",
        "from spu",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("personal.bs.dao.mapper.SpuPOMapper.BaseResultMap")
    SpuPO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") SpuPO record, @Param("example") SpuPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") SpuPO record, @Param("example") SpuPOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(SpuPO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table spu
     *
     * @mbg.generated
     */
    @Update({
        "update spu",
        "set name = #{name,jdbcType=VARCHAR},",
          "type1_id = #{type1Id,jdbcType=INTEGER},",
          "description = #{description,jdbcType=VARCHAR},",
          "store_id = #{storeId,jdbcType=INTEGER},",
          "sale_num = #{saleNum,jdbcType=INTEGER},",
          "upload_date = #{uploadDate,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=VARCHAR},",
          "type2_id = #{type2Id,jdbcType=INTEGER},",
          "pic = #{pic,jdbcType=VARCHAR},",
          "spec_template_id = #{specTemplateId,jdbcType=INTEGER},",
          "caption = #{caption,jdbcType=VARCHAR},",
          "default_sku_id = #{defaultSkuId,jdbcType=INTEGER},",
          "price = #{price,jdbcType=DECIMAL},",
          "is_enable_spec = #{isEnableSpec,jdbcType=VARCHAR},",
          "is_delete = #{isDelete,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SpuPO record);
}