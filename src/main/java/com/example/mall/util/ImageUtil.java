package com.example.mall.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import java.io.IOException;

public class ImageUtil {

    public static void main(String[] args) throws IOException {
//        System.out.println(3 * 0.3 == 0.9);
//        System.out.println(3 * 0.03 == 0.09);
//        System.out.println(3*0.3);
//        System.out.println(3*0.03);

        //原始图为 image/new.jpg
        String path = "image/new/";
        Thumbnails.of("image/old/old.jpg").sourceRegion(Positions.BOTTOM_RIGHT, 50, 50)
                .size(50, 50).toFile(path + "crop.jpg");

        Thumbnails.of(path + "new.jpg").scale(0.7).toFile(path + "scale1.jpg");
        Thumbnails.of(path + "new.jpg").scale(1.5).toFile(path + "scale.jpg");
        Thumbnails.of(path + "new.jpg").size(500, 500).keepAspectRatio(false).toFile(path + "size1.jpg");
        Thumbnails.of(path + "new.jpg").size(500, 500).keepAspectRatio(true).toFile(path + "size2.jpg");


        Thumbnails.of(path + "new.jpg").size(500, 500).keepAspectRatio(true).rotate(90).toFile(path + "rotate1.jpg");
        Thumbnails.of(path + "new.jpg").size(500, 500).keepAspectRatio(true).rotate(180).toFile(path + "rotate2.jpg");


    }
}
