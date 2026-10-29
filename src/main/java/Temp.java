import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;

@Slf4j
public class Temp {
    public static void main(String[] args) {

        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");

        log.info("map is {}", map);

        log.error("error log");

        log.warn("warn log");

        if (log.isDebugEnabled()) {
            log.debug("Current log level is Debug!");
        }

    }
}
