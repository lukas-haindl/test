import java.util.Arrays;

public class ep1test3_5 {

    public static int[][] removeLeadingZeros(int[][] inputArray) {
        int[][] outputArray = new int[inputArray.length][];
        for (int i = 0; i < inputArray.length; i++) {
            int diff = 0;
            for (int j = 0; j < inputArray[i].length; j++) {
                if (inputArray[i][j] == 0) {
                    diff++;
                } else {
                    break;
                }
            }
            outputArray[i] = new int[inputArray[i].length - diff];
            int index = 0;
            for (int j = diff; j < inputArray[i].length; j++) {
                outputArray[i][index] = inputArray[i][j];
                index++;
            }
        }
        return outputArray;
    }

    public static void mask(int[][] inputArray, int[] rows, int[] cols) {
        for (int row : rows) {
            for (int col : cols) {
                if (inputArray.length > row && inputArray[row].length > col){
                    inputArray[row][col] = 0;
                }
            }
        }
    }

    public static String replicateCharacters(String sequence, String repSequence){
        if (!sequence.isEmpty()){
            String rep = repSequence.charAt(0) == '1' ? sequence.charAt(0) + "" : "";
            return sequence.charAt(0) + rep + replicateCharacters(sequence.substring(1), repSequence.substring(1));
        }
        return "";
    }

    public static void main(String[] args) {
        int[][] test1 = {{0, 2, 4}, {2, 0, 0}, {0, 0, 1}};
        int[][] test2 = {{1, 2, 3}, {1, 2, 3, 4, 5}, {1, 2, 3}, {1, 2, 3, 4, 5}};
        int[][] test3 = {{2}, {0, 7}, {6, 7, 8}, {6, 0}, {0, 0}};
        String seq1 = "ABA";
        int[][] result1 = removeLeadingZeros(test1);
        int[][] result2 = removeLeadingZeros(test3);
        System.out.println(Arrays.deepToString(result1));
        System.out.println(Arrays.deepToString(result2));
        mask(test2,new int[]{1,2,3},new int[]{0,1,4});
        mask(test3,new int[]{0,2,4},new int[]{0,1});
        mask(test3,new int[]{},new int[]{0,1});
        System.out.println(Arrays.deepToString(test2));
        System.out.println(Arrays.deepToString(test3));
        System.out.println(Arrays.deepToString(test1));
        System.out.println(replicateCharacters(seq1, "010"));
        System.out.println(replicateCharacters("SAMBA", "10001"));
    }
}
