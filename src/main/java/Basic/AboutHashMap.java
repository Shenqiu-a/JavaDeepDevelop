package Basic;

import java.util.Map;
import java.util.HashMap;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/2/2815:01
 */

public class AboutHashMap {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        System.out.println(map.get("key1").equals("value2") ? 0 : 1);
    }
}
