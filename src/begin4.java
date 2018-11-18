/**
 *知识点：函数重载
 * 重载：当定义的功能相同，但参与运算的未知内容不同，这时就定义一个函数名以表示功能，方便阅读，
 *    而通过参数列表的不同来区分多个同名函数。
 *需求：求两个或三个数的和，打印 n * n 乘法表
 */

public class begin4 {
    public static void main(String[] args){
        int X = add(4,5);
        int Y = add(4,5,6);
        System.out.println(X);
        System.out.println(Y);
        printnn(Y);
        System.out.println("\n\n");
        print99();
    }
    public static void printnn(int num){
        for (int x = 1;x <= num;x++){
            for (int y = x;y <= num;y++){
                System.out.print(y+"*"+x+"="+y*x+"\t");
            }
            System.out.println();
        }
    }
    public static void print99(){

        printnn(9);
    }
    public static int add(int x,int y){

        return x+y;
    }
    public static int add(int x,int y,int z){

        return x+y+z;
    }
}
