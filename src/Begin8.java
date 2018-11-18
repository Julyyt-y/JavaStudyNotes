/**
 *知识点：main函数
 * public static void main(String[] args)
 * 主函数，是一个特殊的函数，作为程序的入口，可以被jvm调用
 *
 * 主函数的定义：
 * public：代表着该函数访问权限是最大的
 * static：代表着主函数随着类的加载就已经存在了
 * void：主函数没有具体的返回值
 * main：不是关键字，但是一个特殊的单词，可以被jvm识别
 * 函数的参数(String[] args)：参数类型是一个字符串类型数组，该数组中的元素是字符串,
 *   args = argments ,
 *
 * 主函数是固定格式的，jvm识别
 * jvm：虚拟机,jvm在调用主函数时，传入的是new String[0];
 */
public class Begin8 {
    public static void main(String[] args){

        System.out.println("Hello Java");
        System.out.println(args);
        System.out.println(args.length);

    }

    public static void main(int x){

        System.out.println("hello java");
    }
}
class MainTest{
    public static void main(String[] args){
        for(int x= 0;x < args.length;x ++){
            System.out.println(args[x]);
        }
    }
}
