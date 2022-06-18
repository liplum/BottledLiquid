package net.liplum.bl.bottle;

import arc.graphics.g2d.TextureRegion;
import mindustry.content.Items;
import mindustry.type.Item;
import mindustry.type.Liquid;
import mindustry.ui.Cicon;
import net.liplum.bl.SpecialDishes;

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
        BottledIcon.genIconFor(cicons, liquid, SpecialDishes.mappingTemplate(liquid));
    }
}
