package com.example.springAI.Service;

import org.springframework.stereotype.Service;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;

@Service
public class TensorFlowService {
    private SavedModelBundle model;

    public TensorFlowService() {
        try {
            // Load the model
            // model = SavedModelBundle.load("E:/springAI/linear_model", "serve");
        } catch (Exception e) {
            System.out.println(
                    "Errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr loading model: "
                            + e.getMessage());
            throw new RuntimeException("Failed to load TensorFlow model: " + e.getMessage(), e);
        }
    }

    public float predict(float input) {
        try (Tensor<Float> inputTensor = Tensor.create(new float[][] { { input } }, Float.class);
                Tensor<?> result = model.session().runner()
                        .feed("dense_input", inputTensor)
                        .fetch("dense/BiasAdd")
                        .run()
                        .get(0)) {
            return result.copyTo(new float[1])[0];
        }
    }
}
