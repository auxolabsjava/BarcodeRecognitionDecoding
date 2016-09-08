package com.auxo.barcoderecognition.utility;

import com.auxo.barcoderecognition.utility.utilityhelpers.LeptonicaImpl;
import com.sun.jna.ptr.PointerByReference;
import net.sourceforge.lept4j.*;
import org.bytedeco.javacpp.lept;

import java.io.File;

import static net.sourceforge.lept4j.ILeptonica.IFF_PNG;
import static net.sourceforge.lept4j.ILeptonica.L_BF_ANY;
import static net.sourceforge.lept4j.ILeptonica.L_USE_WIDTHS;
import static net.sourceforge.lept4j.Leptonica1.*;

/**
 * Created by USER on 02/09/2016.
 */
public class BarcodeRecognition
{
    private int barcodeCount = 0;
    private Leptonica leptonica;
    private int imageWidth;
    private int imageHeight;

    public int extractBarcodes(String imageFilePath, String imageSourcePath)
    {
        Pix sourceImage, pix1, pix2, pix3, pix4, pix5;
        Pix pixs , grayImage, pix6, pix7, pix8, pix9;
        Pix resizeImage;
//        Sarray sad1;
//        Sarray sad2;
//        lept.SARRAY sad3;
        Pixa pixa;
//        PointerByReference saw1 = new PointerByReference();
//        PointerByReference saw2 = new PointerByReference();

        File image = new File(imageFilePath);

        leptonica = new LeptonicaImpl().getInstance();

        if ((sourceImage = leptonica.pixRead(image.getPath())) == null)
        {
            System.err.print("Image data not available");
        }
//        leptonica.pixWrite( imageSourcePath + "/"+ "source.png", sourceImage, IFF_PNG);
//        leptonica.pixDisplayWrite(sourceImage, 1);

//        sad1 = pixProcessBarcodes(sourceImage, L_BF_ANY, L_USE_WIDTHS, saw1, 0);
//        //sarrayWrite("/tmp/junksaw1", saw1);
//        sarrayWrite("/tmp/junksad1", sad1);
//
//        pixRotate180(sourceImage, pixs);
//        sad2 = pixProcessBarcodes(pixs, L_BF_ANY, L_USE_WIDTHS, saw2, 0);
//        //sarrayWrite("/tmp/junksaw2", saw2);
//        sarrayWrite("/tmp/junksad2", sad2);

        if ( (pixGetDepth(sourceImage) == 8) ) //&& !pixGetColormap(sourceImage))
        {
            grayImage = pixClone(sourceImage);
        }
        else
            grayImage = pixConvertTo8(sourceImage, 0);

//        leptonica.pixWrite( imageSourcePath + "/"+ "8bit.png", grayImage, IFF_PNG);
//        leptonica.pixDisplayWrite(grayImage, 1);

        imageWidth = pixGetWidth(grayImage);
        imageHeight = pixGetHeight(grayImage);

        if ( imageHeight > 1650 && imageWidth > 1275 )
            resizeImage = pixCreateHeader(1275,1650,8);
        else
            resizeImage = grayImage;
        leptonica.pixWrite( imageSourcePath + "/"+ "resize.png", resizeImage, IFF_PNG);

        // Apply Erotion
        //Pix erodeImage = pixErodeGray(resizeImage, 3, 3);
        //leptonica.pixWrite( imageSourcePath + "/"+ "erode.png", erodeImage, IFF_PNG);

        Pix erodeImage = pixMorphCompSequence(resizeImage,"o25 + c1 + e1",2);
        leptonica.pixWrite(imageSourcePath + "/" + "oce.png", erodeImage, IFF_PNG);

        return barcodeCount;
    }

}
