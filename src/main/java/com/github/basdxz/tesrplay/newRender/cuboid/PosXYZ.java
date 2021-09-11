package com.github.basdxz.tesrplay.newRender.cuboid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import static com.github.basdxz.tesrplay.Reference.*;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PosXYZ {
    private double x;
    private double y;
    private double z;

    public PosXYZ set(double... xyz) {
        if (xyz.length != 3)
            throw new IllegalArgumentException("PosXYZ Set method expects array length 3");
        x = xyz[X];
        y = xyz[Y];
        z = xyz[Z];
        return this;
    }

    public void add(PosXYZ that) {
        x += that.x;
        y += that.y;
        z += that.z;
    }
}
