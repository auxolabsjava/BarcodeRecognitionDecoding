package com.auxo.barcoderecognition.resource.resourcehelpers;

import com.auxo.barcoderecognition.utility.BarcodeDecode;
import com.auxo.barcoderecognition.utility.BarcodeRecognition;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;

import java.io.*;

/**
 * Created by USER on 02/09/2016.
 */
public class ImageResourceHelpers {

    private String barcodeValue;
    private Result barcodeValueType;
    private int barcodeCont;
    // save uploaded file to new location
    public void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) throws IOException {
        int read;
        final int BUFFER_LENGTH = 1024;
        final byte[] buffer = new byte[BUFFER_LENGTH];
        OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
        while ((read = uploadedInputStream.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
        out.flush();
        out.close();
    }

    public Result barcodeProcess(String imageFilePath, String imageSourcePath) throws FormatException, ChecksumException, NotFoundException, IOException
    {
        try
        {
            BarcodeDecode barcode = new BarcodeDecode();
            barcodeValueType = barcode.barcodeDecode(imageFilePath, imageSourcePath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (FormatException e)
        {
            e.printStackTrace();
        }
        catch (ChecksumException e)
        {
            e.printStackTrace();
        }
        catch (NotFoundException e)
        {
            e.printStackTrace();
        }
        return barcodeValueType;
    }

    public int barcodeRecognition(String imageFilePath, String imageSourcePath)
    {
        BarcodeRecognition barcodeRecognition = new BarcodeRecognition();
        barcodeCont = barcodeRecognition.extractBarcodes(imageFilePath, imageSourcePath);
        return barcodeCont;
    }
}
