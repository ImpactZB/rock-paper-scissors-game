package com.zbockowski.rockpaperscissorsgame.util;

import com.zbockowski.rockpaperscissorsgame.data.dto.Shape;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomShapeGenerator {

    final Random random = new Random();

    public Shape drawShape(){
        int i = random.nextInt(Shape.values().length);
        return Shape.values()[i];
    }
}
