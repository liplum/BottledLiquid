package net.liplum.bl;

import mindustry.Vars;
import mindustry.mod.Mod;
import mindustry.mod.Mods;
import net.liplum.bl.bottle.Bottling;

public class BottledLiquidMod extends Mod {
    public static Mods.LoadedMod self;

    public BottledLiquidMod() {
    }

    @Override
    public void init() {
    }

    @Override
    public void loadContent() {
        self = Vars.mods.getMod(Meta.ModId);
        Bottling.bottlingAllLiquid();
        Blocks.load();
    }
}
