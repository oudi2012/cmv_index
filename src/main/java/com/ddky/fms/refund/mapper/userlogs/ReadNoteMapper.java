package com.ddky.fms.refund.mapper.userlogs;

import com.ddky.fms.refund.model.userlogs.entry.ReadNote;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * ClassInfo
 * @author oudi
 */
public interface ReadNoteMapper {

    /***
     * 列表
     * @param info info
     * @return
     */
    List<ReadNote> list(ReadNote info);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    ReadNote findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(ReadNote item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<ReadNote> list);
}
