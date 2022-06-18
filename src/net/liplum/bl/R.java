package net.liplum.bl;

public final class R {
    public static final class Content {
        public static String gen(String name) {
            return Meta.ModId + "-" + name;
        }
    }

    public static final class Icon {
        public static String Bottle = gen("bottled");
        public static String Cup = gen("cupped");

        public static String gen(String name) {
            return "/sprites/items/" + name + "-liquid-template.png";
        }
    }
}
