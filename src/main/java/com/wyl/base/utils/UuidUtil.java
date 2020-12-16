package com.wyl.base.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * 产生UUID随机字符串工具类
 */
public final class UuidUtil {
    private UuidUtil() {
    }

    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 测试
     */
    public static void main(String[] args) {
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());
        System.out.println(UuidUtil.getUuid());

        /*int i = 0;
        Set<String> set = new HashSet<>();
        while (true) {
            i++;
            String uuid = UuidUtil.getUuid();
            if (set.contains(uuid)) {
                break;
            }
            set.add(uuid);
			System.out.println(i);
        }*/
    }
}
