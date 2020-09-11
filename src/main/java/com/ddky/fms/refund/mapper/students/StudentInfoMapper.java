package com.ddky.fms.refund.mapper.students;

import com.ddky.fms.refund.model.students.entry.GuardianInfo;
import com.ddky.fms.refund.model.students.entry.StudentInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * GradeInfo
 * @author oudi
 */
public interface StudentInfoMapper {

    /***
     * 列表
     * @param item item
     * @return List
     */
    List<StudentInfo> list(StudentInfo item);

    /***
     * 详细
     * @param id id
     * @return obj
     */
    StudentInfo findById(Long id);

    /***
     * 详细
     * @param item
     * @return
     */
    StudentInfo findOne(StudentInfo item);

    /***
     * 列表
     * @param list list
     * @return list
     */
    List<StudentInfo> listByPhoneList(@Param("list") List<String> list);

    /***
     * 列表
     * @param list list
     * @return List
     */
    List<StudentInfo> listByUserNameList(@Param("list") List<String> list);

    /***
     * 编辑
     * @param item item
     * @return int
     */
    int edit(StudentInfo item);

    /***
     * 添加
     * @param item item
     */
    void insert(StudentInfo item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<StudentInfo> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(@Param("idList") List<Long> idList);
}
