import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.*;

@Slf4j
public class Temp {
    private static final ZoneId zoneId = ZoneId.of("Asia/Shanghai");

    private static final Clock clock = Clock.fixed(Instant.parse("2026-11-18T02:00:00Z"), ZoneOffset.UTC);
    public static void main(String[] args) {
        Clock fixed = Clock.fixed(Instant.now(), ZoneOffset.UTC);
        OffsetDateTime offsetDateTime = fixed.instant().atZone(zoneId).toOffsetDateTime();
        log.info("offsetDateTime is : {}",offsetDateTime);
    }
}
