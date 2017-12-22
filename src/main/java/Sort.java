import java.util.Arrays;
import java.util.Stack;

/**
 * 排序算法学习
 */
public class Sort {
    /**
     * day1 2017.12.20
     */


    /**
     * 直接插入排序
     * 直接插入排序的基本思想是：
     * 将数组中的所有元素依次跟前面已经排好的元素相比较，如果选择的元素比已排序的元素小，则交换，直到全部元素都比较过为止。
     * <p>
     * 785
     * 78?
     * 785
     * 58?
     * 758
     * 57?
     * 578
     */


    public static void main(String[] args) {
        int[] arr1 = new int[]{7, 8, 5, 9, 6, 3};
        int[] arr2 = new int[]{2, 5, 7, 8, 9, 0, 4};
        quickSortByStack(arr1);
        quickSortByStack(arr2);
    }

    public static void insertSort1(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    //swap
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    break;
                }
            }
        }

        System.out.println(Arrays.toString(arr));
    }

    //其他写法
    public static void insertSort2(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            for (; j >= 0 && array[j] > temp; j--) {
                //将大于temp的值整体后移一个单位
                array[j + 1] = array[j];
            }
            array[j + 1] = temp;
        }
        System.out.println(Arrays.toString(array) + " insertSort");
    }

    /**
     * 希尔排序
     * 将待排序数组按照步长gap进行分组，然后将每组的元素利用直接插入排序的方法进行排序；
     * 每次再将gap折半减小，循环上述操作；当gap=1时，利用直接插入，完成排序
     */
    public static void shellSort(int[] array) {


        for (int step = array.length / 2; step >= 1; step /= 2) {
            for (int i = step; i < array.length; i++) {
                int temp = array[i];
                int j = i - step;
                for (; j >= 0 && array[j] > temp; j -= step) {
                    array[j + step] = array[j];
                }
                array[j + step] = temp;
            }

        }
        System.out.println(Arrays.toString(array) + " shellSort");


        /*Wiki上步长的写法，3分法
         int gap = 1;
         int len = array.length;
         while (gap < len / 3) { gap = gap * 3 + 1; }
         for (; gap > 0; gap /= 3) {
         ...
         }
         */

    }

    /**
     * day2 2017.12.21
     */


    /**
     冒泡排序
     ①. 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     ②. 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     ③. 针对所有的元素重复以上的步骤，除了最后一个。
     ④. 持续每次对越来越少的元素重复上面的步骤①~③，直到没有任何一对数字需要比较。
     */
    public static void bubbleSort(int[] arr){
        for(int k=arr.length;k>0;k--){
            for(int i=0;i<k-1;i++){
                if(arr[i] > arr[i+1]){
                    int temp =arr[i];
                    arr[i]=arr[i+1];
                    arr[i+1]=temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr) + " bubbleSort");

    }

    /**
     * 快速排序
     ①. 从数列中挑出一个元素，称为"基准"（pivot）。第一个元素
     ②. 重新排序数列，所有比基准值小的元素摆放在基准前面，所有比基准值大的元素摆在基准后面（相同的数可以到任一边）。
     在这个分区结束之后，该基准就处于数列的中间位置。这个称为分区（partition）操作。
     low++,high--,比较大小并交换
     ③. 递归地（recursively）把小于基准值元素的子数列和大于基准值元素的子数列排序。
     */
    public static void quickSort(int[] arr){
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr) + " quickSort");
    }

    private static void quickSort(int[] arr,int low, int high){
        if(arr.length<=0) return;
        if(low>=high) return;

        int middle =getMiddle(arr,low,high);

        quickSort(arr,low,middle-1);
        quickSort(arr,middle+1,high);
    }

    /**
     * 快速排序 非递归实现
     *
     */
    public static void quickSortByStack(int[] arr){
        if(arr.length <= 0) return;
        Stack<Integer> stack = new Stack<Integer>();

        //初始状态的左右指针入栈
        stack.push(0);
        stack.push(arr.length - 1);
        while(!stack.isEmpty()){
            int high = stack.pop();     //出栈进行划分
            int low = stack.pop();

            int pivotIdx = partition(arr, low, high);

            //保存中间变量
            if(pivotIdx > low) {
                stack.push(low);
                stack.push(pivotIdx - 1);
            }
            if(pivotIdx < high && pivotIdx >= 0){
                stack.push(pivotIdx + 1);
                stack.push(high);
            }
        }
        System.out.println(Arrays.toString(arr) + " quickSortByStack");
    }

    private static int partition(int[] arr, int low, int high){
        if(arr.length <= 0) return -1;
        if(low >= high) return -1;
        return getMiddle(arr,low,high);
    }

    private static int getMiddle(int[] list, int low, int high) {
        int tmp = list[low];    //数组的第一个作为中轴
        while (low < high) {
            while (low < high && list[high] >= tmp) {
                high--;
            }


            list[low] = list[high];   //比中轴小的记录移到低端
            while (low < high && list[low] <= tmp) {
                low++;
            }


            list[high] = list[low];   //比中轴大的记录移到高端
        }
        list[low] = tmp;              //中轴记录到尾
        return low;                  //返回中轴的位置
    }


    /**
     * 简单选择排序
     ①. 从待排序序列中，找到关键字最小的元素；
     ②. 如果最小元素不是待排序序列的第一个元素，将其和第一个元素互换；
     ③. 从余下的 N - 1 个元素中，找出关键字最小的元素，重复①、②步，直到排序结束。
     */
    public static void selectSort(int[] arr){
       for(int k=0;k<arr.length;k++){
           for(int i=k;i<arr.length;i++){
               int min =arr[k];
               if(arr[i] <arr[k]){
                   int temp =arr[i];
                   arr[i]=arr[k];
                   arr[k]=temp;
               }
           }
       }
        System.out.println(Arrays.toString(arr) + " selectSort");

    }
}
