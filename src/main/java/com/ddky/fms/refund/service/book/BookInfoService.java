package com.ddky.fms.refund.service.book;

import com.ddky.fms.refund.model.books.entry.BookInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * 课本接口
 * @author oudi
 */
public interface BookInfoService {

    /***
     * 分页显示
     * @param t t
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return PageInfo
     */
    PageInfo<BookInfo> pageList(BookInfo t, int pageIndex, int pageSize);

    /***
     * 列表
     * @param t t
     * @return
     */
    List<BookInfo> list(BookInfo t);

    /***
     * 详细
     * @param bookId id
     * @return obj
     */
    BookInfo findById(int bookId);

    /***
     * 添加
     * @param item item
     */
    void insert(BookInfo item);

    /***
     * 编辑
     * @param item item
     */
    void update(BookInfo item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<BookInfo> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(List<Long> idList);

}
