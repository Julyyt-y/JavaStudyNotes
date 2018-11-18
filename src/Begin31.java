import javax.xml.bind.ValidationException;

/**
 * 知识点：异常练习
 * 需求：有一个圆形和长方形，都可以获取面积，
 *       对于面积，如果出现非法的数值，视为是获取面积出现问题，
 *       问题通过异常来表示，现对这个程序进行基本设计
 *
 */

//自定义异常
class NoValueExceprion extends RuntimeException{
    NoValueExceprion(String message){

        super(message);
    }
}

interface Shape{
    void getArea();
}
class Rec implements Shape{
    private int len,wid;
    Rec(int len,int wid) /*throws NoValueExceprion*/{
        if (len <= 0||wid <=0){
            throw new NoValueExceprion("出现非法值");
        }
        this.len = len;
        this.wid = wid;
    }
    public void getArea(){

        System.out.println(len * wid);
    }
}

class Circle implements Shape{
    private int radius;
    public static final double PI = 3.14;
    Circle(int radius){
        if (radius <= 0){
            throw new RuntimeException("出现非法半径");
        }
        this.radius = radius;
    }
    public void getArea(){

        System.out.println(PI * radius * radius);
    }
}
public class Begin31 {
    public static void main(String[] args){
//        try {
            Rec r = new Rec(3,9);
            r.getArea();
//        }
//        catch (NoValueExceprion e){
//            System.out.println(e.toString());
//        }

        Circle c = new Circle(-2);
        c.getArea();
        System.out.println("over");
    }
}
