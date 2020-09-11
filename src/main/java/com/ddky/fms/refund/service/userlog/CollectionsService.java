package com.ddky.fms.refund.service.userlog;

import com.ddky.fms.refund.model.userlogs.entry.Collections;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * @author a
 */
public interface CollectionsService {

    /***
     * 列表获取
     * @param termInfo termInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<Collections> list(Collections termInfo, int pageIndex, int pageSize);

    /***
     * 详细
     * @param id id
     * @return Collections
     */
    Collections findById(int id);

    /***
     * 添加
     * @param termInfo termInfo
     */
    void insert(Collections termInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<Collections> list);

}
