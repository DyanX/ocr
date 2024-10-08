package io.github.mymonstercat.controller;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
       /* String base64String = "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";
        String filePath = "D:\\output.png";
        saveImage(base64String, filePath);*/
        try {
            // 读取图片
            File input = new File("D:\\workspace\\rapidocr-demo-main\\spring-boot\\target\\classes\\images\\100.png");
            BufferedImage image = ImageIO.read(input);

            // 创建一个新的BufferedImage，设置抗锯齿
            BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = result.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(image, 0, 0, null);
            g2d.dispose();

            // 保存处理后的图片
            File output = new File("D:\\workspace\\rapidocr-demo-main\\spring-boot\\target\\classes\\images\\100y.png");
            ImageIO.write(result, "jpg", output);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void saveImage(String base64ImageString, String path) {
        byte[] imageBytes = Base64.getDecoder().decode(base64ImageString);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
            BufferedImage image = ImageIO.read(bis);
            bis.close();
            File outputFile = new File(path);
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
