package net.liplum.bl.bottle;

import arc.math.Mathf;
import arc.struct.ObjectMap;
import mindustry.Vars;
import mindustry.type.Liquid;

import static java.lang.Math.max;

public class Bottling {
    public static ObjectMap<Liquid, BottledLiquid> liquid2Bottled = new ObjectMap<>();

    /**
     * Bottle all liquid except for the hidden.
     */
    public static void bottlingAllLiquid() {
        for (Liquid liquid : Vars.content.liquids()) {
            if (liquid.minfo.mod == null && !liquid.isHidden()) {
                BottledLiquid bottled = new BottledLiquid(liquid);
                setupProperties(bottled, liquid);
                liquid2Bottled.put(liquid, bottled);
            }
        }
    }

    public static void setupProperties(BottledLiquid b, Liquid l) {
        b.explosiveness = l.explosiveness * 1.5f;
        b.radioactivity = l.viscosity * max(l.temperature - 0.5f, 0f);
        b.charge = l.heatCapacity * l.temperature;
        b.flammability = Mathf.sqrt(max(l.temperature - 0.5f, 0f) + l.flammability);
    }

    public static BottledLiquid get(Liquid liquidToBeBottled) {
        return liquid2Bottled.get(liquidToBeBottled);
    }

    public static boolean contains(Liquid liquidToBeBottled) {
        return liquid2Bottled.containsKey(liquidToBeBottled);
    }
}
