import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/8/1310:02
 */

@Slf4j
public class TempClass {
    private static final DateTimeFormatter dmy = DateTimeFormatter.ofPattern("ddMMyyyy");
    public static void main(String[] args) {
        LocalDate parsed = LocalDate.parse("01072026", dmy);
        log.info("Parsed Date: {}",parsed);

        String formatted = parsed.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        log.info("Formatted Date: {}",formatted);

    }
}
