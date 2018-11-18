/**
 * 知识点：设计模式
 *
 * 单例模式设计：解决一个类在内存中只存在一个对象
 *
 * 要想保证对象唯一：
 * 1.为了避免其他程序过多建立该对象，先禁止其他程序建立该对象
 * 2.为了让其他程序可以访问到该类对象，只好在本类中定义一个对象
 * 3.为了方便其他程序对自定义对象的访问，可以对外提供一些访问方式
 * 以上三步用代码体现：
 * 1.将构造函数私有化
 * 2.在类中创建一个本类对象
 * 3.提供一个方法可以获取到该类对象
 * 当需要将该事物的对象保证在内存中唯一时，就将以上的三步加上即可
 */
class Single{  //在方法区中
/*  private Single(){}; //此方法是类（Single）一进内存，就已经创建好了对象，开发时多用此方法
    private static Single s = new Single();  //new Single 在堆内存中
    public static Single getInstance(){  //方法
        return s;
    }*/
//或
    private static Single s = null;  //此方法是方法（Instance）被调用时才初始化，也叫做对象的延时加载
    private Single(){};
    public static Single getInstance(){
        if(s == null){
            s = new Single();
        }
        return s;
    }
    private int num;
    public void setNum(int num){

        this.num = num;
    }
    public int getNum(){

        return num;
    }
}
public class Begin12 {
    public static void main(String[] args){
        Single ss = Single.getInstance();  //main和ss都在栈内存中
        ss.getNum();
//        Single s1 = new Single();
//        s1.setNum(23);
//        Single s2 = new Single();
//        s2.setNum(21);

    }
}
