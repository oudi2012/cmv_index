package com.ddky.fms.refund.service.term;

import com.ddky.fms.refund.model.term.entry.TermTeachers;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * @author a
 */
public interface TermTeachersService {

    /***
     * 列表获取
     * @param termInfo termInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<TermTeachers> pageList(TermTeachers termInfo, int pageIndex, int pageSize);

    /***
     * 详细
     * @param id id
     * @return TermTeachers
     */
    TermTeachers findById(int id);

    /***
     * 添加
     * @param areaInfo areaInfo
     */
    void insert(TermTeachers areaInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<TermTeachers> list);

}
