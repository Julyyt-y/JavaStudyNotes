/**
 * 知识点：线程练习、同步代码块
 * 需求：创建两个线程，和主线程交替运行；简单的卖票程序
 *
 * static Thread currentThread();获取当前线程对象
 * getName();获取线程名称
 * 设置线程名称：setName或者构造函数
 *
 * 创建线程的第二种方式——实现Runnable接口：
 *       1.定义类实现Runnable接口
 *       2.覆盖Runnable接口中的run方法
 *         将线程要运行的代码存放在该run方法中
 *       3.通过Thread类建立线程对象
 *       4.将Runnable接口的子类对象作为实际参数传递给Thread类的构造函数
 *         为什么要将Runnable接口的子类对象传递给Thread的构造函数？
 *         因为自定义的run方法所属的对象是Runnable接口的子类对象，
 *         所以要让线程去指定指定对象的run方法，就必须要明确该方法所属对象
 *       5.调用Thread类的start方法开启线程并调用Runnable接口子类的run方法
 *
 * 实现方式和继承方式有什么区别：
 * 实现方式的好处：避免了单继承的局限性
 * 在定义线程时，建议使用实现方式
 * 两种方式的区别：
 * 继承Thread：线程代码存放在Thread子类的run方法中
 * 实现Runnable：线程代码存放在接口的子类的run方法中
 *
 * 多线程运行可能会出现安全问题：
 * 当多条语句在操作同一线程共享数据时，一个线程对多条语句只执行了一部分，还没有执行完，
 * 另一个线程就参与进来执行，导致共享数据的错误
 * 解决办法：对多条操作共享数据的语句，只能让一个线程都执行完，且在执行过程中，其他线程不可以参与执行
 *
 * Java对多线程的安全问题提供了专业的解决方式，就是同步代码块、同步函数和同步锁
 * 同步代码块：
 * （需要同步的代码：需要共享操作的语句）
 *          synchronized(对象){
 *              需要被同步的代码
 *          }
 * 使用同步监视器的通用方法就是同步代码块；
 * 同步监视器的目的：阻止两个线程对同一个资源进行并发访问；
 *
 * 同步的前提：
 *   1.必须要有两个或多个线程
 *   2.必须是多个线程使用同一个锁
 * 必须保证同步中只能有一个线程在运行
 * 同步的好处：解决了多线程的安全问题
 * 同步的弊端：多个线程都需要判断锁，较为消耗资源
 *
 * 对象如同锁，持有锁的线程可以在同步中执行，
 * 没有持有锁的线程即使获取CPU的执行权，也进不去，因为没有获取锁
 *
 */
class Test3 extends Thread{
    private String name;
    //构造函数
    Test3(String name){
        this.name = name;
    }
    @Override
    public void run() {
        for (int i = 0;i <60;i ++){
            //this.getName()获取当前线程的名字
//            System.out.println(this.getName()+"test run"+i);
            //调用Thread.currentThread()方法获取当前线程
            System.out.println((Thread.currentThread()==this)+"..."+getName()+"run..."+i);
        }
    }
}
//public class Begin34 {
//    public static void main(String[] args){
//        Test3 t1 = new Test3("one--");
//        Test3 t2 = new Test3("two++");
//        t1.start();
//        t2.start();
//        for (int j = 0;j <60;j ++){
//            System.out.println("main . . ."+j);
//        }
//    }
//}

//需求：简单的卖票程序
//多个窗口同时卖票
class Ticket1 implements Runnable{  //建立Runnable接口
    private int tick = 100;
    Object obj = new Object();
    public void run(){
        while(true){
            //obj作为同步监视器，任何线程进入下面的代码块之前，必须先获得对obj的锁定，
            //使得其他线程无法获得锁（也就无法修改它），
            //这种做法符合“加锁、修改、释放锁”的逻辑
            /**括号里的obj就是同步监视器（任何时刻只能有一个线程可以获得对同步监视器的锁定），
             * 线程开始执行同步代码块之前，必须先获得同步监视器的锁定
             */
            synchronized (obj) {
                while (true) {
                    if (tick > 0) {
                        try {
                            Thread.sleep(10);
                        } catch (Exception e) {

                        }
                        System.out.println(Thread.currentThread().getName() + "sale:" + tick--);
                    }
                }
            } //同步代码块结束，线程释放同步锁
        }
    }
}

//有3个线程
//实现Runnable接口
class Ticket implements Runnable{
    private int tick = 100;
//    Object obj = new Object();

    private String name;
    Ticket(String name){
        this.name = name;
    }
    boolean flag = true;
    //自定义线程
    public void run() {
        if (flag){
            while (true) {
                //同步代码块，用于让此线程先执行完
                synchronized (Ticket.class){
                    try {
                        Thread.sleep(10);
                    } catch (Exception e) {
                    }
                    System.out.println(Thread.currentThread().getName() + "...code:" + tick--);
                }
            }
        }
        else
            while (true)
                show();
    }
    //同步函数
    public synchronized void show(){
        if (tick > 0) {
            try {

                Thread.sleep(10);
            } catch (Exception e) {

            }
            System.out.println(Thread.currentThread().getName() + "show:" + tick--);
        }
    }
}
public class Begin34 {
    public static void main(String[] args) {
        Ticket t = new Ticket("one");
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
//        Thread t3 = new Thread(t);
//        Thread t4 = new Thread(t);
        t1.start();
//        t.flag = false;
        t2.start();
        t.flag = false;
//        t3.start();
//        t4.start();
    }
}