package com.ddky.fms.refund.service.student;

import com.ddky.fms.refund.model.students.entry.SchoolInfo;
import com.ddky.fms.refund.model.students.vo.SchoolInfoVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/***
 * 学校
 * @author oudi
 */
public interface SchoolInfoService {

    /***
     * 列表
     * @param schoolInfo schoolInfo
     * @param pageIndex pageIndex
     * @param pageSize pageSize
     * @return PageInfo
     */
    PageInfo<SchoolInfo> list(SchoolInfo schoolInfo, int pageIndex, int pageSize);

    /***
     * 列表
     * @param schoolInfo schoolInfo
     * @return List
     */
    List<SchoolInfo> listByParam(SchoolInfo schoolInfo);

    /***
     * 格式化单条
     * @param schoolInfo schoolInfo
     * @return List
     */
    SchoolInfoVo itemToVo(SchoolInfo schoolInfo);

    /***
     * 格式化列表
     * @param schoolInfoList schoolInfoList
     * @return List
     */
    List<SchoolInfoVo> listToVo(List<SchoolInfo> schoolInfoList);

    /***
     * 详细
     * @param id areaCode
     * @return SchoolInfo
     */
    SchoolInfo findById(Long id);

    /***
     * 详细
     * @param ids ids
     * @return list
     */
    List<SchoolInfo> findByIds(List<Long> ids);

    /***
     * 详细
     * @param ids ids
     * @return list
     */
    Map<Long, SchoolInfo> findMapByIds(List<Long> ids);

    /***
     * 详细
     * @param id id
     * @return
     */
    SchoolInfoVo findVoById(Long id);

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
    void batchInsert(List<SchoolInfo> list);

    /***
     * 删除
     * @param idList idList
     * @return int
     */
    int delete(List<Long> idList);

}
