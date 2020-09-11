package com.ddky.fms.refund.service.student;

import com.ddky.fms.refund.model.students.entry.AreaInfo;
import com.github.pagehelper.PageInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.ddky.fms.refund.constants.CmvConstants.*;

/***
 * 地区接口
 * @author oudi
 */
public interface AreaInfoService {

    /***
     * 截取区域编号
     * @param areaCode areaCode
     * @return list
     */
    default List<Integer> cutAreaCode(int areaCode) {
        String strAreaCode = String.valueOf(areaCode);
        List<Integer> areaCodeList = new ArrayList<>();
        if (areaCode/ONE_OO_OO_OO > 0) {
            areaCodeList.add(Integer.parseInt(strAreaCode.substring(0, 3)));
        }
        if (areaCode/ONE_OO_OO > 0) {
            areaCodeList.add(Integer.parseInt(strAreaCode.substring(0, 5)));
        }
        areaCodeList.add(areaCode);
        return areaCodeList;
    }

    /***
     * 省级列表
     * @return List
     */
    List<AreaInfo> listProvince();

    /***
     * 根据名称获取各级信息
     * @param provinceName provinceName
     * @param cityName cityName
     * @param townName townName
     * @return
     */
    List<AreaInfo> listByNames(String provinceName, String cityName, String townName);

    /***
     * 地级列表
     * @param provinceId provinceId
     * @return
     */
    List<AreaInfo> listCities(Integer provinceId);

    /***
     * 下级列表
     * @param parentId parentId
     * @return list
     */
    List<AreaInfo> listArea(Integer parentId);

    /***
     * 县级级列表
     * @param cityId cityId
     * @return
     */
    List<AreaInfo> listTown(Integer cityId);

    /***
     * 列表获取
     * @param areaInfo areaInfo
     * @return list
     */
    PageInfo<AreaInfo> list(AreaInfo areaInfo);

    /***
     * 更新
     * @param areaInfo areaInfo
     * @return AreaInfo
     */
    AreaInfo editByCode(AreaInfo areaInfo);

    /***
     * 获取地区路径
     * @param areaCode areaCode
     * @return list
     */
    List<AreaInfo> pathListByCode(Integer areaCode);

    /***
     * 批量获取地区路径
     * @param areaCodeList areaCodeList
     * @return list
     */
    Map<Integer, AreaInfo> pathListByCode(List<Integer> areaCodeList);


    /***
     * 获取该父节点下最大的子节点
     * @param parentCode parentCode
     * @return Integer
     */
    Integer findMaxNodeByParentCode(int parentCode);

    /***
     * 删除
     * @param areaCode areaCode
     */
    void removeByCode(int areaCode);

    /***
     * 详细
     * @param areaCode areaCode
     * @return AreaInfo
     */
    AreaInfo findByCode(int areaCode);

    /***
     * 添加
     * @param areaInfo areaInfo
     */
    void insert(AreaInfo areaInfo);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(List<AreaInfo> list);
}
