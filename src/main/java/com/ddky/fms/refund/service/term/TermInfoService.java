package com.ddky.fms.refund.service.term;

import com.ddky.fms.refund.model.term.entry.TermInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * 学期
 * @author a
 */
public interface TermInfoService {

    /***
     * 列表获取
     * @param termInfo termInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<TermInfo> pageList(TermInfo termInfo, int pageIndex, int pageSize);

    /***
     * 详细
     * @param id id
     * @return TermInfo
     */
    TermInfo findById(int id);

    /***
     * 添加
     * @param areaInfo areaInfo
     */
    void insert(TermInfo areaInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<TermInfo> list);
}
