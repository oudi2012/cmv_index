package com.ddky.fms.refund.service.impl.image;

import com.ddky.fms.refund.service.image.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;


/**
 * @author a
 */
@Service
public class ImageServiceImpl implements ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageServiceImpl.class);

    @Override
    public BufferedImage readImage(File file) throws IOException {
        return readImage(ImageIO.createImageInputStream(file));
    }

    @Override
    public BufferedImage readImage(InputStream stream) throws IOException {
        return readImage(ImageIO.createImageInputStream(stream));
    }

    @Override
    public BufferedImage readImage(ImageInputStream input) throws IOException {
        Iterator<?> readers = ImageIO.getImageReaders(input);
        if(readers == null || !readers.hasNext()) {
            return null;
        }
        ImageReader reader = (ImageReader) readers.next();
        reader.setInput(input);
        BufferedImage image;
        try {
            // 尝试读取图片 (包括颜色的转换).//RGB
            image = reader.read(0);
        } catch (IIOException e) {
            // 读取Raster (没有颜色的转换). //CMYK
            Raster raster = reader.readRaster(0, null);
            image = createJpeg4(raster);
        }
        return image;
    }

    private BufferedImage createJpeg4(Raster raster) {
        int w = raster.getWidth();
        int h = raster.getHeight();
        byte[] rgb = new byte[w * h * 3];
        //彩色空间转换
        float[] yellow = raster.getSamples(0, 0, w, h, 0, (float[]) null);
        float[] blue = raster.getSamples(0, 0, w, h, 1, (float[]) null);
        float[] red = raster.getSamples(0, 0, w, h, 2, (float[]) null);
        float[] key = raster.getSamples(0, 0, w, h, 3, (float[]) null);
        for (int i = 0, imax = yellow.length, base = 0; i < imax; i++, base += 3) {
            float k = 220 - key[i], y = 255 - yellow[i], cb = 255 - blue[i],
                    cr = 255 - red[i];
            double val = y + 1.402 * (cr - 128) - k;
            val = (val - 128) * .65f + 128;
            rgb[base] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
                    : (byte) (val + 0.5);
            val = y - 0.34414 * (cb - 128) - 0.71414 * (cr - 128) - k;
            val = (val - 128) * .65f + 128;
            rgb[base + 1] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
                    : (byte) (val + 0.5);
            val = y + 1.772 * (cb - 128) - k;
            val = (val - 128) * .65f + 128;
            rgb[base + 2] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
                    : (byte) (val + 0.5);
        }
        raster = Raster.createInterleavedRaster(new DataBufferByte(rgb, rgb.length), w, h, w * 3, 3, new int[]{0, 1, 2}, null);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
        ColorModel cm = new ComponentColorModel(cs, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        return new BufferedImage(cm, (WritableRaster) raster, true, null);
    }
}
