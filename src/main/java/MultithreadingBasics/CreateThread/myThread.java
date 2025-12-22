package MultithreadingBasics.CreateThread;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/415:11
 */

///  通过继承 Thread 类创建线程，但是，如果该类继承了其他类，就无法再继承Thread类，扩展性差，而且逻辑写死在了run中
public class myThread extends Thread{
    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName() + " is running...");
    }
}
