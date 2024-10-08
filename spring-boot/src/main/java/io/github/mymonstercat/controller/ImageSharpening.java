package io.github.mymonstercat.controller;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageSharpening {
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        // 读取图像
// 加载图像
        Mat image = Imgcodecs.imread("D:\\workspace\\rapidocr-demo-main\\spring-boot\\target\\classes\\images\\3.png", Imgcodecs.IMREAD_GRAYSCALE);

        if (image.empty()) {
            System.out.println("Error: Image cannot be loaded!");
            return;
        }

        // 创建输出图像
        Mat sharpenedImage = new Mat(image.size(), image.type());
        Mat dxKernel = new Mat();
        // 使用 Laplacian 滤波器进行锐化
        // dxKernel 现在包含了 x 方向的一阶导数内核
        Imgproc.getDerivKernels(dxKernel, dxKernel, 0, 1, 3, true);
        Mat laplacianFiltered = new Mat();
        Imgproc.filter2D(image, laplacianFiltered, -1, dxKernel);

        // 将原图与拉普拉斯锐化结果相加
        Core.addWeighted(image, 1.5, laplacianFiltered, -0.5, 0, sharpenedImage);

        // 保存结果图像
        Imgcodecs.imwrite("D:\\workspace\\rapidocr-demo-main\\spring-boot\\target\\classes\\images\\x3.png", sharpenedImage);

        // 释放资源
        image.release();
        sharpenedImage.release();
        laplacianFiltered.release();
        dxKernel.release();
    }
}