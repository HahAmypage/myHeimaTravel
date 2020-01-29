package com.davina.travel.util;

import java.util.ResourceBundle;

/**
 * 工厂类，用于创建接口的实现类对象
 */
public class FactoryUtil {

    private static ResourceBundle resourceBundle;

    static {
        resourceBundle = ResourceBundle.getBundle("impl");
    }

    /**
     * 根据接口名字获取对应实现类对象
     * @param interfaceName
     * @return
     */
    public static Object getInstance(String interfaceName){
        try {
            String className = resourceBundle.getString(interfaceName);
            return Class.forName(className).getConstructor().newInstance();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
