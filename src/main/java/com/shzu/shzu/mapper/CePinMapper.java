package com.shzu.shzu.mapper;

import com.shzu.shzu.model.CePin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface CePinMapper {
    @Insert("INSERT INTO `shzu`.`shzu_cp` (`cp_name`, `cp_begindate`, `cp_enddate`) VALUES (#{cp_name}, #{cp_begindate}, #{cp_enddate});")
    void add(CePin cePin);

    @Select("SELECT * FROM shzu.shzu_cp order by cp_begindate DESC,cp_id DESC;")
    List<CePin> getAll();


    @Select("SELECT cp_enddate FROM shzu.shzu_cp WHERE cp_id = #{cpId}")
    Date getDeadDate(Integer cpId);

}
