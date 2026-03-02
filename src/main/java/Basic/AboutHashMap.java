package Basic;


import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.HashMap;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/2/2815:01
 */

@Slf4j
public class AboutHashMap {
    public static void main(String[] args) {
        log.info("HashMap测试");
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key1", "value2");
        System.out.println(map.get("key1").equals("value2") ? 0 : 1);
        System.out.println(map);
        log.info("HashMap测试结束");

        log.info("1 << 30 is : {}", 1 << 30);
    }
}
