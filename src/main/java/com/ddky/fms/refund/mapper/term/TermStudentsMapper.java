package com.ddky.fms.refund.mapper.term;

import com.ddky.fms.refund.model.term.entry.TermStudents;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * TermStudents
 * @author oudi
 */
public interface TermStudentsMapper {

    /***
     * 列表
     * @param termInfo termInfo
     * @return List
     */
    List<TermStudents> list(TermStudents termInfo);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    TermStudents findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(TermStudents item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<TermStudents> list);
}
