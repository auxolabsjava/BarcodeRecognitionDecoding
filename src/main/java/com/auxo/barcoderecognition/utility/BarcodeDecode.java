package com.auxo.barcoderecognition.utility;

/**
 * Created by USER on 02/09/2016.
 */
import com.google.zxing.*;
import com.google.zxing.Reader;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

/**
 * Created by USER on 25/08/2016.
 */
public class BarcodeDecode
{
    /**
     * @param
     */
    public Result barcodeDecode(String imageFilepath, String imageSourcePath) throws IOException, FormatException, ChecksumException, NotFoundException
    {
        // Remove Lines
        //LineRemoval lineRemoval = new LineRemoval();
        //lineRemoval.lineRemovalProcess(imageFilepath, imageSourcePath);

        InputStream barCodeInputStream = new FileInputStream(imageFilepath);
        BufferedImage barCodeBufferedImage = ImageIO.read(barCodeInputStream);

        LuminanceSource source = new BufferedImageLuminanceSource(barCodeBufferedImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Reader readerBarcode = new MultiFormatReader();
        Result barcodeValueType = readerBarcode.decode(bitmap);
        ResultPoint[] point = barcodeValueType.getResultPoints();
        return barcodeValueType;//result.getText();

    }

}
