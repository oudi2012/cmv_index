package com.ddky.fms.refund.mapper.images;

import com.ddky.fms.refund.model.images.entry.ImageCate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/***
 * ClassInfo
 * @author oudi
 */
public interface ImageCateMapper {

    /***
     * 列表
     * @return List
     */
    List<ImageCate> list();

    /***
     * 详细
     * @param id id
     * @return obj
     */
    ImageCate findById(int id);

    /***
     * 添加
     * @param item item
     */
    void insert(ImageCate item);

    /***
     * 批量添加
     * @param list list
     */
    void batchInsert(@Param("list") List<ImageCate> list);
}
