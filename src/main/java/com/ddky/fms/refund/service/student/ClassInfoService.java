package com.ddky.fms.refund.service.student;


import com.ddky.fms.refund.model.students.entry.ClassInfo;

import java.util.List;

/***
 * 班级接口
 * @author oudi
 */
public interface ClassInfoService {
    /***
     * 列表获取
     * @return list
     */
    List<ClassInfo> list();

    /***
     * 详细
     * @param id id
     * @return ClassInfo
     */
    ClassInfo findById(int id);

    /***
     * 添加
     * @param areaInfo areaInfo
     */
    void insert(ClassInfo areaInfo);

    /***
     * 更新
     * @param areaInfo areaInfo
     */
    void update(ClassInfo areaInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<ClassInfo> list);

    /***
     * 删除
     * @param id id
     */
    void delete(int id);
}
