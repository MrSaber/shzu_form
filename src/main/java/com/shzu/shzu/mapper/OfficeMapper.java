package com.shzu.shzu.mapper;

import com.shzu.shzu.model.Office;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OfficeMapper {
    @Select("SELECT * FROM shzu.shzu_user,shzu_office WHERE shzu_user.user_office=of_id AND user_role =1;")
     List<Office> get();

    @Update("UPDATE `shzu`.`shzu_office` SET `of_permit`='1' WHERE `of_id`=#{xyId};")
     void resetQX(Integer xyId);

    @Update("UPDATE `shzu`.`shzu_office` SET `of_permit`='1;")
    void resetAllQX();
}
