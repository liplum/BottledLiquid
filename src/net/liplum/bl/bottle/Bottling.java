package net.liplum.bl.bottle;

import arc.struct.ObjectMap;
import mindustry.Vars;
import mindustry.type.Liquid;
import net.liplum.bl.BottledLiquidMod;

public class Bottling {
    public static ObjectMap<Liquid, BottledLiquid> liquid2Bottled = new ObjectMap<>();

    /**
     * Bottle all liquid except for the hidden.
     */
    public static void bottlingAllLiquid() {
        Vars.content.setCurrentMod(BottledLiquidMod.self);
        for (Liquid liquid : Vars.content.liquids()) {
            if (!liquid.isHidden()) {
                BottledLiquid bottled = new BottledLiquid(liquid);
                liquid2Bottled.put(liquid, bottled);
            }
        }
        Vars.content.setCurrentMod(null);
    }

    public static BottledLiquid get(Liquid liquidToBeBottled) {
        return liquid2Bottled.get(liquidToBeBottled);
    }

    public static boolean contains(Liquid liquidToBeBottled) {
        return liquid2Bottled.containsKey(liquidToBeBottled);
    }
}
