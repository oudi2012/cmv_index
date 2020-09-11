package com.ddky.fms.refund.service.userlog;

import com.ddky.fms.refund.model.userlogs.entry.StudentWork;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * StudentWork
 * @author a
 */
public interface StudentWorkService {

    /***
     * 列表获取
     * @param termInfo termInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<StudentWork> list(StudentWork termInfo, int pageIndex, int pageSize);

    /***
     * 详细
     * @param id id
     * @return StudentWork
     */
    StudentWork findById(int id);

    /***
     * 添加
     * @param termInfo termInfo
     */
    void insert(StudentWork termInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<StudentWork> list);

}
