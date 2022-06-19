package net.liplum.bl.bottle;

import arc.graphics.Color;
import arc.graphics.Pixmap;
import arc.graphics.Texture;
import arc.graphics.g2d.TextureRegion;
import mindustry.type.Liquid;
import net.liplum.bl.utils.BytesH;
import net.liplum.bl.utils.PixmapH;
import net.liplum.bl.utils.Res;

import java.io.InputStream;
import java.util.HashMap;

public class BottledIcon {
    private static final HashMap<String, Pixmap> templateName2Cache = new HashMap<>();

    public static TextureRegion genIconFor(Liquid targetLiquid, String templateName) {
        return genIcon(templateName, targetLiquid.color);
    }

    public static TextureRegion genIcon(String templateName, Color color) {
        Pixmap template = getTemplate(templateName);
        return genIcon(template, color);
    }

    public static Pixmap getTemplate(String templateName) {
        Pixmap template = templateName2Cache.get(templateName);
        if (template == null) {
            InputStream originalStream = Res.getFromJar(templateName);
            template = new Pixmap(BytesH.readBytes(originalStream));
            templateName2Cache.put(templateName, template);
        }
        return template;
    }

    public static TextureRegion genIcon(Pixmap template, Color c) {
        Pixmap icon = new Pixmap(template.getWidth(), template.getHeight());
        int width = icon.getWidth();
        int height = icon.getHeight();
        int targetColor = c.rgba8888();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = PixmapH.getRaw(template, i, j);
                if (color == 0xFF0000FF) {
                    PixmapH.setRaw(icon, i, j, targetColor);
                } else {
                    PixmapH.setRaw(icon, i, j, color);
                }
            }
        }
        return new TextureRegion(new Texture(icon));
    }
}
