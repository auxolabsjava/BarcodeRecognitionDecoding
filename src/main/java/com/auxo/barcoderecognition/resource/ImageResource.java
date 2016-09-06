package com.auxo.barcoderecognition.resource;

import com.auxo.barcoderecognition.application.BarcodeRecognitionConfig;

import com.auxo.barcoderecognition.resource.resourcehelpers.ImageResourceHelpers;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by USER on 02/09/2016.
 */
@Path("/")
public class ImageResource {
    private BarcodeRecognitionConfig configBarcode;
    private String barcodeValue;
    private Result barcodeValueType;

    public ImageResource(BarcodeRecognitionConfig barcodeRecognitionConfig)
    {
        configBarcode = barcodeRecognitionConfig;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/file-uploader")
    public Response uploadFile(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws MessagingException, IOException, FormatException, ChecksumException, NotFoundException
    {

        String uploadedFilePath = configBarcode.getResourceDirectoryPath() + "/" + fileDetail.getFileName();

        ImageResourceHelpers imageResourceHelpers = new ImageResourceHelpers();
        imageResourceHelpers.writeToFile(uploadedInputStream, uploadedFilePath);

        // Extract barcodes from image.
        int barcodeCount = imageResourceHelpers.barcodeRecognition(uploadedFilePath, configBarcode.getResourceDirectoryPath());

        // Barcode decoding
        //barcodeValueType = imageResourceHelpers.barcodeProcess(uploadedFilePath,configBarcode.getResourceDirectoryPath());

        return Response.ok(uploadedFilePath).build();
//        if (barcodeValueType != null)
//            return Response.ok("Barcode Type: " + barcodeValueType.getBarcodeFormat().toString() + "  \n\nBarcode Value: " + barcodeValueType.getText()).build();
//        else
//            return Response.ok("Barcode Not Found...").build();
    }
}
