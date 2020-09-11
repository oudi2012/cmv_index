package com.ddky.fms.refund.mapper.term;

import com.ddky.fms.refund.model.term.entry.TermInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * TermInfo
 * @author oudi
 */
public interface TermInfoMapper {

    /***
     * 列表
     * @param termInfo termInfo
     * @return List
     */
    List<TermInfo> list(TermInfo termInfo);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    TermInfo findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(TermInfo item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<TermInfo> list);
}
