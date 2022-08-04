package net.liplum.bl.bottle;

import arc.graphics.g2d.TextureRegion;
import mindustry.content.Items;
import mindustry.type.Item;
import mindustry.type.Liquid;
import net.liplum.bl.R;
import net.liplum.bl.SpecialDishes;

public class BottledLiquid extends Item {
    public Liquid liquid;

    public String containerType;

    public BottledLiquid(Liquid liquid) {
        super(SpecialDishes.mappingContainer(liquid) + "-" + liquid.name);
        containerType = SpecialDishes.mappingContainer(liquid);
        this.liquid = liquid;
        this.localizedName = "[" + liquid.localizedName + "]";
        this.color = liquid.color.cpy().lerp(Items.metaglass.color, 0.3f);
    }

    @Override
    public void loadIcon() {
        TextureRegion icon = BottledIcon.genIconFor(liquid, R.Icon.gen(containerType));
        fullIcon = icon;
        uiIcon = icon;
    }
}
