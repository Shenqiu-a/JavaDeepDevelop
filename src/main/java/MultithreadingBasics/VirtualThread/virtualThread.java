package MultithreadingBasics.VirtualThread;

// 导入整个Executors类（推荐）
import java.util.concurrent.Executors;

/**
 * 功能：
 * 作者：yml
 * 日期：2026/1/2214:23
 */

public class virtualThread {
    public static void main(String[] args) {
        // 创建单个虚拟线程
        Thread.startVirtualThread(() -> {
            System.out.println("Hello From a Virtual Thread");
        });

        // 用线程池跑 10 万个并发任务，传统代码可能早就 OOM 了，虚拟线程毫无压力
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100_000; i++) {
                executor.submit(() -> {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        }
    }
}
