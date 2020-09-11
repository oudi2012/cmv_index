package com.ddky.fms.refund.service.term;

import com.ddky.fms.refund.model.term.entry.TermBooks;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * @author a
 */
public interface TermBooksService {

    /***
     * 列表获取
     * @param termInfo termInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<TermBooks> pageList(TermBooks termInfo, int pageIndex, int pageSize);

    /***
     * 详细
     * @param id id
     * @return TermBooks
     */
    TermBooks findById(int id);

    /***
     * 添加
     * @param termInfo termInfo
     */
    void insert(TermBooks termInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<TermBooks> list);

}
