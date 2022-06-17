package net.liplum.bl.utils;

import arc.graphics.Pixmap;

public class PixmapH {
    public static int getRaw(Pixmap p, int x, int y) {
        return p.getPixels().getInt((x + y * p.getWidth()) * 4);
    }

    public static void setRaw(Pixmap p, int x, int y, int color) {
        p.getPixels().putInt((x + y * p.getWidth()) * 4, color);
    }
}
