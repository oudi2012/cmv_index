package com.ddky.fms.refund.service.image;

import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/***
 * 图片处理
 * @author a
 */
public interface ImageService {

    /***
     * 读取图片
     * @param file
     * @return
     * @throws IOException
     */
    BufferedImage readImage(File file) throws IOException;

    /***
     * 读取图片
     * @param stream
     * @return
     * @throws IOException
     */
    BufferedImage readImage(InputStream stream) throws IOException;

    /***
     * 读取图片
     * @param input
     * @return
     * @throws IOException
     */
    BufferedImage readImage(ImageInputStream input) throws IOException;
}
