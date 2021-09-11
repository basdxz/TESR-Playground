package com.github.basdxz.tesrplay;

import com.github.basdxz.tesrplay.newRender.cuboid.ColorRGBA;

import java.awt.*;

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
}
