package com.ddky.fms.refund.service.image;

/**
 * 网络图片下载
 * @author a
 */
public interface DownLoadImageService {

    /***
     * 下载图片
     * @param url
     * @param savePath
     */
    void download(String url, String savePath);
}
