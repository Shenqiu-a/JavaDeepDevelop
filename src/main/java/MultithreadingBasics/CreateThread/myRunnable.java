package MultithreadingBasics.CreateThread;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/415:18
 */

public class myRunnable extends Task implements Runnable{
    @Override
    public void run() {
        runTask();
    }
}
