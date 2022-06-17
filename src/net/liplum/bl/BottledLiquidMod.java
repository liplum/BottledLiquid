package net.liplum.bl;

import mindustry.mod.Mod;
import net.liplum.bl.bottle.Bottling;

public class BottledLiquidMod extends Mod {

    public BottledLiquidMod() {
    }

    @Override
    public void init() {
        Bottling.bottlingAllLiquid();
    }

    @Override
    public void loadContent() {
        Blocks.load();
    }
}
