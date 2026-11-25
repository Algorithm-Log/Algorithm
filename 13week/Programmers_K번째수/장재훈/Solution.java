import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int a = 0; a < commands.length; a++) {
            int i = commands[a][0] - 1;
            int j = commands[a][1];
            int k = commands[a][2] - 1;
            
            int[] temp = Arrays.copyOfRange(array, i, j);
            Arrays.sort(temp);
            
            answer[a] = temp[k];
        }
        
        return answer;
    }
}