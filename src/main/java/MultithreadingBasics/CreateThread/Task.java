package MultithreadingBasics.CreateThread;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/415:21
 */

public class Task {
    public void runTask(){
        System.out.println(Thread.currentThread().getName() + " is running...");
    }
}
