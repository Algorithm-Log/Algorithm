import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        Arrays.sort(routes, (r1, r2)-> r1[0]- r2[0]);
        int cnt = 0;
        int start= Integer.MIN_VALUE;
        int end= Integer.MIN_VALUE;
        
        for(int[] route: routes){
            
            // 다음의 시작이 전의 끝보다 크면 +1하고 새로운 구간
            if(route[0]> end) {
                start= route[0];
                end= route[1];
                cnt ++;
                continue;
            }
            
            // 다음의 시작이 시작보다 크거나 같으면 겹치는 부분의 새로운 시작
            if(route[0]>= start) start= route[0];            
            if(route[1]<= end) end= route[1];
            
        }
        
        return cnt;
    }
}