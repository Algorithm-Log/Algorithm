import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {

        int commandCount = commands.length;
        int[] answer = new int[commandCount];

        for (int cmdIdx = 0; cmdIdx < commandCount; cmdIdx++) {
            int i = commands[cmdIdx][0];
            int j = commands[cmdIdx][1];
            int k = commands[cmdIdx][2];

            int subArrayLen = j - i + 1;
            int[] subArray = new int[subArrayLen];

            int copyIdx = 0;
            for (int arrIdx = i - 1; arrIdx < j; arrIdx++) {
                subArray[copyIdx++] = array[arrIdx];
            }

            Arrays.sort(subArray);
            answer[cmdIdx] = subArray[k - 1];
        }

        return answer;
    }
}
