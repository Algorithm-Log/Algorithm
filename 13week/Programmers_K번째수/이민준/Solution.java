import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i = 0; i < commands.length; i++){
            int start = commands[i][0]-1;
            int end = commands[i][1]-1;
            int k = commands[i][2]-1;
            
            int[] arr = new int[end-start+1];
            int idx = 0;
            for(int s = start; s <= end; s++){
                arr[idx++] = array[s];
            }
            
            Arrays.sort(arr);
            answer[i] = arr[k];
        }
        
        return answer;
    }
}