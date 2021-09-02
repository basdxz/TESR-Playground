package com.github.basdxz.tesrplay.advancedCubeMakingThing;

import com.github.basdxz.tesrplay.advancedCubeMakingThing.GLHelp.StraightGLUtil;
import com.github.basdxz.tesrplay.advancedCubeMakingThing.components.*;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class RenderMessAround {
    public void cubeDrawingButRoundAboutWay(BlendableIcon icon, double posX, double posY, double posZ) {
        StraightGLUtil.drawAndSettingWrapper(() -> {
            val posXYZ = new PosXYZ(posX, posY, posZ);
            val bounds = new CuboidBounds(new PosXYZ(0.5, 0.5, 0.5), new PosXYZ(1, 1, 1));
            //val bounds = new CuboidBounds(new PosXYZ(0, 0, 0), new PosXYZ(1, 1, 1));
            //val bounds = new CuboidBounds(new PosXYZ(0, 0, 0), new PosXYZ(0.5, 0.5, 0.5));
            ClassThatDrawsACubeLikeIWantItTo.makeCube(posXYZ, bounds, new SameSideAllAround());
        }, true);
    }
}
