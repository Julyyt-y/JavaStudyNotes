/**
 * 知识点：多线程---优先级（抢资源的频率）、线程让步
 *
 * 线程让步：yield()
 * 让当前正在执行的线程暂停，进入就绪状态（而非阻塞），
 * 线程调用yield()方法后将执行机会让给优先级相同的其它线程
 *
 * 所有的线程默认优先级都是5（共10级）
 */

class Demo8 implements Runnable{
    @Override
    public void run() {
        for (int x = 0;x < 70;x ++){
            System.out.println(Thread.currentThread().toString()+"......"+x);
            Thread.yield();
        }
    }
}
public class Begin42 {
    public static void main(String[] args) throws Exception{

        Demo8 d = new Demo8();

        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);

        t1.start();

        //设置线程优先级
        t1.setPriority(Thread.MAX_PRIORITY);
        //t2线程将会在t1和main执行完后再执行
        t2.setPriority(Thread.MIN_PRIORITY);

        t2.start();

        for (int x = 0;x <80;x ++){
            System.out.println("main..."+x);
        }
        System.out.println("over");
    }
}
