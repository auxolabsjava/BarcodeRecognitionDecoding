package com.auxo.barcoderecognition.application;

import com.auxo.barcoderecognition.resource.ImageResource;
import com.auxo.barcoderecognition.resource.ViewResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 * Created by USER on 02/09/2016.
 */
public class BarcodeRecognitionApplication extends Application<BarcodeRecognitionConfig>
{
    public static void main(String[] args) throws Exception {
        new BarcodeRecognitionApplication().run(args);
    }

    public void run(BarcodeRecognitionConfig barcodeRecognitionConfig, Environment environment) throws Exception
    {

        final ViewResource viewResource = new ViewResource();
        environment.jersey().register(viewResource);

        final ImageResource imageResource = new ImageResource(barcodeRecognitionConfig);
        environment.jersey().register(imageResource);

        environment.jersey().register(MultiPartFeature.class);
    }

    @Override
    public void initialize(Bootstrap<BarcodeRecognitionConfig> bootstrap) {
        bootstrap.addBundle(new ViewBundle<BarcodeRecognitionConfig>());
    }
}
