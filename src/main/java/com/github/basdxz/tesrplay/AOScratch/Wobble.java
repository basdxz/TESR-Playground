package com.github.basdxz.tesrplay.AOScratch;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.IBlockAccess;

public class Wobble {
    private static IBlockAccess blockAccess;
    /**
     * Whether ambient occlusion is enabled or not.
     */
    private static boolean enableAO;
    /**
     * Used as a scratch variable for ambient occlusion on the north/bottom/east corner.
     */
    private static float aoLightValueScratchXYZNNN;
    /**
     * Used as a scratch variable for ambient occlusion between the bottom face and the north face.
     */
    private static float aoLightValueScratchXYNN;
    /**
     * Used as a scratch variable for ambient occlusion on the north/bottom/west corner.
     */
    private static float aoLightValueScratchXYZNNP;
    /**
     * Used as a scratch variable for ambient occlusion between the bottom face and the east face.
     */
    private static float aoLightValueScratchYZNN;
    /**
     * Used as a scratch variable for ambient occlusion between the bottom face and the west face.
     */
    private static float aoLightValueScratchYZNP;
    /**
     * Used as a scratch variable for ambient occlusion on the south/bottom/east corner.
     */
    private static float aoLightValueScratchXYZPNN;
    /**
     * Used as a scratch variable for ambient occlusion between the bottom face and the south face.
     */
    private static float aoLightValueScratchXYPN;
    /**
     * Used as a scratch variable for ambient occlusion on the south/bottom/west corner.
     */
    private static float aoLightValueScratchXYZPNP;
    /**
     * Used as a scratch variable for ambient occlusion on the north/top/east corner.
     */
    private static float aoLightValueScratchXYZNPN;
    /**
     * Used as a scratch variable for ambient occlusion between the top face and the north face.
     */
    private static float aoLightValueScratchXYNP;
    /**
     * Used as a scratch variable for ambient occlusion on the north/top/west corner.
     */
    private static float aoLightValueScratchXYZNPP;
    /**
     * Used as a scratch variable for ambient occlusion between the top face and the east face.
     */
    private static float aoLightValueScratchYZPN;
    /**
     * Used as a scratch variable for ambient occlusion on the south/top/east corner.
     */
    private static float aoLightValueScratchXYZPPN;
    /**
     * Used as a scratch variable for ambient occlusion between the top face and the south face.
     */
    private static float aoLightValueScratchXYPP;
    /**
     * Used as a scratch variable for ambient occlusion between the top face and the west face.
     */
    private static float aoLightValueScratchYZPP;
    /**
     * Used as a scratch variable for ambient occlusion on the south/top/west corner.
     */
    private static float aoLightValueScratchXYZPPP;
    /**
     * Used as a scratch variable for ambient occlusion between the north face and the east face.
     */
    private static float aoLightValueScratchXZNN;
    /**
     * Used as a scratch variable for ambient occlusion between the south face and the east face.
     */
    private static float aoLightValueScratchXZPN;
    /**
     * Used as a scratch variable for ambient occlusion between the north face and the west face.
     */
    private static float aoLightValueScratchXZNP;
    /**
     * Used as a scratch variable for ambient occlusion between the south face and the west face.
     */
    private static float aoLightValueScratchXZPP;
    /**
     * Ambient occlusion brightness XYZNNN
     */
    private static int aoBrightnessXYZNNN;
    /**
     * Ambient occlusion brightness XYNN
     */
    private static int aoBrightnessXYNN;
    /**
     * Ambient occlusion brightness XYZNNP
     */
    private static int aoBrightnessXYZNNP;
    /**
     * Ambient occlusion brightness YZNN
     */
    private static int aoBrightnessYZNN;
    /**
     * Ambient occlusion brightness YZNP
     */
    private static int aoBrightnessYZNP;
    /**
     * Ambient occlusion brightness XYZPNN
     */
    private static int aoBrightnessXYZPNN;
    /**
     * Ambient occlusion brightness XYPN
     */
    private static int aoBrightnessXYPN;
    /**
     * Ambient occlusion brightness XYZPNP
     */
    private static int aoBrightnessXYZPNP;
    /**
     * Ambient occlusion brightness XYZNPN
     */
    private static int aoBrightnessXYZNPN;
    /**
     * Ambient occlusion brightness XYNP
     */
    private static int aoBrightnessXYNP;
    /**
     * Ambient occlusion brightness XYZNPP
     */
    private static int aoBrightnessXYZNPP;
    /**
     * Ambient occlusion brightness YZPN
     */
    private static int aoBrightnessYZPN;
    /**
     * Ambient occlusion brightness XYZPPN
     */
    private static int aoBrightnessXYZPPN;
    /**
     * Ambient occlusion brightness XYPP
     */
    private static int aoBrightnessXYPP;
    /**
     * Ambient occlusion brightness YZPP
     */
    private static int aoBrightnessYZPP;
    /**
     * Ambient occlusion brightness XYZPPP
     */
    private static int aoBrightnessXYZPPP;
    /**
     * Ambient occlusion brightness XZNN
     */
    private static int aoBrightnessXZNN;
    /**
     * Ambient occlusion brightness XZPN
     */
    private static int aoBrightnessXZPN;
    /**
     * Ambient occlusion brightness XZNP
     */
    private static int aoBrightnessXZNP;
    /**
     * Ambient occlusion brightness XZPP
     */
    private static int aoBrightnessXZPP;
    /**
     * Brightness top left
     */
    private static int brightnessTopLeft;
    /**
     * Brightness bottom left
     */
    private static int brightnessBottomLeft;
    /**
     * Brightness bottom right
     */
    private static int brightnessBottomRight;
    /**
     * Brightness top right
     */
    private static int brightnessTopRight;
    /**
     * Red color value for the top left corner
     */
    private static float colorRedTopLeft;
    /**
     * Red color value for the bottom left corner
     */
    private static float colorRedBottomLeft;
    /**
     * Red color value for the bottom right corner
     */
    private static float colorRedBottomRight;
    /**
     * Red color value for the top right corner
     */
    private static float colorRedTopRight;
    /**
     * Green color value for the top left corner
     */
    private static float colorGreenTopLeft;
    /**
     * Green color value for the bottom left corner
     */
    private static float colorGreenBottomLeft;
    /**
     * Green color value for the bottom right corner
     */
    private static float colorGreenBottomRight;
    /**
     * Green color value for the top right corner
     */
    private static float colorGreenTopRight;
    /**
     * Blue color value for the top left corner
     */
    private static float colorBlueTopLeft;
    /**
     * Blue color value for the bottom left corner
     */
    private static float colorBlueBottomLeft;
    /**
     * Blue color value for the bottom right corner
     */
    private static float colorBlueBottomRight;
    /**
     * Blue color value for the top right corner
     */
    private static float colorBlueTopRight;


