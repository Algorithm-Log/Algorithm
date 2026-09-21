import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        Arrays.sort(jobs, (a,b) -> a[0]==b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        // [1]과 [0]을 뺀 값 total에 더하기
        // 처음 한 시간 time 이전까지의 작업 시간[0] 중 제일 최소 시간[1]인 거 1번 작업 진행
        // 반복
        int time = jobs[0][1] + jobs[0][0];
        int total = jobs[0][1];
        
        boolean[] visited = new boolean[jobs.length];
        visited[0] = true;
        
        for(int i = 1; i < jobs.length; i++){
            int min = 1001;
            int minIdx = 100000;
            
            for(int j = 1; j < jobs.length; j++){ 
                if(jobs[j][0] <= time && !visited[j]){ // 지금까지 소요된 시간보다 작거나 같은 오출 시간을 가진 요소들
                    if(min > jobs[j][1]){
                        min = jobs[j][1];
                        minIdx = j;
                    }
                }
            }
            
            if(minIdx == 100000){ // time과 다음 작업 간에 틈이 있다면.
                for(int j = 0; j < jobs.length; j++){
                    if(!visited[j]) {
                        minIdx = j;
                        time = jobs[j][0];
                        break;
                    }
                }
            }
            
            visited[minIdx] = true;
            time += jobs[minIdx][1];
            total += time-jobs[minIdx][0];
        }
        
        return total / jobs.length;
    }
}