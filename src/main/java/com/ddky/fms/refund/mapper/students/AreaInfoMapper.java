package com.ddky.fms.refund.mapper.students;

import com.ddky.fms.refund.model.students.entry.AreaInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * areaInfo
 * @author oudi
 */
public interface AreaInfoMapper {

    /***
     * 列表获取
     * @param areaInfo areaInfo
     * @return list
     */
    List<AreaInfo> list(AreaInfo areaInfo);

    /***
     * 根据名称获取各级信息
     * @param provinceName provinceName
     * @param cityName cityName
     * @param townName townName
     * @return
     */
    List<AreaInfo> listByNames(@Param("provinceName") String provinceName, @Param("cityName")String cityName, @Param("townName")String townName);

    /***
     * 详细
     * @param areaCode areaCode
     * @return AreaInfo
     */
    AreaInfo findByCode(int areaCode);

    /***
     * 批量详细
     * @param areaCodeList areaCodeList
     * @return AreaInfo
     */
    List<AreaInfo> findByCodeList(@Param("areaCodeList") List<Integer> areaCodeList);

    /***
     * 获取该父节点下最大的子节点
     * @param parentCode parentCode
     * @return Integer
     */
    Integer findMaxNodeByParentCode(int parentCode);

    /***
     * 添加
     * @param areaInfo areaInfo
     */
    void insert(AreaInfo areaInfo);

    /***
     * 更新
     * @param areaInfo areaInfo
     */
    void update(AreaInfo areaInfo);

    /***
     * 删除
     * @param areaCode areaCode
     */
    void delete(int areaCode);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<AreaInfo> list);
}
