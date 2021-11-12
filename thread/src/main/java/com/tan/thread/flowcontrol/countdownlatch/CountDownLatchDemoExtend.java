package com.tan.thread.flowcontrol.countdownlatch;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tan.cs
 * @version 1.0
 * @description 模拟4*100接力跑步，5个队，每个队4名选手都准备好了，只等裁判一声令下，所有人同时开始跑步 当所有人都到达终点之后比赛结束
 * @date 2021/7/2 14:30
 **/
public class CountDownLatchDemoExtend {
    public static void main(String[] args)  {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(5);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        try {
            for (int i = 0; i < 5; i++) {
                final int no = i + 1;
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("No" + no + "队准备完毕，等待发令抢");
                        try {
                            begin.await();
                            CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>(new String[]{"A", "B", "C", "D"});
                            for (String s : list) {
                                System.out.println("No" + no + "队 " + (s + no) + " 开始跑步");
                                Thread.sleep((long) (Math.random() * 10000));
                                System.out.println("No" + no + "队 " + (s + no) + " 跑完100米");
                            }
                            System.out.println("No" + no + "跑到终点");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            end.countDown();
                        }
                    }
                };
                executorService.submit(runnable);
            }
            // 裁判员检查发令枪
            Thread.sleep(3000);
            System.out.println("发令枪响，比赛开始");
            begin.countDown();

            end.await();
            System.out.println("所有人到达终点，比赛结束");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }



    }
}
