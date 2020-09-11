package com.ddky.fms.refund.mapper.students;

import com.ddky.fms.refund.model.students.entry.GradeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/***
 * GradeInfo
 * @author oudi
 */
public interface GradeInfoMapper {

    /***
     * 列表
     * @return List
     */
    List<GradeInfo> list();

    /***
     * 详细
     * @param id id
     * @return obj
     */
    GradeInfo findById(int id);

    /***
     * 列表
     * @param ids ids
     * @return obj
     */
    List<GradeInfo> findByIds(@Param("listIds") List<Integer> ids);

    /***
     * 添加
     * @param item item
     */
    void insert(GradeInfo item);

    /***
     * 更新
     * @param item item
     */
    void update(GradeInfo item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<GradeInfo> list);

    /***
     * 删除
     * @param id id
     */
    void delete(int id);
}
