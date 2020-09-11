package com.ddky.fms.refund.service.student;


import com.ddky.fms.refund.model.students.entry.UserInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/***
 * 用户接口
 * @author oudi
 */
public interface UserInfoService<T extends UserInfo> {

    /***
     * 列表
     * @param t t
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return
     */
    PageInfo<T> list(T t, int pageIndex, int pageSize);

    /***
     * 手机号获取用户
     * @param phone phone
     * @return
     */
    T findByPhone(String phone);

    /***
     * 用户名获取用户
     * @param userName userName
     * @return
     */
    T findByUserName(String userName);

    /***
     * 详细
     * @param id areaCode
     * @return SchoolInfo
     */
    T findById(Long id);

    /***
     * 列表
     * @param list list
     * @return list
     */
    List<T> listByPhoneList(List<String> list);

    /***
     * 列表
     * @param list list
     * @return List
     */
    List<T> listByUserNameList(List<String> list);

    /***
     * 编辑
     * @param item item
     * @return int
     */
    int edit(T item);

    /***
     * 添加
     * @param item item
     */
    void insert(T item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<T> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(List<Long> idList);

}
