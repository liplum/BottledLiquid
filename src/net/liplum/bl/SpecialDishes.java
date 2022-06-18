package net.liplum.bl;

import mindustry.type.Liquid;

import java.util.HashMap;

public class SpecialDishes {
    public static HashMap<String, String> liquidName2TemplateName = new HashMap<>();

    static {
        HashMap<String, String> m = liquidName2TemplateName;
        m.put("betamindy-coffee", R.Icon.Cup);
    }

    public static String mappingTemplate(Liquid liquid) {
        String templateName = liquidName2TemplateName.get(liquid.name);
        if (templateName == null) {
            return R.Icon.Bottle;
        }
        return templateName;
    }
}
