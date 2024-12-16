package com.example.springAI.mxnet_inference;

import ai.djl.Application;
import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * An example of inference using an object detection model.
 *
 * <p>
 * See this <a
 * href=
 * "https://github.com/awslabs/djl/blob/master/examples/docs/object_detection.md">doc
 * for
 * information about this example.
 */
public final class ObjectDetection {

    private static final Logger logger = LoggerFactory.getLogger(ObjectDetection.class);

    private ObjectDetection() {
    }

    public static void main(String[] args) throws IOException, ModelException, TranslateException {
        DetectedObjects detection = ObjectDetection.predict();
        logger.info("{}", detection);
    }

    public static DetectedObjects predict() throws IOException, ModelException, TranslateException {
        // Path imageFile = Paths.get("src/test/resources/dog_bike_car.jpg");
        // Path imageFile = Paths.get("src/test/resources/car.jpg");
        Path imageFile = Paths.get("src/main/resources/dogcat.jpg");
        Image img = ImageFactory.getInstance().fromFile(imageFile);

        Criteria<Image, DetectedObjects> criteria = Criteria.builder()
                .optApplication(Application.CV.OBJECT_DETECTION)
                .setTypes(Image.class, DetectedObjects.class)
                .optFilter("backbone", "resnet50")
                .optProgress(new ProgressBar())
                .build();

        try (ZooModel<Image, DetectedObjects> model = ModelZoo.loadModel(criteria)) {
            try (Predictor<Image, DetectedObjects> predictor = model.newPredictor()) {
                DetectedObjects detection = predictor.predict(img);
                saveBoundingBoxImage(img, detection);
                return detection;
            }
        }
    }

    private static void saveBoundingBoxImage(Image img, DetectedObjects detection)
            throws IOException {
        Path outputDir = Paths.get("build/output");
        Files.createDirectories(outputDir);

        // Make image copy with alpha channel because original image was jpg
        // Image newImage = img.duplicate(Image.Type.TYPE_INT_ARGB);
        // newImage.drawBoundingBoxes(detection);

        // Convert the image to a BufferedImage with an alpha channel
        BufferedImage bufferedImage = (BufferedImage) img.getWrappedImage();
        BufferedImage newImage = new BufferedImage(
                bufferedImage.getWidth(),
                bufferedImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);

        // Draw the original image onto the new image
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();

        // Use DJL's drawBoundingBoxes to draw the bounding boxes
        Image djlImage = ImageFactory.getInstance().fromImage(newImage);
        djlImage.drawBoundingBoxes(detection);

        Path imagePath = outputDir.resolve("detected-objects.png");
        // OpenJDK can't save jpg with alpha channel
        djlImage.save(Files.newOutputStream(imagePath), "png");
        logger.info("Detected objects image has been saved in: {}", imagePath);
    }
}
