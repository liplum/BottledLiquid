package net.liplum.bl;

import mindustry.content.Items;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import net.liplum.bl.bottle.BeverageMachine;
import net.liplum.bl.bottle.CanningMachine;

public class Blocks {
    public static BeverageMachine beverageMachine;
    public static CanningMachine canningMachine;

    public static void load() {
        beverageMachine = new BeverageMachine("beverage-machine") {{
            requirements(Category.crafting, ItemStack.with(
                    Items.titanium, 200,
                    Items.graphite, 150,
                    Items.metaglass, 100
            ));
            size = 2;
            itemCapacity = 20;
            liquidCapacity = Var.liquidPerBottle * 10;
            consumes.power(3f);
        }};
        canningMachine = new CanningMachine("canning-machine") {{
            requirements(Category.crafting, ItemStack.with(
                    Items.titanium, 150,
                    Items.graphite, 100,
                    Items.metaglass, 200
            ));
            size = 2;
            itemCapacity = 20;
            liquidCapacity = Var.liquidPerBottle * 10;
            consumes.power(3f);
        }};
    }
}
