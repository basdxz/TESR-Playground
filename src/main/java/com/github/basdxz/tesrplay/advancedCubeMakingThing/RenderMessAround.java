package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.StraightGLUtil;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.*;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class RenderMessAround {
    private static final double renderMinX = 0.0D;
    private static final double renderMinY = 0.0D;
    private static final double renderMinZ = 0.0D;
    private static final double renderMaxX = 1.0D;
    private static final double renderMaxY = 1.0D;
    private static final double renderMaxZ = 1.0D;

    public void cubeDrawingButRoundAboutWay(BlendableIcon icon, double posX, double posY, double posZ) {
        StraightGLUtil.drawAndSettingWrapper(() -> {
            val bounds = new CuboidBounds(new PosXYZ(posX, posY, posZ), new PosXYZ(posX + 1, posY + 1, posZ + 1));
            ClassThatDrawsACubeLikeIWantItTo.makeCube(bounds, new SameSideAllAround());

        }, true);

    }


}
