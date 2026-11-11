import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int bCnt = number.length() - k;   // 앞으로 뽑아야 할 개수
        int totalCnt = bCnt;              // 최종 답 길이

        int useCnt = number.length() - bCnt + 1;
        
        StringBuilder answer = new StringBuilder();
        
        while(true){
            char max = '0';
            int maxIdx = -1;
            for(int i = 0; i < useCnt; i++){
                if(max < number.charAt(i)){
                    max = number.charAt(i);
                    maxIdx = i;
                }
            }
            
            answer.append(max);
            if(answer.length() == totalCnt) break;
            bCnt--;
            
            number = number.substring(maxIdx+1, number.length());
            useCnt = number.length() - bCnt+1;
        }
        
        return answer.toString();
    }
}