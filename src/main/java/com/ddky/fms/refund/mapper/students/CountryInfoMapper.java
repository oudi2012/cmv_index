package com.ddky.fms.refund.mapper.students;

import com.ddky.fms.refund.model.students.entry.CountryInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * areaInfo
 * @author oudi
 */
public interface CountryInfoMapper {

    /***
     * 列表
     * @return List
     */
    List<CountryInfo> list();

    /***
     * 详细
     * @param code code
     * @return CountryInfo
     */
    CountryInfo findByCode(int code);

    /***
     * 添加
     * @param info info
     */
    void insert(CountryInfo info);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list")List<CountryInfo> list);
}
