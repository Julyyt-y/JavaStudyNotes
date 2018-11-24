/**
 * 知识点：多态的应用
 */
//应用1：
/*abstract class Student1{

    public abstract void study();

    public void sleep(){

        System.out.println("躺着睡");
    }
}

class BaseStudent extends Student1{
    public void study(){

        System.out.println("base study");
    }
}

class AdvStudent extends Student1{
    public void study(){
        System.out.println("adv study");
    }
    public void sleep(){

        System.out.println("坐着睡");
    }
}

class DoStubent2{

    public void doSome(Student1 stu) {
        stu.study();
        stu.sleep();
    }
}
//public class Begin19 {
//    public static void main(String[] args) {
////        BaseStudent bs = new BaseStudent();
////        bs.study();
////        bs.sleep();
////        AdvStudent as = new AdvStudent();
////        as.study();
////        as.sleep();
//
////        BaseStudent bs = new BaseStudent();
////        AdvStudent as = new AdvStudent();
////        DoStubent2 ds = new DoStubent2();
////        ds.doSome(bs);
////        ds.doSome(bs);
//        //上面四步等于
//        DoStubent2 ds1 = new DoStubent2();
//        ds1.doSome(new BaseStudent());
//        ds1.doSome(new AdvStudent());
//    }
//
////    public void doSome(Student1 stu) {
////        stu.study();
////        stu.sleep();
////    }
//
//}*/


//应用2：
//需求：电脑运行实例，电脑运行基于主板
interface PCI{
    public void open();
    public void close();
}
class MainBoard{
    public void run(){
        System.out.println("mainboard run");
    }
    public void usePCI(PCI p){  //PCI p = new NetCard() 接口型引用指向自己的子类对象
        if (p != null) {
            p.open();
            p.close();
        }
    }
}
class NetCard implements PCI{
    public void open(){
        System.out.println("netboard open");
    }
    public void close(){
        System.out.println("netboard close");
    }
}
class SoundCard implements PCI{
    public void open(){
        System.out.println("soundcard open");
    }
    public void close(){
        System.out.println("soundoard close");
    }
}
public class Begin19 {
    public static void main(String[] args) {
        MainBoard mb = new MainBoard();
        mb.run();
        mb.usePCI(null);
        mb.usePCI(new NetCard());
        mb.usePCI(new SoundCard());
    }
}

