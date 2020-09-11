package com.ddky.fms.refund.service.admin;


import com.ddky.fms.refund.model.admin.entry.AdminInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * AdminInfoService
 * @author a
 */
public interface AdminInfoService {

    /***
     * 列表
     * @param adminInfo adminInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<AdminInfo> list(AdminInfo adminInfo, int pageIndex, int pageSize);

    /***
     * 用户名获取用户
     * @param userName userName
     * @return
     */
    AdminInfo findByUserName(String userName);

    /***
     * 详细
     * @param id areaCode
     * @return SchoolInfo
     */
    AdminInfo findById(Long id);

    /***
     * 列表
     * @param list list
     * @return List
     */
    List<AdminInfo> listByUserNameList(List<String> list);

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
    void batchInsert(List<AdminInfo> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(List<Long> idList);

}
