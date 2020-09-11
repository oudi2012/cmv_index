package com.ddky.fms.refund.service.exam;

import com.ddky.fms.refund.model.exam.entry.ExamBean;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * @author a
 * @param <T>
 */
public interface ExamService<T extends ExamBean> {

    /***
     * 列表
     * @param t
     * @return
     */
    List<T> list(T t);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    T findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(T item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<T> list);

    /***
     * 编辑
     * @param item item
     */
    void update(T item);

    /***
     * 分页显示
     * @param t t
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return PageInfo
     */
    PageInfo<T> pageList(T t, int pageIndex, int pageSize);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(List<Long> idList);

}
