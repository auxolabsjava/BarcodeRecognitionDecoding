package com.auxo.barcoderecognition.resource;

import com.auxo.barcoderecognition.views.SampleView;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by USER on 23/08/2016.
 */
@Path("/")
public class ViewResource {

    public ViewResource(){}

    @GET
    @Path("/barcode")
    public SampleView loginPage() {
        return new SampleView("UploadFile.ftl");
    }
}
