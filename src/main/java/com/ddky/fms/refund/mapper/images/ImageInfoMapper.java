package com.ddky.fms.refund.mapper.images;

import com.ddky.fms.refund.model.images.entry.ImageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * ClassInfo
 * @author oudi
 */
public interface ImageInfoMapper {

    /***
     * 列表
     * @return List
     */
    List<ImageInfo> list();

    /***
     * 详细
     * @param id id
     * @return obj
     */
    ImageInfo findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(ImageInfo item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list") List<ImageInfo> list);
}
