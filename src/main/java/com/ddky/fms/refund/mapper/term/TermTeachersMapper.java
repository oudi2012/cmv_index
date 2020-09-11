package com.ddky.fms.refund.mapper.term;

import com.ddky.fms.refund.model.term.entry.TermTeachers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * TermTeachers
 * @author oudi
 */
public interface TermTeachersMapper {

    /***
     * 列表
     * @param termInfo termInfo
     * @return List
     */
    List<TermTeachers> list(TermTeachers termInfo);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    TermTeachers findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(TermTeachers item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<TermTeachers> list);
}
