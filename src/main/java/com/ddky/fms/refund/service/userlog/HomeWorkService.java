package com.ddky.fms.refund.service.userlog;

import com.ddky.fms.refund.model.userlogs.entry.HomeWork;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * @author a
 */
public interface HomeWorkService {

    /***
     * 列表获取
     * @param termInfo termInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<HomeWork> list(HomeWork termInfo, int pageIndex, int pageSize);

    /***
     * 详细
     * @param id id
     * @return HomeWork
     */
    HomeWork findById(int id);

    /***
     * 添加
     * @param termInfo termInfo
     */
    void insert(HomeWork termInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<HomeWork> list);

}
