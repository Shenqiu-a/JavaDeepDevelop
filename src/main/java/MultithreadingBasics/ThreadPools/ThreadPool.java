package MultithreadingBasics.ThreadPools;

import MultithreadingBasics.CreateThread.myRunnable;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;

/**
 * 功能：
 * 作者：yml
 * 日期：2025/12/516:59
 */

public class ThreadPool {
    public static void main(String[] args) {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            if (Math.random() > 0.5){
                fixedThreadPool.execute(new myRunnable());
            } else {
              fixedThreadPool.execute(() -> {
                  System.out.println("running...");
              });
            }
        }
        fixedThreadPool.shutdown();

        TimeTask();
    }

    static void Task2() throws ExecutionException, InterruptedException {
        // 1.简单异步任务
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Asynchronous thread: " + Thread.currentThread().getName() + " is running...");
        });

        // 2.有返回值的异步任务
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("Asynchronous thread: " + Thread.currentThread().getName() + " is running...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "hi there";
        });
        System.out.println("Asynchronous thread result: " + supplyAsync.get());

        // 3.任务链式调用
        CompletableFuture<String> chainedFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("\tFirst stage: " + "hi");
            return "hi";
        }).thenApplyAsync(s -> {
            System.out.println("\tSecond stage: " + s + " there");
            return s + " there";
        }).thenApplyAsync(s -> {
            System.out.println("\tThird stage: " + s + " !");
            return s + " !!!";
        });
        System.out.println("chained thread result: " + chainedFuture.get());

        // 4.任务组合
        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(
                CompletableFuture.runAsync(() -> {
                    System.out.println("\tFirst stage: " + "hi");
                }),
                CompletableFuture.runAsync(() -> {
                    System.out.println("\tSecond stage: " + "there");
                })
        );
        combinedFuture.get();
    }

    static void TimeTask() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int count = 0;
            @Override
            public void run() {
                System.out.println("Timer task is being executed for the " + ++count + " time.");
                if (count == 10) {
                    timer.cancel();
                    System.out.println("Timer task is canceled.");
                }
            }
        }, 1000,2000);  // 延迟1s 周期2s
    }
}
