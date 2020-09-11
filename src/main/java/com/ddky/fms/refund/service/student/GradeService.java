package com.ddky.fms.refund.service.student;

import com.ddky.fms.refund.model.students.entry.GradeInfo;

import java.util.List;
import java.util.Map;

/***
 * 年级接口
 * @author oudi
 */
public interface GradeService {

    /***
     * 列表获取
     * @return list
     */
    List<GradeInfo> list();

    /***
     * 详细
     * @param id id
     * @return GradeInfo
     */
    GradeInfo findById(int id);

    /***
     * 列表获取
     * @param ids ids
     * @return GradeInfo
     */
    List<GradeInfo> findByIds(List<Integer> ids);

    /***
     * 列表获取
     * @param ids ids
     * @return GradeInfo
     */
    Map<Integer, GradeInfo> findMapByIds(List<Integer> ids);

    /***
     * 添加
     * @param itemInfo itemInfo
     */
    void insert(GradeInfo itemInfo);

    /***
     * 更新
     * @param itemInfo itemInfo
     */
    void update(GradeInfo itemInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<GradeInfo> list);

    /***
     * 删除
     * @param id id
     */
    void delete(int id);

}
