/**
 * 知识点：匿名内部类的练习
 */
interface Inter3{
    void method();
}
class Test1{
//    static class Inner3 implements Inter3{
//        public void method(){
//            System.out.println("method run");
//        }
//    }
    static Inter3 function(){
        return new Inter3() {
            public void method(){

                System.out.println("method run");
            }
        };
    }
}
public class Begin25 {
    public static void main(String[] args){
        Test1.function().method();
//        Inter3 in = Test1.function();
//        in.method();

        show(new Inter3() {
            @Override
            public void method() {
               System.out.println("method show run");
            }
        });
    }
    public static void show(Inter3 in){
        in.method();
    }
}

class Overloading {
    //原方法
    public int test(){
        System.out.println("test1");
        return 1;
    }

    //重载，参数列表不同
    public void test(int a){

        System.out.println("test2");
    }

    //以下两个参数类型顺序不同
    public String test(int a,String s){
        System.out.println("test3");
        return "returntest3";
    }

    public String test(String s,int a){
        System.out.println("test4");
        return "returntest4";
    }

    public static void main(String[] args){
        Overloading o = new Overloading();
        System.out.println(o.test());
        o.test(1);
        System.out.println(o.test(1,"test3"));
        System.out.println(o.test("test4",1));
    }
}