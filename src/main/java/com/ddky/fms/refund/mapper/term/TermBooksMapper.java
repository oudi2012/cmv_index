package com.ddky.fms.refund.mapper.term;

import com.ddky.fms.refund.model.term.entry.TermBooks;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * TermBooks
 * @author oudi
 */
public interface TermBooksMapper {

    /***
     * 列表
     * @param termInfo termInfo
     * @return List
     */
    List<TermBooks> list(TermBooks termInfo);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    TermBooks findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(TermBooks item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<TermBooks> list);
}
