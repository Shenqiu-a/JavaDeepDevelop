package MultithreadingBasics.CreateThread;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/415:11
 */

public class Main {
    public static void main(String[] args) throws InterruptedException {
        /// 线程启动后，会进入就绪状态，并不会立即执行，只有在拿到 CPU时间片的时候才会执行
        /// 所以还需要再研究 线程的 6种状态（线程的生命周期）
        /// 线程的 6种状态：New   Runnable   Blocked   Waiting   Timed_Waiting   Terminated
        interruptThread();
    }

    public static void defautThreadExecute() {
        Thread thread = new myThread();
        thread.setName("myThread");
        thread.start();
    }

    public static void runnableExecute(){
        Runnable runnable = new myRunnable();
        Thread thread = new Thread(runnable,"myRunnableThread-1");
        Thread thread2 = new Thread(runnable,"myRunnableThread-2");
        thread.start();
        thread2.start();
    }

    public static void lambdaExecute(){
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " is running...");
        };
        Thread thread = new Thread(runnable,"LambdaRunnableThread");
        thread.start();

        Thread lambdaThread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " is running...");
        },"LambdaThread");
        lambdaThread.start();
    }

    public static void Block() throws InterruptedException {
        /// 阻塞的永远是调用join方法的线程，例如A需要等B执行完，那么可以在A中调用B的join方法，阻塞的线程A会等待B执行完毕，才会继续执行
        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread B is finished.");
        });
        threadB.start();
        threadB.join();
        System.out.println("Main thread is finished.");
    }

    /// 如果 main线程启动 A和 B，然后先启动 A和 B，之后 join A，join B，那么顺序也一定是 A，B，main，但是 B的时间可能会立即执行完
    /// 也就是说，join 方法会阻塞当前线程，【直到被join的线程执行完毕】，A 和 B 基本同时启动的情况下，后面的join的等待时间可能会更短，因为 B 和 A 已经并发执行了
    public static void Block2(){
        Thread threadB = new Thread(() -> {
            System.out.println("Thread B is running...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread B is finished.");
        });

        Thread threadA = new Thread(() -> {
            System.out.println("Thread A is running...");
            threadB.start();
            System.out.println("Thread A is waiting for Thread B to finish...");
            try {
                threadB.join();  // 因此A会被阻塞，直到B执行完毕
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread A is finished.");
        });
        threadA.start();
    }

    /**
     * 线程状态
     */
    public static void ThreadStatus() {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("Befor start : " + thread.getState());
        thread.start();
        System.out.println("After start : " + thread.getState());

        Thread.State previousState = null;
        while (thread.isAlive()) {
            Thread.State currentState = thread.getState();
            if (currentState != previousState) {
                System.out.println("State : " + thread.getState());
                previousState = currentState;
            }
        }
        System.out.println("State : " + thread.getState());
    }

    /**
     * 线程分为  用户线程（非守护线程）  守护线程
     * 只有用户线程全部执行结束，JVM才会退出
     * 守护线程依赖于用户线程，用户线程结束，守护线程结束
     */
    public static void DaemonThread() {
        System.out.println("=============================");
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("I am a daemon thread.");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.setDaemon(true);  // 设置为守护线程

        Thread userThread = new Thread(() -> {
            System.out.println("I am a non-daemon(user-thread) thread.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("non-daemon is finished.");
        });
        userThread.start();
        thread.start();
        System.out.println("Main thread is finished.");
    }

    /**
     * Java没有杀死一个线程的方法，只能发送一个中断信号，然后让线程自己去判断何时停止
     * 中断一个线程，线程不会立即中断，而是自己去找一个中断点，然后中断
     */
    public static void interruptThread() {
        Thread counterThread = new Thread(() -> {
            long count = 0;
            while (!Thread.currentThread().isInterrupted()) {  // 判断线程是否被中断
                count++;
                if (count % 1_000_000 == 0) {
                    System.out.print("\rCount : " + count); // 刷新控制台
                }
            }
            System.out.println("Save the counter.");  // 中断后可以进行一些最后的操作, 比如保存数据; 需要被join一下，等待处理完成(也就是线程结束)
        });

        // 也可以通过捕获sleep异常来退出
        Thread counterThread2 = new Thread(() -> {
            long count = 0;
            try {
                while (!Thread.currentThread().isInterrupted()) {  // 判断线程是否被中断
                    count++;
                    System.out.print("\rCount : " + count); // 刷新控制台
                    Thread.sleep(500);
                }
            } catch (InterruptedException e){
                System.out.println("Exit");
            }
            System.out.println("Save the counter.");  // 中断后可以进行一些最后的操作, 比如保存数据; 需要被join一下，等待处理完成(也就是线程结束)
        });

        Thread interruptThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("\nBefore interrupt: Counter thread is alive: " + counterThread.isAlive());
            System.out.println("Interrupting the counter thread...");
            counterThread.interrupt();
            try {
                counterThread.join(); // 等待线程结束，这样After interrupt的状态就会使false，线程结束了
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("After interrupt: Counter thread is alive: " + counterThread.isAlive());

        });
        counterThread.start();
        interruptThread.start();
    }
}
