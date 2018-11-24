import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 知识点：线程间通讯练习
 * 需求：生产者消费者（毕向东147）
 *
 * 对于多个生产者和消费者，为什么要定义while判断标记？
 * 原因：让被唤醒的线程再标记一次
 *
 * 为什么要定义notifyAll();？
 * 因为需要唤醒对方线程；
 * 因为只用notify容易出现只唤醒本方线程的情况，会导致程序中的所有线程都等待
 *
 * 当使用Lock对象在保证同步时，可使用Condition（类）让那些已经得到Lock对象却无法继续执行的线程释放Lock对象，
 * Condition也可以唤醒其他正在等待的线程。
 * Condition类提供了三个方法：
 *      await()方法：类似于隐式同步监视器上的wait()方法
 *      signal()方法：唤醒在此Lock对象上等待的单个线程
 *      signalAll()方法：唤醒在此Lock对象上等待的所有线程
 *
 */

/*
public class Begin39 {
    public static void main(String[] args){
        Resource r = new Resource();
        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);
        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(con);
        t1.start();
        t2.start();
    }
}

class Resource{
    private String name;
    private int count = 1;
    private boolean flag = false;
    public synchronized void set(String name){
        if (flag){
            try {
                wait();  //或this.wait();
            } catch (Exception e){}
        }
        this.name = name + "..."+ count++;
        System.out.println(Thread.currentThread().getName()+"...生产者"+this.name);
        flag = true;
        this.notifyAll();
    }
    public synchronized void out(){
        if (!flag){
            try {
                wait();
            } catch (Exception e){}
        }
        System.out.println(Thread.currentThread().getName()+"...消费者"+this.name);
        flag = false;
        this.notifyAll();
    }
}

class Producer implements Runnable{
    private Resource res;
    Producer(Resource res){
        this.res = res;
    }

    @Override
    public void run() {
        while (true){
            res.set("+商品+");
        }
    }
}

class Consumer implements Runnable{
    private Resource res;
    Consumer(Resource res){
        this.res = res;
    }

    @Override
    public void run() {
        while (true){
            res.out();
        }
    }
}
*/

//JDK1.5 中提供了多线程升级解决方案，将同步synchronized替换成现实Lock操作，
//将Object中的wait、notify、notifyAll替换成了condition对象，
// 该对象可以通过Lock锁进行获取

//在该实例中，实现了本方只唤醒对方的操作
public class Begin39 {
    public static void main(String[] args){
        Resource r = new Resource();
        Producer pro = new Producer(r);
        Consumer con = new Consumer(r);
        Thread t1 = new Thread(pro);
        Thread t2 = new Thread(con);
        t1.start();
        t2.start();
    }
}

class Resource{
    private String name;
    private int count = 1;
    private boolean flag = false;
    //定义显式Lock对象
    private Lock lock = new ReentrantLock();
    //获得指定Lock对象对应的Condition
    private Condition condition_pro = lock.newCondition();
    private Condition condition_con = lock.newCondition();

    public void set(String name) throws InterruptedException{

        lock.lock();
        try {
            while (flag) {
                condition_pro.await();
            }
            this.name = name + "..." + count++;
            System.out.println(Thread.currentThread().getName() + "...生产者" + this.name);

            flag = true;
            //唤醒在此Lock对象上等待的单个线程
            condition_con.signal();
        } finally {
            lock.unlock();  //释放锁的动作一定要执行
        }
    }
    public synchronized void out() throws InterruptedException{

        lock.lock();
        try {
            while (!flag) {
                condition_con.await();
            }
            System.out.println(Thread.currentThread().getName()+"...消费者"+this.name);
            flag = false;
            condition_pro.signal();
        }
        finally {
            lock.unlock();
        }
    }
}

class Producer implements Runnable{
    private Resource res;
    Producer(Resource res){

        this.res = res;
    }

    @Override
    public void run(){
        while (true){
            try {
                res.set("+商品+");
            } catch (InterruptedException e){}
        }
    }
}

class Consumer implements Runnable{
    private Resource res;
    Consumer(Resource res){

        this.res = res;
    }

    @Override
    public void run(){
        while (true){
            try {
                res.out();
            } catch (InterruptedException e){}
        }
    }
}