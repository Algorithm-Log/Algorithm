import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        for(int i = 0; i <= citations.length; i++){ //후보
            int cnt = 0; //인용 횟수
            for(int j = 0; j < citations.length; j++){
                if(citations[j] >= i)   cnt++;
            }
            if(cnt >= i){
                answer = i;
            }
        }
        
        return answer;
    }
}