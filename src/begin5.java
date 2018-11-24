import java.lang.reflect.Array;

/**
 * 知识点：数组、集合
 *    折半查（二分查找）找要求数组必须有序，
 * 需求：获取数组长度，获取数组中最大元素，对数组中元素进行排序，数组的查找和折半查找。
 */

public class begin5 {
    public static void main(String[] args) {
        //以下三种方式创建数组的效果相同，但是不推荐使用第一种方式
        int arr[] = new int[2];
        int[] arry = new int[]{3, 4, 6, 77, 231}; //此处[]中尽量不添加长度
        int[] ryy = {43, 23, 6, 7, 5, 4, 3};

        int[] ayy = new int[3];
        ayy[0] = 3;
        ayy[1] = 4;
        ayy[2] = 5;
        System.out.println(ayy[2]);
        arr = null;
        System.out.println(arr);
        for (int i = 0;i < 5;i++){
            System.out.println("arry["+ i + "]="+arry[i]+";");  // + 号起连接作用
        }
        System.out.println(arry[3]);
        System.out.println("\n\n");

        //获取数组长度
        System.out.println("length:"+arry.length);

        int sum = 0;
        for(int j = 0;j < arry.length;j ++){
            sum = sum + arry[j];
            System.out.println("arry["+ j + "] = "+ arry[j] + ";");
        }
        //输出arry数组所有元素的和
        System.out.println(sum);

        for (int k = 0;k < arry.length;k ++){
            if (k != arry.length - 1){
                System.out.print(arry[k] + "、");
            }
            else
                System.out.print(arry[k]);
        }
        System.out.println(".......................");

        //输出数组中最大元素
        int max = arry[0];
        for (int i = 0;i < arry.length;i ++){
            if (arry[i] >max) {
                max = arry[i];
            }
        }
        System.out.println(max);

        // 数组元素排序（选择排序）
        for ( int i = 0;i < arry.length;i ++ ){
            for ( int j = i + 1;j <arry.length;j ++ ){
                if (arry[i] > arry[j]) {
                    int temp = arry[i];
                    arry[i] = arry[j];
                    arry[j] = temp;
                }
            }
            System.out.println(arry[i]);
        }

        //数组元素排序（冒泡排序）
        for ( int i = 0;i <arry.length;i ++ ){
            for ( int j = 0;j < arry.length - 1;j ++ ) {  // 让每一次比较的元素减少，-1，避免角标越界
                if (arry[j] > arry[j + 1]) {
                    int temp = arry[j];
                    arry[j] = arry[j + 1];
                    arry[j + 1] = temp;
                }
            }
            System.out.println(arry[i]);
        }

        // Array.sort(arry); :java中已经定义好的的数组排序方法，开发中常使用，目前先不考虑

        //1查找元素，获取key第一次出现在数组中的位置，若返回 -1 则代表key在数组中不存在
        int index = getIndex(arry,5);
        System.out.println("index..." + index); //输出index...-1，因为数组中没有5

        //折半查找，前提是数组必须有序
        int[] c = {1,3,5,7,9,23,78,100};
        int A = halfsearch(c,9);
        System.out.println("A" + A);

        arry = null;
        System.out.println(arry);
        System.out.println(ayy); // [@1540e19d : 数组类型的数据，1540e19d 为数组内存的存放地址
    }

    //1查找的函数
    public static int getIndex(int[] arr,int key) {
        for (int x = 0;x <arr.length;x ++){
            if (arr[x] == key)
                return x;
        }
        return -1;
    }

    //折半查找函数
    public static int halfsearch(int[] arry,int key){
        int min,max,mid;
        min = 0;
        max = arry.length - 1;
        mid = ( min + max ) / 2;
        while(arry[mid] != key){
            if (key > arry[mid])
                min = mid + 1;
            else if (key < arry[mid])
                max = mid - 1;

            if(min > max)
                mid = (min + max) / 2;
        }
        return mid;
    }
}
