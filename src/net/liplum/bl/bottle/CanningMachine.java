package net.liplum.bl.bottle;

import arc.util.Nullable;
import arc.util.io.Reads;
import arc.util.io.Writes;
import mindustry.Vars;
import mindustry.gen.Building;
import mindustry.graphics.Pal;
import mindustry.type.Liquid;
import mindustry.ui.Bar;
import mindustry.world.Block;
import net.liplum.bl.Var;

import static java.lang.Math.max;

public class CanningMachine extends Block {
    public float canningReqTime = 30f;

    public CanningMachine(String name) {
        super(name);
        buildType = CanningBuild::new;
        hasPower = true;
        hasLiquids = true;
        update = true;
        consumesPower = true;
        solid = true;
        hasItems = true;
    }

    @Override
    public void init() {
        liquidCapacity = max(liquidCapacity, Var.liquidPerBottle * 3f);
        super.init();
    }

    @Override
    public void setBars() {
        super.setBars();
        bars.add("progress",
                (CanningBuild b) -> new Bar(
                        () -> (int) (b.getProgress() * 100) + "%",
                        () -> b.curCanning != null ? b.curCanning.color : Pal.gray,
                        b::getProgress
                ));
    }

    public class CanningBuild extends Building {
        public float curCanningTime = 0f;
        @Nullable
        public Liquid curCanning = null;

        @Override
        public void updateTile() {
            Liquid canning = getCurrentCanningLiquid();
            if (canning != null) {
                curCanningTime += edelta();
                if (curCanningTime > canningReqTime) {
                    curCanningTime = 0f;
                    caned(canning);
                }
            }
        }

        public float getProgress() {
            return curCanningTime / canningReqTime;
        }

        @Override
        public boolean acceptLiquid(Building source, Liquid liquid) {
            return hasLiquids &&
                    Bottling.contains(liquid) &&
                    liquids.get(liquid) < liquidCapacity;
        }

        /**
         * Output the bottled liquid and consume the corresponding liquid.
         *
         * @param liquid which to be canned
         */
        public void caned(Liquid liquid) {
            BottledLiquid bottled = Bottling.get(liquid);
            if (bottled != null) {
                items.add(bottled, 1);
                dump(bottled);
                liquids.remove(liquid, Var.liquidPerBottle);
                curCanning = null;
            }
        }

        /**
         * It won't change until the canning succeeded
         *
         * @return which liquid to be canned or null if there is no liquid.
         */
        @Nullable
        public Liquid getCurrentCanningLiquid() {
            if (curCanning != null) return curCanning;
            for (Liquid liquid : Vars.content.liquids()) {
                if (liquids.get(liquid) >= Var.liquidPerBottle) {
                    curCanning = liquid;
                    return liquid;
                }
            }
            return null;
        }

        @Override
        public void write(Writes write) {
            super.write(write);
            write.f(curCanningTime);
            write.i(curCanning != null ? curCanning.id : -1);
        }

        @Override
        public void read(Reads read, byte revision) {
            super.read(read, revision);
            curCanningTime = read.f();
            int id = read.i();
            curCanning = id != -1 ? Vars.content.liquid(id) : null;
        }
    }
}
