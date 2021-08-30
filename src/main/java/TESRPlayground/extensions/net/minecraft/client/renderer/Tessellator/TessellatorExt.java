package TESRPlayground.extensions.net.minecraft.client.renderer.Tessellator;

import cpw.mods.fml.client.FMLClientHandler;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;


//Not working with Quad rendering (?)
@Extension
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TessellatorExt {
  // Theses being static is okay because the Tessellator is a singleton anyway
  private static boolean suspendedDrawing;
  private static int suspendedDrawMode;

  public static void pauseDraw(@This Tessellator thiz) {
    suspendedDrawing = thiz.jailbreak().isDrawing;
    if (suspendedDrawing) {
      suspendedDrawMode = thiz.jailbreak().drawMode;
      thiz.draw();
    }
  }

  public static void resumeDraw(@This Tessellator thiz) {
    if (suspendedDrawing) {
      rebindBlockTextures();
      thiz.startDrawing(suspendedDrawMode);
    }
  }

  private static void rebindBlockTextures() {
    FMLClientHandler.instance().getClient().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
  }
}
