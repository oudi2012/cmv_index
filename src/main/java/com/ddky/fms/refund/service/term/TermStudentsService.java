package com.ddky.fms.refund.service.term;

import com.ddky.fms.refund.model.term.entry.TermStudents;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * @author a
 */
public interface TermStudentsService {

    /***
     * 列表获取
     * @param termInfo termInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<TermStudents> pageList(TermStudents termInfo, int pageIndex, int pageSize);

    /***
     * 详细
     * @param id id
     * @return TermStudents
     */
    TermStudents findById(int id);

    /***
     * 添加
     * @param termInfo termInfo
     */
    void insert(TermStudents termInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<TermStudents> list);

}
