/**
 * 知识点：多线程---Join方法
 *
 * Join：
 * 当A线程执行到了B线程的.join()方法时，A线程就会等待，等B线程都执行完，A线程才会执行
 * join可以用来临时加入线程执行
 *
 */

class Demo7 implements Runnable{
    @Override
    public void run() {
        for (int x = 0;x < 70;x ++){
            //Thread.currentThread().getName()获取当前线程的名字
            System.out.println(Thread.currentThread().getName()+"......"+x);
        }
    }
}
public class Begin41 {
    public static void main(String[] args) throws Exception{

        Demo7 d = new Demo7();

        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);

        /**t1先执行，t1执行完后t2和main强CPU的执行权*/
//        t1.start();
//        t1.join();
//        //主线程在向下走，它读到了t1的join时，t1申请加入到运行中来（t1要CPU的执行权）；
//        //此时执行权在主线程上，于是主线程的CPU的执行权就给放开了，放开后主线程处于冻结状态，
//        //不会和t1抢CPU的执行权，t1拿到执行权后把run方法中的全都打印完，打印结束后，主线程才恢复到运行状态
//        t2.start();

        /**t1先和t2争夺CPU执行权，t1执行完后t2和main争夺CPU执行权*/
        t1.start();
        t2.start();
        t1.join();
        //主线程在向下走，它读到了t1的join时，t1申请加入到运行中来（t1要CPU的执行权）；
        //此时执行权在主线程上，于是主线程的CPU的执行权就给放开了，放开后主线程处于冻结状态，
        //不会和t1抢CPU的执行权，t1拿到执行权后把run方法中的全都打印完，打印结束后，主线程才恢复到运行状态,t2什么时候结束无关，
        //如果t1结束后t2还未结束，这时主线程醒了，和t2抢CPU的执行权，即主线程和t2交替进行

        for (int x = 0;x <80;x ++){
            System.out.println("main..."+x);
        }
        System.out.println("over");
    }
}
