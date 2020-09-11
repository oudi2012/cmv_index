package com.ddky.fms.refund.mapper.admin;

import com.ddky.fms.refund.model.admin.entry.AdminInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * GradeInfo
 * @author oudi
 */
public interface AdminInfoMapper {

    /***
     * 列表
     * @param item item
     * @return List
     */
    List<AdminInfo> list(AdminInfo item);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    AdminInfo findById(Long id);

    /***
     * 详细
     * @param item
     * @return
     */
    AdminInfo findOne(AdminInfo item);

    /***
     * 列表
     * @param list list
     * @return List
     */
    List<AdminInfo> listByUserNameList(@Param("list") List<String> list);

    /***
     * 编辑
     * @param item item
     * @return int
     */
    int edit(AdminInfo item);

    /***
     * 添加
     * @param item item
     */
    void insert(AdminInfo item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<AdminInfo> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(@Param("idList") List<Long> idList);
}
