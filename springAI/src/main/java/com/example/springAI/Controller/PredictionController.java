package com.example.springAI.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.springAI.Service.TensorFlowService;

@RestController
public class PredictionController {

    @Autowired
    private TensorFlowService tensorFlowService;

    @GetMapping("/predict")
    public float predict(@RequestParam float input) {
        return tensorFlowService.predict(input);
    }
}
