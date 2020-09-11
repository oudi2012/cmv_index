package com.ddky.fms.refund.mapper.students;

import com.ddky.fms.refund.model.students.entry.GuardianInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * GradeInfo
 * @author oudi
 */
public interface GuardianInfoMapper {

    /***
     * 列表
     * @param item item
     * @return List
     */
    List<GuardianInfo> list(GuardianInfo item);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    GuardianInfo findById(Long id);

    /***
     * 详细
     * @param item
     * @return
     */
    GuardianInfo findOne(GuardianInfo item);

    /***
     * 列表
     * @param list list
     * @return list
     */
    List<GuardianInfo> listByPhoneList(@Param("list") List<String> list);

    /***
     * 列表
     * @param list list
     * @return List
     */
    List<GuardianInfo> listByUserNameList(@Param("list") List<String> list);

    /***
     * 编辑
     * @param item item
     * @return int
     */
    int edit(GuardianInfo item);

    /***
     * 添加
     * @param item item
     */
    void insert(GuardianInfo item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<GuardianInfo> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(@Param("idList") List<Long> idList);
}
