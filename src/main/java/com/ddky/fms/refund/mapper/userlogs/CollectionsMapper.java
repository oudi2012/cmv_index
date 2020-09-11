package com.ddky.fms.refund.mapper.userlogs;

import com.ddky.fms.refund.model.userlogs.entry.Collections;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * ClassInfo
 * @author oudi
 */
public interface CollectionsMapper {

    /***
     * 列表
     * @param info info
     * @return
     */
    List<Collections> list(Collections info);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    Collections findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(Collections item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<Collections> list);
}
