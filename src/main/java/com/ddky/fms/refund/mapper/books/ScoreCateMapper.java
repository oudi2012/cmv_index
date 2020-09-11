package com.ddky.fms.refund.mapper.books;

import com.ddky.fms.refund.model.books.entry.ScoreCate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * ClassInfo
 * @author oudi
 */
public interface ScoreCateMapper  {

    /***
     * 列表
     * @return List
     */
    List<ScoreCate> list();

    /***
     * 详细
     * @param id id
     * @return obj
     */
    ScoreCate findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(ScoreCate item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list") List<ScoreCate> list);
}
