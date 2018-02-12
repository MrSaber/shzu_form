package com.shzu.shzu.mapper;

import com.shzu.shzu.model.Form;
import com.shzu.shzu.model.GG;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface FormMapper {

    int add(Form form);
    @Select("SELECT * FROM shzu_form WHERE form_user_id =#{id}")
    Form get(Integer id);
    @Select("SELECT * FROM shzu.shzu_form WHERE form_user_id = #{id} ORDER BY form_date DESC,form_id DESC LIMIT 0,1;")
    Form getLast(Integer id);

    @Select("SELECT * FROM shzu_form WHERE form_office_id=#{xyId} and form_cp_id =#{cId} and form_user_id <> '' order by form_id desc limit 1;")
    Form getByTJ(@Param("cId")Integer cid,@Param("xyId")Integer xyId);

    Form getcpBM(@Param("cId")Integer cId,@Param("xyId")Integer xyId);

    @Select("SELECT * FROM shzu_form WHERE form_office_id=#{xyId} and form_cp_id =#{cId} and form_expert_id <> '' order by form_id desc limit 1;")
    Form getcpZJ(@Param("cId")Integer cId,@Param("xyId")Integer xyId);
    @Insert("INSERT INTO `shzu`.`shzu_gg` (`gg_text`, `gg_date`) VALUES (#{gg_text}, #{gg_date});")
    void addGG(GG gg);
    @Select("SELECT  * FROM shzu.shzu_gg order by gg_id DESC limit 1;")
    GG getGG();

    void save(Form form);

    @Select("SELECT * FROM shzu_temp WHERE form_office_id=#{xyId} and form_cp_id =#{cId} and form_user_id <> '' order by form_id desc limit 1;")
    Form getSave(@Param("cId")Integer cid,@Param("xyId")Integer xyId);

    @Select("SELECT * FROM shzu_temp WHERE form_office_id=#{xyId} and form_cp_id =#{cId} and form_expert_id <> '' order by form_id desc limit 1;")
    Form getSaveZJ(@Param("cId")Integer cid,@Param("xyId")Integer xyId);
}