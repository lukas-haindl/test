import java.util.Arrays;

public class ep1test3_1 {

    public static int[][] generate(int[][] input){
        int[][] array = new int[input.length * 2 - 1][];
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (i < input.length){
                array[i] = new int[input[i].length - i];
                if (array[i].length > 1){
                    index = i;
                }
                for (int j = 0; j < input[i].length - i; j++) {
                    array[i][j] = input[i][j];
                }
            } else{
                array[i] = new int[array[index].length];
                for (int j = 0; j < array[index].length; j++) {
                    array[i][j] = array[index][j];
                }
                index--;
            }
        }
        return array;
    }
    
    public static void reorder(int[][] input){
        for (int[] array : input) {
            int beginIndex = 0;
            int endIndex = array.length - 1;
            while (beginIndex < endIndex) {
                int temp = array[beginIndex];
                array[beginIndex] = array[endIndex];
                array[endIndex] = temp;
                beginIndex++;
                endIndex--;
            }
        }
        System.out.println(Arrays.deepToString(input));
    }

    public static boolean isPresentNTimes(String sequence, char marker, int count){
        if (sequence.isEmpty())
            return count == 0;
        else {
            count -= sequence.charAt(0) == marker ? 1 : 0;
            return isPresentNTimes(sequence.substring(1), marker, count);
        }
    }

    public static void main(String[] args) {
        int[][] test1 = { {5, 2, 4}, {2, 7, 3}, {9, 5, 8} };
        int[][] test2 = { {1} };
        int[][] test3 = { {6, 7, 8}, {7, 5, 3, 1}, {2} };
        String seq1 = "ABBAACBA";
        int[][] result1 = generate(test1);
        int[][] result2 = generate(test2);
        System.out.println(Arrays.deepToString(result1));
        System.out.println(Arrays.deepToString(result2));
        reorder(result1);
        reorder(result2);
        reorder(test3);
        System.out.println(isPresentNTimes(seq1, 'A', 4));
        System.out.println(isPresentNTimes(seq1, 'A', 3));
        System.out.println(isPresentNTimes(seq1, 'A', 5));
        System.out.println(isPresentNTimes(seq1, 'Z', 0));
    }
}
