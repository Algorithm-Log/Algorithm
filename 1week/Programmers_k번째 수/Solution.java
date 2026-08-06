import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for(int i = 0; i < commands.length; i++){
            List<Integer> list = new ArrayList<>();
            int st = commands[i][0] - 1;
            int end = commands[i][1];
            for(int j = st; j < end; j++){
                list.add(array[j]);
            }
            int[] arr = new int[list.size()];
            for(int j = 0; j < list.size(); j++){
                arr[j] = list.get(j);
            }
            Arrays.sort(arr);
            answer[i] = arr[commands[i][2]-1];
        }
        
        return answer;
    }
}