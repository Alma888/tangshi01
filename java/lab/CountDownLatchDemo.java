package lab;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    private static class Job implements Runnable {
        private CountDownLatch countDownLatch;

        Job(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            Random random = new Random();
            try {
                Thread.sleep(random.nextInt(30) * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //下面这个方法意思是，当前线程任务结束，传进来的参数就减1
            countDownLatch.countDown();
            System.out.println("一个线程的任务结束了");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //刚开始先定义一个countDownLatch，传进来参数为所操作的任务数
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Job(countDownLatch));
            thread.start();
        }

        System.out.println("等待 10 个线程全部结束");
        countDownLatch.await();//这个方法就是在等待CountDownLatch内传的参数减为0
        System.out.println("10 个线程全部结束了");
    }
}
