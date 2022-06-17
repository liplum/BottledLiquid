package net.liplum.bl.utils;

import java.io.InputStream;

public class Res {
    public static InputStream getFromJar(String name) {
        return Res.class.getResourceAsStream(name);
    }
}
