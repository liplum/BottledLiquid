package net.liplum.bl.bottle;

import arc.graphics.Pixmap;
import arc.graphics.Texture;
import arc.graphics.g2d.TextureRegion;
import mindustry.content.Items;
import mindustry.graphics.Pal;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.ui.Cicon;
import net.liplum.bl.utils.BytesH;
import net.liplum.bl.utils.PixmapH;
import net.liplum.bl.utils.Res;

import java.io.InputStream;
import java.util.Arrays;

public class BottledLiquid extends Item {
    public Liquid liquid;

    public BottledLiquid(Liquid liquid) {
        super("bottled-" + liquid.name);
        this.liquid = liquid;
        this.localizedName = "[" + liquid.localizedName + "]";
        this.color = liquid.color.cpy().lerp(Items.metaglass.color, 0.3f);
    }

    @Override
    public TextureRegion icon(Cicon icon) {
        if (!isIconLoaded) {
            composeIcon();
        }
        return super.icon(icon);
    }

    private boolean isIconLoaded = false;

    public void composeIcon() {
        isIconLoaded = true;
        InputStream originalStream = Res.getFromJar("/sprites/items/bottled-liquid-template.png");
        Pixmap original = new Pixmap(BytesH.readBytes(originalStream));
        Pixmap icon = new Pixmap(original.getWidth(), original.getHeight());
        int width = icon.getWidth();
        int height = icon.getHeight();
        int targetColor = liquid.color.rgba8888();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int color = PixmapH.getRaw(original, i, j);
                if (color == 0xFF0000FF) {
                    PixmapH.setRaw(icon, i, j, targetColor);
                } else {
                    PixmapH.setRaw(icon, i, j, color);
                }
            }
        }
        TextureRegion tr = new TextureRegion(new Texture(icon));
        Arrays.fill(cicons, tr);
    }
}
