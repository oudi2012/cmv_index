package com.ddky.fms.refund.service.userlog;

import com.ddky.fms.refund.model.userlogs.entry.BookShelf;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * @author a
 */
public interface BookShelfService {

    /***
     * 列表获取
     * @param termInfo termInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<BookShelf> list(BookShelf termInfo, int pageIndex, int pageSize);

    /***
     * 详细
     * @param id id
     * @return BookShelf
     */
    BookShelf findById(int id);

    /***
     * 添加
     * @param termInfo termInfo
     */
    void insert(BookShelf termInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<BookShelf> list);

}
