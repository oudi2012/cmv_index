package com.ddky.fms.refund.mapper.userlogs;

import com.ddky.fms.refund.model.userlogs.entry.BookShelf;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * ClassInfo
 * @author oudi
 */
public interface BookShelfMapper {

    /***
     * 列表
     * @param logInfo
     * @return
     */
    List<BookShelf> list(BookShelf logInfo);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    BookShelf findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(BookShelf item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<BookShelf> list);
}
