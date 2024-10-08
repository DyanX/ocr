package io.github.mymonstercat.controller;

import com.benjaminwan.ocrlibrary.OcrResult;
import io.github.mymonstercat.Model;
import io.github.mymonstercat.ocr.InferenceEngine;
import io.github.mymonstercat.ocr.config.ParamConfig;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * @author Monster
 */
@RestController
@RequestMapping("/ocr")
public class OcrController {

    @GetMapping()
    public String ocr() throws IOException {
        ParamConfig paramConfig = ParamConfig.getDefaultConfig();
        paramConfig.setDoAngle(true);
        paramConfig.setMostAngle(true);
        InferenceEngine engine = InferenceEngine.getInstance(Model.ONNX_PPOCR_V4);
        BufferedImage image = ImageIO.read(new File("D:\\workspace\\rapidocr-demo-main\\spring-boot\\target\\classes\\images\\100.png"));
        // 开始识别
        OcrResult ocrResult = engine.runOcr("D:\\workspace\\rapidocr-demo-main\\spring-boot\\target\\classes\\images\\100.png", paramConfig);
        return ocrResult.getStrRes().toString();
    }

    @PostMapping
    public String ocr(@RequestParam("file") MultipartFile fileUpload) throws IOException {
        ParamConfig paramConfig = ParamConfig.getDefaultConfig();
        paramConfig.setDoAngle(true);
        paramConfig.setMostAngle(true);
        InferenceEngine engine = InferenceEngine.getInstance(Model.ONNX_PPOCR_V4);
        File file = new File(System.getProperty("java.io.tmpdir") + "ocrJava/test.png");
        fileUpload.transferTo(file);
        file.deleteOnExit();

        OcrResult ocrResult = engine.runOcr(file.getPath(),paramConfig);
        return ocrResult.getStrRes().toString();
    }


    @SneakyThrows
    private static String getResourcePath(String path) {
        return Thread.currentThread().getContextClassLoader().getResource(path).getPath();
    }
}