    private static boolean renderAllFaces;

    //x, y, z, red, green, blue (lighting)
    private static void renderStandardBlockWithAmbientOcclusion(Block block, int posX, int posY, int posZ, float aoR, float aoG, float aoB) {
        Tessellator.instance.setBrightness(983055);// what is this number even??

        aoBrightnessXYNN = block.getMixedBrightnessForBlock(blockAccess, posX - 1, posY, posZ);
        aoBrightnessYZNN = block.getMixedBrightnessForBlock(blockAccess, posX, posY, posZ - 1);
        aoBrightnessYZNP = block.getMixedBrightnessForBlock(blockAccess, posX, posY, posZ + 1);
        aoBrightnessXYPN = block.getMixedBrightnessForBlock(blockAccess, posX + 1, posY, posZ);

        aoBrightnessXYZNNN = block.getMixedBrightnessForBlock(blockAccess, posX - 1, posY, posZ - 1);
        aoBrightnessXYZNNP = block.getMixedBrightnessForBlock(blockAccess, posX - 1, posY, posZ + 1);
        aoBrightnessXYZPNN = block.getMixedBrightnessForBlock(blockAccess, posX + 1, posY, posZ - 1);
        aoBrightnessXYZPNP = block.getMixedBrightnessForBlock(blockAccess, posX + 1, posY, posZ + 1);

        int mixedBrightnessForBlock = block.getMixedBrightnessForBlock(blockAccess, posX, posY, posZ);// Will be constant for all sides, calc early

        if (!blockAccess.getBlock(posX, posY - 1, posZ).isOpaqueCube()) {
            mixedBrightnessForBlock = block.getMixedBrightnessForBlock(blockAccess, posX, posY - 1, posZ);
        }

        brightnessTopLeft = getAoBrightness(aoBrightnessXYZNNP, aoBrightnessXYNN, aoBrightnessYZNP, mixedBrightnessForBlock);

        aoLightValueScratchXYNN = blockAccess.getBlock(posX - 1, posY, posZ).getAmbientOcclusionLightValue();
        aoLightValueScratchYZNN = blockAccess.getBlock(posX, posY, posZ - 1).getAmbientOcclusionLightValue();
        aoLightValueScratchYZNP = blockAccess.getBlock(posX, posY, posZ + 1).getAmbientOcclusionLightValue();
        aoLightValueScratchXYPN = blockAccess.getBlock(posX + 1, posY, posZ).getAmbientOcclusionLightValue();

        aoLightValueScratchXYZNNN = blockAccess.getBlock(posX - 1, posY, posZ - 1).getAmbientOcclusionLightValue();
        aoLightValueScratchXYZNNP = blockAccess.getBlock(posX - 1, posY, posZ + 1).getAmbientOcclusionLightValue();
        aoLightValueScratchXYZPNN = blockAccess.getBlock(posX + 1, posY, posZ - 1).getAmbientOcclusionLightValue();
        aoLightValueScratchXYZPNP = blockAccess.getBlock(posX + 1, posY, posZ + 1).getAmbientOcclusionLightValue();

        float woag = blockAccess.getBlock(posX, posY - 1, posZ).getAmbientOcclusionLightValue();
        float topLeftAOCoeff = ((aoLightValueScratchXYZNNP + aoLightValueScratchXYNN + aoLightValueScratchYZNP + woag) / 4.0F) * 0.5F;
        colorRedTopLeft = aoR * topLeftAOCoeff;
        colorGreenTopLeft = aoG * topLeftAOCoeff;
        colorBlueTopLeft = aoB * topLeftAOCoeff;
    }

    public static int getAoBrightness(int ao1, int ao2, int ao3, int aoMin) {
        if (ao1 == 0)
            ao1 = aoMin;
        if (ao2 == 0)
            ao2 = aoMin;
        if (ao3 == 0)
            ao3 = aoMin;
        return (ao1 + ao2 + ao3 + aoMin) / 4 & 0b111111110000000011111111;
    }
}
