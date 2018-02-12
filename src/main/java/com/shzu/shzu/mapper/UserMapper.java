package com.shzu.shzu.mapper;

import com.shzu.shzu.model.Office;
import com.shzu.shzu.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM shzu.shzu_user,shzu_office WHERE user_name=#{user_name} and user_password=#{user_password} and user_office=of_id;")
    User getUser(User user);

    @Select("SELECT * FROM shzu.shzu_user,shzu_office WHERE shzu_user.user_office=of_id AND user_role =#{type};")
    List<User> getALL(Integer type);

    @Insert("INSERT INTO `shzu`.`shzu_user` (`user_name`, `user_password`, `user_role`, `user_other`, `user_office`, `user_permit`) VALUES (#{user_name}, #{user_password}, '1', '1', #{user_office}, '1');")
    void addU(User user);

    @Insert("INSERT INTO `shzu`.`shzu_user` (`user_name`, `user_password`, `user_role`, `user_other`, `user_office`, `user_permit`) VALUES (#{user_name}, #{user_password}, '3', #{user_other}, #{user_office}, '1');")
    void addBM(User user);


    @Update("UPDATE `shzu`.`shzu_user` SET `user_name`=#{user_name}, `user_password`=#{user_password} WHERE `user_id`=#{user_id};")
    void update(User user);

    @Delete("DELETE FROM `shzu`.`shzu_user` WHERE `user_id`=#{id};")
    void del(Integer id);

    @Select("SELECT of_permit FROM academy WHERE of_id=#{id}")
    Integer isAllow(Integer id);
}
