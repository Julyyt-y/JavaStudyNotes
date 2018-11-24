import java.util.concurrent.locks.ReentrantLock;

/**
 * 知识点：多线程---同步锁、死锁、运行和阻塞、线程死亡
 * 死锁：同步中嵌套同步
 *
 * 同步锁：
 * 线程同步机制，显示定义同步锁；
 * 通常建议使用finally来确保在必要时释放锁。
 *
 * 死锁：
 * 两个对象互相等待对方释放同步监视器时会发生死锁，
 * 此时整个线程处于阻塞状态，无法运行。
 *
 * 线程对象的isAlive()方法：若线程处于新建或死亡则返回false，就绪运行阻塞时返回true
 * sleep()方法需要继承Thread类
 *
 * 阻塞：
 * 当发生如下情况时，线程会进入阻塞状态：
 * 1.线程调用sleep()方法主动放弃所占用的CPU资源
 * 2.线程调用了一个阻塞式IO方法，在该方法返回之前，线程被阻塞
 * 3.线程试图获得一个同步监视器，但该同步监视器正在其他线程所持有
 * 4.线程正在等待某个通知notify()
 * 5.程序调用了线程的suspend()方法将该线程挂起（这个方法容易导致死锁，尽量避免使用）
 *
 * 解除阻塞：
 * 1.调用sleep()方法已经过了指定时间
 * 2.线程调用的阻塞式IO方法已经返回
 * 3.线程成功地获得了试图取得的某个同步监视器
 * 4.线程正在等待某个通知时，其他线程发出了一个通知
 * 5.处于挂起状态的线程被调用了resume()恢复方法
 *
 * 线程死亡：
 * 1.run()方法或者call()方法运行结束，线程就会进入死亡状态
 * 2.线程抛出一个未捕获的Exception或Error
 * 3.直接调用该线程的stop()方法结束该线程（该方法容易导致死锁，不推荐使用）
 */

/*class Test4 implements Runnable{
    private boolean flag;
    Test4(boolean flag){

        this.flag = flag;
    }

    @Override
    public void run() {
        if (flag){
            synchronized (MyLock.lock_a){
                System.out.println("if lock_a");
                synchronized (MyLock.lock_b){
                    System.out.println("if lock_b");

                }
            }
        }
        else {
            synchronized (MyLock.lock_b){
                System.out.println("else lock_b");
                synchronized (MyLock.lock_a){
                    System.out.println("else lock_a");

                }
            }
        }
    }
}

class MyLock{
    static Object lock_a = new Object();
    static Object lock_b = new Object();
}
public class Begin37{
    public static void main(String[] args){
        Thread t1 = new Thread(new Test4(true));
        Thread t2 = new Thread(new Test4(false));
        t1.start();
        t2.start();
    }
}*/

//举例展示出现死锁
//程序无法向下执行，也不会抛出任何异常
//因为A1对象和B1对象的方法都是同步方法，即A对象和B对象都是同步锁，
class A1 {
    public synchronized void foo(B1 b){
        System.out.println("当前线程名："+ Thread.currentThread().getName() + "进入了A实例的foo方法");
        try {
            Thread.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("当前线程名：" + Thread.currentThread().getName() + "企图调用B实例的last()方法");
        b.last();
    }
    public synchronized void last(){
        System.out.println("进入了A类的last()方法内部");
    }
}
class B1{
    public synchronized void bar(A1 a){
        System.out.println("当前线程名：" + Thread.currentThread().getName() + "企图调用B实例的bar()方法");
        try {
            Thread.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("当前线程名：" + Thread.currentThread().getName() + "企图调用A实例的last()方法");
        a.last();
    }
    public synchronized void last(){
        System.out.println("进入了B类的last()方法内部");
    }
}
public class Begin37 implements Runnable{
    A1 a = new A1();
    B1 b = new B1();
    public void init(){
        Thread.currentThread().setName("主线程");
        //调用a对象的foo()方法
        a.foo(b);
        System.out.println("进入了主线程之后");
    }

    @Override
    public void run() {
        Thread.currentThread().setName("副线程");
        //调用b对象的bar()方法
        b.bar(a);
        System.out.println("进入了副线程之后");
    }

    public static void main(String[] args){
        Begin37 d = new Begin37();
        //以b为target启动新线程
        new Thread(d).start();
        //调用init()方法
        d.init();
    }
}