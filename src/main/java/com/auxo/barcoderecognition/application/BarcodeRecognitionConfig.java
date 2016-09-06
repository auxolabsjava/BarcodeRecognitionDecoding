package com.auxo.barcoderecognition.application;

import io.dropwizard.Configuration;

/**
 * Created by USER on 02/09/2016.
 */
public class BarcodeRecognitionConfig extends Configuration
{
    public String getResourceDirectoryPath() {
        return resourceDirectoryPath;
    }

    public void setResourceDirectoryPath(String resourceDirectoryPath) {
        this.resourceDirectoryPath = resourceDirectoryPath;
    }

    private String resourceDirectoryPath;


}
