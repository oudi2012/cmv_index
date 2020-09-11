package com.ddky.fms.refund.mapper.userlogs;

import com.ddky.fms.refund.model.userlogs.entry.HomeWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * ClassInfo
 * @author oudi
 */
public interface HomeWorkMapper {

    /***
     * 列表
     * @param info info
     * @return
     */
    List<HomeWork> list(HomeWork info);

    /***
     * 详细
     * @param homeWorkId id
     * @return obj
     */
    HomeWork findById(int homeWorkId);

    /***
     * 添加
     * @param item item
     */
    void insert(HomeWork item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<HomeWork> list);
}
