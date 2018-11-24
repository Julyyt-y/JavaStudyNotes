/**
 * 知识点：函数的使用
 *
 * 用public定义的变量或数组为全局(公共)变量。任何运行程序都能对全局变量进行使用和修改。
 *
 * 需求：比较两个数
 */
//public class begin2 {
//    public static int compare(int a,int b){
//        if(a == b)
//            return 1;
//        return 0;
//    }
//    public static void main(String[] args) {
//        int x = 4;
//        int y = 9;
//        int X = compare(x, y);
//        System.out.println(X);
//    }
//}

public class begin2 {
    public static void main(String[] args){
        int x = 5;
        int y = 9;
        boolean m = compare(x,y);
        System.out.println(m);
    }

    /**Java中函数调用不用声明直接调用*/

    public static boolean compare(int a,int b){
        if(a == b)
            return true;
        return false;
    }
}
