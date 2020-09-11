package com.ddky.fms.refund.service;

import com.ddky.fms.refund.model.books.entry.VersionType;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * 版本类型
 * @author oudi
 */
public interface VersionTypeService {

    /***
     * 分页显示
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return PageInfo
     */
    PageInfo<VersionType> pageList(int pageIndex, int pageSize);

    /***
     * 列表获取
     * @return list
     */
    List<VersionType> list();

    /***
     * 详细
     * @param id id
     * @return GradeInfo
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
    void batchInsert(List<VersionType> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(List<Long> idList);

}
