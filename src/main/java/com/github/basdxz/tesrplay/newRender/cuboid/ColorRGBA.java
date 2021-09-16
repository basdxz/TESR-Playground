package com.github.basdxz.tesrplay.newRender.cuboid;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.val;

import java.awt.*;

@Accessors(fluent = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ColorRGBA {
    public static final ColorRGBA NO_COLOR = new ColorRGBA(1, 1, 1, 1);

    private float r;
    private float g;
    private float b;
    private float a;

    public ColorRGBA(Color color) {
        r = clampColor(color.getRed() / 255F);
        g = clampColor(color.getGreen() / 255F);
        b = clampColor(color.getBlue() / 255F);
        a = clampColor(color.getAlpha() / 255F);
    }

    private static float clampColor(float value) {
        return Math.max(0.0F, Math.min(1.0F, value));
    }

    public ColorRGBA set(ColorRGBA that) {
        r = that.r();
        g = that.g();
        b = that.b();
        a = that.a();
        return this;
    }

    public void mult(float factor) {
        r *= factor;
        g *= factor;
        b *= factor;
    }

    public enum Colors {
        WHITE(new Color(0XF9FFFE)),
        ORANGE(new Color(0XF9801D)),
        MAGENTA(new Color(0XC74EBD)),
        LIGHT_BLUE(new Color(0X3AB3DA)),
        YELLOW(new Color(0XFED83D)),
        LIME(new Color(0X80C71F)),
        PINK(new Color(0XF38BAA)),
        GRAY(new Color(0X474F52)),
        LIGHT_GRAY(new Color(0X9D9D97)),
        CYAN(new Color(0X169C9C)),
        PURPLE(new Color(0X8932B8)),
        BLUE(new Color(0X3C44AA)),
        BROWN(new Color(0X835432)),
        GREEN(new Color(0X5E7C16)),
        RED(new Color(0XB02E26)),
        BLACK(new Color(0X1D1D21));

        private final Color color;

        Colors(Color color) {
            this.color = color;
        }

        public ColorRGBA getColor() {
            return new ColorRGBA(color);
        }

        public ColorRGBA getColor(float saturation) {
            val colorHSV = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
            colorHSV[1] = clampColor(saturation);
            return new ColorRGBA(Color.getHSBColor(colorHSV[0], colorHSV[1], colorHSV[2]));
        }
    }
}
