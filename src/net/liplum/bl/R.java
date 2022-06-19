package net.liplum.bl;

public final class R {
    public static final class Content {
        public static String gen(String name) {
            return Meta.ModId + "-" + name;
        }
    }

    public static final class Icon {
        public static String Bottle = "bottled";
        public static String Cup = "cupped";

        public static String gen(String name) {
            return "/sprites/items/" + name + "-liquid-template.png";
        }
    }
}
