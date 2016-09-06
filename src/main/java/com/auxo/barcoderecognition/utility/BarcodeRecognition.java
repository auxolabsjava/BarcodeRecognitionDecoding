package com.auxo.barcoderecognition.utility;

import com.auxo.barcoderecognition.utility.utilityhelpers.LeptonicaImpl;
import net.sourceforge.lept4j.Leptonica;
import net.sourceforge.lept4j.Leptonica1;
import net.sourceforge.lept4j.Pix;
import net.sourceforge.lept4j.Pixa;
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

    public int extractBarcodes(String imageFilePath, String imageSourcePath)
    {
        Pix sourceImage, pix1, pix2, pix3, pix4, pix5;
        Pix pixg, pix6, pix7, pix8, pix9;
        lept.SARRAY saw1, saw2, saw3, sad1, sad2, sad3;
        Pixa pixa;

        saw1 = null;
        File image = new File(imageFilePath);

        leptonica = new LeptonicaImpl().getInstance();

        if ((sourceImage = leptonica.pixRead(image.getPath())) == null)
        {
            System.err.print("Image data not available");
        }
        leptonica.pixWrite( imageSourcePath + "/"+ "source.png", sourceImage, IFF_PNG);
        leptonica.pixDisplayWrite(sourceImage, 1);

        //sad1 = pixProcessBarcodes(sourceImage, L_BF_ANY, L_USE_WIDTHS, &saw1, 0);

        if ( (pixGetDepth(sourceImage) == 8) ) //&& !pixGetColormap(sourceImage))
        {
            pixg = pixClone(sourceImage);
        }
        else
            pixg = pixConvertTo8(sourceImage, 0);

        int countBar = 0;
        pixa = pixExtractBarcodes(pixg, countBar);


        return barcodeCount;
    }

}
