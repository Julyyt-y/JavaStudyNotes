/**
 * 知识点：线程间通讯、等待唤醒机制
 * 线程间通讯其实就是多个线程在操作同一个资源，但是操作的动作不同
 *
 * wait();让当前线程等待，直到其他线程调用该同步监视器的notify或notifyAll方法来唤醒该线程
 * notify();唤醒在此同步监视器上等待的单个线程
 * notifyAll();唤醒在此同步监视器上等待的所有线程
 * 这3个都使用在同步中，因为要对持有监视器（锁）的线程操作，
 * 所以要使用在同步中，因为只有同步才具有锁
 * 为什么这些操作线程的方法要定义在Object类中呢？
 * 因为这些方法在操作同步中的线程时，都必须要标识它们锁操作线程只有的锁，
 * 只有同一个锁上的被等待线程，都可以被同一个锁上的notify唤醒，
 * 也就是说，等待和唤醒必须是对同一个锁做的，而锁可以是被任意对象调用的方法定义在Object类中
 */

//class Res{
//    String name;
//    String sex;
//    boolean flag = false;
//}
//class Input implements Runnable{
//    private Res r;
//    Object obj = new Object();
//    Input(Res r){
//        this.r = r;
//    }
//    @Override
//    public void run() {
//        int x = 0;
//        while (true){
//            synchronized (r) {  //或 synchronized(Input.class)
//                if (r.flag)
//                    try {
//                        r.wait();
//                    } catch (Exception e){}
//                if (x == 0) {
//                    r.name = "mike";
//                    r.sex = "man";
//                } else {
//                    r.name = "lily";
//                    r.sex = "女女女女女";
//                }
//                x = (x + 1) % 2;
//                r.flag = true;
//                r.notify();  //叫醒
//            }
//        }
//    }
//}
//class Output implements Runnable{
//    private Res r;
//    Object obj = new Object();
//    Output(Res r){
//        this.r = r;
//    }
//
//    @Override
//    public void run() {
//        while (true){
//            synchronized (r) {  //或synchronized(Input.class)
//                if (!r.flag)
//                    try {
//                        r.wait();
//                    } catch (Exception e){}
//                System.out.println(r.name + "..." + r.sex);
//                r.flag = false;
//                r.notify();
//            }
//        }
//    }
//}
//public class Begin38 {
//    public static void main(String[] args){
//        Res r = new Res();
//        Input in = new Input(r);
//        Output out = new Output(r);
//
//        Thread t1 = new Thread(in);
//        Thread t2 = new Thread(out);
//
//        t1.start();
//        t2.start();
//
//    }
//}

//优化后的代码
class Res{
    private String name;
    private String sex;
    //设置一个标志
    //当flag=true时表明此时没有录入任何信息，往进添加了后设置为true，并设置notify()或notifyAll()唤醒其他线程；
    private boolean flag = false;
    //设置姓名和性别，同步方法
    public synchronized void set(String name,String sex){
        if (flag){
            try {
                this.wait();
            } catch (Exception e){}
        }
        this.name = name;
        this.sex = sex;
        flag = true;
        this.notify();
    }
    //然后，如果标志为true就让线程等待，标志为true表明已经存在信息，取出程序可以向下执行，
    //取出后，标志设置为false，并调用notify()或notifyAll()唤醒其他线程；
    //取出线程进入线程体后，若标志=false，则调用wait()方法让该线程等待
    public synchronized void out(){
        if (!flag){
            try {
                wait();
            } catch (Exception e){}
        }
        System.out.println(name+"..."+sex);
        flag = false;
        this.notify();
    }
}

class Input implements Runnable{
    private Res r;
    Object obj = new Object();
    Input(Res r){

        this.r = r;
    }
    @Override
    public void run() {
        int x = 0;
        while (true){
                if (x == 0) {
                    r.set("mike","man");
                } else {
                    r.set("丽丽","女女女女女");
                }
                x = (x + 1) % 2;
        }
    }
}
class Output implements Runnable{
    private Res r;
    Object obj = new Object();
    //构造函数，用来为成员变量赋初值
    Output(Res r){

        this.r = r;
    }

    @Override
    public void run() {
        while (true){
            //out是在Res类中定义的同步方法
            r.out();
        }
    }
}

public class Begin38 {
    public static void main(String[] args){
        Res r = new Res();

        new Thread(new Input(r)).start();
        new Thread(new Output(r)).start();

    }
}
