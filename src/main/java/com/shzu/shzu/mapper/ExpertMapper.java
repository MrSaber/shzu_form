package com.shzu.shzu.mapper;

import com.shzu.shzu.model.Office;
import com.shzu.shzu.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ExpertMapper {
    @Select("SELECT * FROM shzu.shzu_user WHERE user_role=2;")
    List<User> getAll();

    @Select("SELECT * FROM shzu_office WHERE of_id IN (SELECT ea_a_id as of_id FROM shzu_e_a WHERE ea_e_id=#{exId});")
    List<Office> getAcademys(Integer exId);

    @Insert("INSERT INTO `shzu`.`shzu_user` (`user_name`, `user_password`, `user_role`, `user_other`, `user_office`, `user_permit`) VALUES (#{user_name}, #{user_password}, '2', #{user_other}, '99', '1');")
    void addU(User user);

    @Insert("INSERT INTO `shzu`.`shzu_e_a` (`ea_e_id`, `ea_a_id`) VALUES (#{ea_e_id},#{ea_a_id});")
    void addXY(@Param("ea_e_id") Integer id1,@Param("ea_a_id") Integer id2);

    @Delete("DELETE FROM `shzu`.`shzu_e_a` WHERE `ea_e_id`=#{ea_e_id} and `ea_a_id`=#{ea_a_id};")
    void delXY(@Param("ea_e_id") Integer id1,@Param("ea_a_id") Integer id2);

    @Update("UPDATE `shzu`.`shzu_user` SET `user_name`=#{user_name}, `user_password`=#{user_password}, `user_other`=#{user_other} WHERE `user_id`=#{user_id};")
    void updateU(User user);

    @Delete("DELETE FROM `shzu`.`shzu_user` WHERE `user_id`=#{id};")
    void deleteU(Integer id);
}
