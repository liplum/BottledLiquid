package net.liplum.bl.bottle;

import arc.struct.ObjectMap;
import mindustry.Vars;
import mindustry.type.Liquid;

public class Bottling {
    public static ObjectMap<Liquid, BottledLiquid> liquid2Bottled = new ObjectMap<>();

    /**
     * Bottle all liquid except for the hidden.
     */
    public static void bottlingAllLiquid() {
        for (Liquid liquid : Vars.content.liquids()) {
            if (!liquid.isHidden()) {
                BottledLiquid bottled = new BottledLiquid(liquid);
                liquid2Bottled.put(liquid, bottled);
            }
        }
    }

    public static BottledLiquid get(Liquid liquidToBeBottled) {
        return liquid2Bottled.get(liquidToBeBottled);
    }

    public static boolean contains(Liquid liquidToBeBottled) {
        return liquid2Bottled.containsKey(liquidToBeBottled);
    }
}
