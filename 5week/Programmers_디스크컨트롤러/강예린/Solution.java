import java.util.*;

class Solution {

    public int solution(int[][] jobs) {
        // 요청 시간 순으로 원본 배열 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        Queue<Process> q = new PriorityQueue<>();
        
        int n = jobs.length;
        int now = 0;   // 현재 시각
        int total = 0; // 각 작업의 반환 시간 합계
        int cnt = 0;   // 완료된 작업 수
        int qidx = 0;  // jobs 배열 탐색 인덱스
        
        while (cnt < n) {
            // 현재 시간 이전에 들어온 모든 작업을 큐에 담기
            while (qidx < n && jobs[qidx][0] <= now) {
                q.offer(new Process(qidx, jobs[qidx][0], jobs[qidx][1]));
                qidx++;
            }
            
            if (!q.isEmpty()) {
                // 대기 중인 작업 중 가장 짧은 것 실행
                Process p = q.poll();
                // 작업 완료 후 시간 계산: 현재 시각 + 소요 시간
                now += p.time;
                // 반환 시간: (실제 끝난 시간 - 요청 시점)
                total += (now - p.start);
                cnt++;
            } else {
                // 큐가 비어있다면 다음 작업이 시작시간으로 
                now = jobs[qidx][0];
            }
        } 
        
        return total / n;
    }
    
  
    static class Process implements Comparable<Process> {
        int num, start, time;
        
        Process(int num, int start, int time) {
            this.num = num;
            this.start = start;
            this.time = time;
        }
        
        @Override
        public int compareTo(Process o) {
            // 소요 시간 짧은 순
            if (this.time != o.time) {
                return this.time - o.time;
            }
            // 요청 시간 빠른 순
            if (this.start != o.start) {
                return this.start - o.start;
            }
            // 인덱스 작은 순
            return this.num - o.num;
        }
    }
}