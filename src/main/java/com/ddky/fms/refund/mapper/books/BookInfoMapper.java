package com.ddky.fms.refund.mapper.books;


import com.ddky.fms.refund.model.books.entry.BookInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/***
 * @author a
 */
public interface BookInfoMapper {

    /***
     * 列表
     * @param t t
     * @return List
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
     * 添加
     * @param item item
     */
    void update(BookInfo item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<BookInfo> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(@Param("idList") List<Long> idList);
}
