package StreamAPI;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/511:45
 */

public class Main {
    public static void main(String[] args) {
        Integer retryCount = 4;
        String maxRetryCount = "6";
        String lockTime = "10";
        if (retryCount >= Integer.valueOf(maxRetryCount).intValue())
        {
            String errMsg = String.format("密码输入错误%s次，帐户锁定%s分钟", maxRetryCount, lockTime);
            throw new RuntimeException(errMsg);
        }
    }
}

