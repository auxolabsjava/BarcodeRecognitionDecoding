package com.auxo.barcoderecognition.utility;

import com.auxo.barcoderecognition.utility.utilityhelpers.LeptonicaImpl;
import net.sourceforge.lept4j.Leptonica;
import net.sourceforge.lept4j.Pix;

import java.io.File;

import static javax.swing.JOptionPane.showMessageDialog;
import static net.sourceforge.lept4j.ILeptonica.IFF_PNG;

/**
 * Created by USER on 03/09/2016.
 */
public class LineRemoval
{
    private float angleVal = 0;
    private float confVal = 0;
    private float deg2radVal = 0;

    Leptonica leptonica;

    LineRemoval()
    {}

    public void lineRemovalProcess(String imageFilename, String imageSourcePath)
    {
        Pix pixs, pix1, pix2, pix3, pix4, pix5;
        Pix pix6, pix7, pix8, pix9;

        File image = new File(imageFilename);
        deg2radVal = (float) (3.14159 / 180.);

        leptonica = new LeptonicaImpl().getInstance();

        if ((pixs = leptonica.pixRead(image.getPath())) == null)
        {
            System.err.print("Image data not available");
        }

        /* Apply threshold to binary image to extracting lines */
        pix1 = leptonica.pixThresholdToBinary(pixs, 170);
        leptonica.pixWrite( imageSourcePath + "/"+ "threshold.png", pix1, IFF_PNG);
        leptonica.pixDisplayWrite(pix1, 1);


    }
}
