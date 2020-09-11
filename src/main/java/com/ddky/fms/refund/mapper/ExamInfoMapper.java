package com.ddky.fms.refund.mapper;


import com.ddky.fms.refund.model.exam.entry.ExamBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/***
 * @author a
 * @param <T>
 */
public interface ExamInfoMapper<T extends ExamBean> {

    /***
     * 列表
     * @param t t
     * @return List
     */
    List<T> list(T t);

    /***
     * 详细
     * @param bookId id
     * @return obj
     */
    T findById(int bookId);

    /***
     * 添加
     * @param item item
     */
    void insert(T item);

    /***
     * 编辑
     * @param item item
     */
    void update(T item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<T> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(@Param("idList")List<Long> idList);
}
