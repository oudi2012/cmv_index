package com.ddky.fms.refund.mapper.userlogs;

import com.ddky.fms.refund.model.userlogs.entry.StudentWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * ClassInfo
 * @author oudi
 */
public interface StudentWorkMapper {

    /***
     * 列表
     * @param info info
     * @return
     */
    List<StudentWork> list(StudentWork info);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    StudentWork findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(StudentWork item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<StudentWork> list);
}
