package org.example.thread.v20;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @Author qiu
 * @Date 2020/12/14 23:56
 */
public class ThreadPoolExecutorTest {

    /**
     * int corePoolSize : 核心线程数，不会被释放。
     * int maximumPoolSize ：最大线程数
     * long keepAliveTime ： 线程存活时间，如果当前线程池没有正在运行的线程，且队列中没有任务执行，则除了核心线程超出指定的存活时间，就会被释放资源
     * TimeUnit unit ：时间单位
     * BlockingQueue<Runnable> workQueue ：阻塞队列
     * ThreadFactory threadFactory ：线程工厂，可以自定义
     * RejectedExecutionHandler handler ：拒绝策略
     *     AbortPolicy：如果当前队列已满，且线程都在运行，如果新增加任务，则抛出异常 RejectedExecutionException
     *     DiscardPolicy：如果当前队列已满，且线程都在运行，如果新增加任务，则默认直接抛弃该任务
     *     DiscardOldestPolicy：如果当前队列已满，且线程都在运行，如果新增加任务，则抛弃时间最久的任务，将当前任务加入队列
     *     CallerRunsPolicy：如果当前队列已满，且线程都在运行，如果新增加任务，该任务则由设置任务的线程来运行。如果该任务是由主线程设置的，则由主线程执行
     */
    private static final Executor EXECUTOR = new ThreadPoolExecutor(4, 8, 60,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        EXECUTOR.execute(() -> {
            System.out.println("123456");
        });
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
