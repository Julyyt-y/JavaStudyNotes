/**
 * 知识点：循环
 * 此处主要说的是C语言里面没有的循环：Foreach循环
 * 这种循环遍历数组和集合更加简洁。
 * 使用foreach循环遍历数组或集合的元素时：
 * 1.无需获得数组或集合的长度；
 * 2.无需根据索引来访问数组元素或集合元素，
 * foreach循环自动遍历数组或集合的每个元素。
 *
 *  foreach循环和普通循环的区别：
 *  foreach循环无需循环条件，无需循环迭代语句，因为这些部分都是由系统来完成的，
 *  foreach循环自动迭代数组的每一个元素，当每个元素都被迭代一次后，foreach循环自动结束。
 *
 *  注意：使用foreach循环时，通常不要对循环遍历赋值，原因：语法上允许，但没有太大意义，且易引起错误。
 */

public class begin3 {
    public static void main(String[] args){
        String[] arr = {"java","book","aaa","fff"};
        for (String arrs : arr) {
            System.out.println(arrs);
        }
    }
}
