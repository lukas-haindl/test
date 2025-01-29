import java.util.Arrays;

public class ep1test3_4 {

    public static int[][] rearrange(int[][] inputArray){
        boolean isLeft = true;
        int itemsCount = 0;
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                itemsCount ++;
            }
        }
        int[] tmpArray = new int[itemsCount];
        int index = 0;
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                tmpArray[index] = inputArray[i][j];
                index ++;
            }
        }
        int[][] outputArray = new int[(int) Math.round(itemsCount / 4.0)][4];
        index = 0;
        int tmpIndex = 0;
        for (int i = 0; i < outputArray.length; i++) {
            for (int j = 0; j < outputArray[i].length; j++) {
                outputArray[i][index] = tmpIndex < tmpArray.length ? tmpArray[tmpIndex] : 0;
                tmpIndex ++;
                if (isLeft){
                    index++;
                } else{
                    index--;
                }
            }
            isLeft = !isLeft;
            if (isLeft) {
                index = 0;
            } else{
                index = 3;
            }
        }
        return outputArray;
    }

    public static void label(int[][] inputArray){
        int oldLength = Integer.MIN_VALUE;
        boolean change = false;
        for (int i = 0; i < inputArray.length; i++) {
            if (oldLength == inputArray[i].length){
                change = true;
                for (int j = 0; j < inputArray[i].length; j++) {
                    if (inputArray[i - 1][j] != inputArray[i][j]){
                        change = false;
                    }
                }
                if (change){
                    for (int j = 0; j < inputArray[i - 1].length; j++) {
                        inputArray[i - 1][j] = -9;
                    }
                }
            }
            oldLength = inputArray[i].length;
        }
    }

    public static int findMaxOppositeSum(int[] sequence, int start, int end){
        if (start < end){
            return Math.max(sequence[start] + sequence[end], findMaxOppositeSum(sequence, ++start, --end));
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        int[][] test1 = {{5, 2, 4}, {8, 5, 4}, {9, 6, 8, 7}};
        int[][] test2 = {{0, 1, 2}, {0, 1, 2}, {0, 1, 2}};
        int[][] test3 = {{6}, {2, 4}, {2, 4}, {2, 4}, {4, 2}};
        int[] seq1 = {4, 3, 2, 1, 10, 5, 5, 5};
        int[][] result1 = rearrange(test1);
        System.out.println(Arrays.deepToString(result1));
        int[][] result2 = rearrange(test3);
        System.out.println(Arrays.deepToString(result2));
        System.out.println(Arrays.deepToString(rearrange(new int[][]{{}})));
        label(test1);
        System.out.println(Arrays.deepToString(test1));
        label(test2);
        System.out.println(Arrays.deepToString(test2));
        label(test3);
        System.out.println(Arrays.deepToString(test3));
        System.out.println(findMaxOppositeSum(seq1, 0, 7));
        System.out.println(findMaxOppositeSum(seq1, 0, 5));
        System.out.println(findMaxOppositeSum(seq1, 4, 7));
    }
}
