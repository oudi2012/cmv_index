package com.ddky.fms.refund.mapper.books;

import com.ddky.fms.refund.model.books.entry.SubjectInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * SubjectInfo
 * @author oudi
 */
public interface SubjectInfoMapper {

    /***
     * 列表
     * @return List
     */
    List<SubjectInfo> list();

    /***
     * 详细
     * @param id id
     * @return obj
     */
    SubjectInfo findById(int id);

    /***
     * 列表
     * @param listIds listIds
     * @return obj
     */
    List<SubjectInfo> findByIds(@Param("listIds")List<Integer> listIds);

    /***
     * 详细
     * @param name name
     * @return obj
     */
    SubjectInfo findByName(String name);

    /***
     * 添加
     * @param item item
     */
    void insert(SubjectInfo item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<SubjectInfo> list);

    /***
     * 更新
     * @param item item
     */
    void update(SubjectInfo item);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(@Param("idList")List<Long> idList);
}
