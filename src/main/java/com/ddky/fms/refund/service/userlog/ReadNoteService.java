package com.ddky.fms.refund.service.userlog;

import com.ddky.fms.refund.model.userlogs.entry.ReadNote;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * ReadNote
 * @author a
 */
public interface ReadNoteService {

    /***
     * 列表获取
     * @param termInfo termInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<ReadNote> list(ReadNote termInfo, int pageIndex, int pageSize);

    /***
     * 详细
     * @param id id
     * @return ReadNote
     */
    ReadNote findById(int id);

    /***
     * 添加
     * @param termInfo termInfo
     */
    void insert(ReadNote termInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<ReadNote> list);

}
