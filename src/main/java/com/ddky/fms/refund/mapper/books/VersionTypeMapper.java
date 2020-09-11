package com.ddky.fms.refund.mapper.books;

import com.ddky.fms.refund.model.books.entry.VersionType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * SubjectInfo
 * @author oudi
 */
public interface VersionTypeMapper {

    /***
     * 列表
     * @return List
     */
    List<VersionType> list();

    /***
     * 详细
     * @param id id
     * @return obj
     */
    VersionType findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(VersionType item);

    /***
     * 更新
     * @param item item
     */
    void update(VersionType item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list") List<VersionType> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(@Param("idList") List<Long> idList);
}
