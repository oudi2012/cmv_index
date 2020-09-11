package com.ddky.fms.refund.mapper.students;

import com.ddky.fms.refund.model.students.entry.SchoolInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * areaInfo
 * @author oudi
 */
public interface SchoolInfoMapper {

    /***
     * 列表
     * @param schoolInfo schoolInfo
     * @return List
     */
    List<SchoolInfo> list(SchoolInfo schoolInfo);

    /***
     * 详细
     * @param id areaCode
     * @return SchoolInfo
     */
    SchoolInfo findById(Long id);

    /***
     * 详细
     * @param ids ids
     * @return SchoolInfo
     */
    List<SchoolInfo> findByIds(@Param("idList")List<Long> ids);

    /***
     * 添加
     * @param item item
     */
    void insert(SchoolInfo item);

    /***
     * 编辑
     * @param item item
     */
    void update(SchoolInfo item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<SchoolInfo> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(@Param("idList")List<Long> idList);
}
