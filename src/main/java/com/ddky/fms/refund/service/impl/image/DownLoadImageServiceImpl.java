package com.ddky.fms.refund.service.impl.image;

import com.ddky.fms.refund.service.image.DownLoadImageService;
import com.ddky.fms.refund.service.image.ImageService;
import com.ddky.fms.refund.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/***
 * 下载网络图片
 * @author a
 */
@Service
public class DownLoadImageServiceImpl implements DownLoadImageService {

    private static final Logger logger = LoggerFactory.getLogger(DownLoadImageServiceImpl.class);

    private static final String CHAR_DOT = ".";
    private static final String CHAR_ASK = "?";
    private static final String CHAR_SPLIT = "/";

    @Autowired
    private ImageService imageService;

    @Override
    public void download(String url, String savePath) {
        logger.info("download {} \n\t\t\t savePath:{}", url, savePath);
        downLoadItem(url, savePath);
        logger.info("download {} 结束", url);
    }

    private void downLoadItem(String strUrl, String savePath) {
        try {
            URL url = new URL(strUrl);
            URLConnection urlConnection = url.openConnection();
            HttpURLConnection httpUrlConnection =(HttpURLConnection)urlConnection;
            httpUrlConnection.connect();
            if (httpUrlConnection.getResponseCode()== HttpURLConnection.HTTP_OK){
                InputStream inputStream= httpUrlConnection.getInputStream();
                BufferedImage bufferedImage = imageService.readImage(inputStream);
                String[] imageExt = getImageNameExt(strUrl);
                if (imageExt != null && !StringUtils.isEmpty(imageExt[1])) {
                    String dirPath = savePath.replace(imageExt[0] + "." + imageExt[1], "");
                    File dir = new File(dirPath);
                    if (!dir.exists()) {
                        if (dir.mkdirs()) {
                            File file = new File(dirPath, imageExt[0] + "." + imageExt[1]);
                            if (!file.exists() && file.createNewFile()) {
                                ImageIO.write(bufferedImage, imageExt[1],file);
                            }
                        }
                    }
                }
            }else {
                logger.info("连接失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] getImageNameExt(String strUrl) {
        if (StringUtils.isEmpty(strUrl) || !strUrl.contains(CHAR_DOT)) {
            return null;
        }
        String name = strUrl.substring(strUrl.lastIndexOf(CHAR_SPLIT) + 1, strUrl.lastIndexOf(CHAR_DOT));
        String ext = strUrl.substring(strUrl.lastIndexOf(CHAR_DOT) + 1);
        if (ext.contains(CHAR_ASK)) {
            ext = ext.substring(0, ext.indexOf(CHAR_ASK));
        }
        return new String[]{name, ext};
    }
}
