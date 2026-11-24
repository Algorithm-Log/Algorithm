import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<commands.length;i++){

            int[] temp = new int[commands[i][1]-commands[i][0]+1];
            
            int index=0;

            for(int start=commands[i][0]-1;start<commands[i][1];start++){
                temp[index]=array[start];
                index++;
            }

            Arrays.sort(temp);

            list.add(temp[commands[i][2]-1]);
            
        }

        int[] answer = new int[list.size()];
        int index=0;

        for( int temp : list){
            answer[index++]=temp;
        }

        return answer;
    }
}