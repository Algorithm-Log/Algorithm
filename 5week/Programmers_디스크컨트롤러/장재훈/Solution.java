import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // [["작업 시작하는 시점", "작업 소요 시간"]]
        int answer = 0; // 모든 요청 작업의 반환 시간의 평균 (종료시점 - 요청시점)
        
        Job[] list = new Job[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            list[i] = new Job(jobs[i][1], jobs[i][0], i);
        }
        // 요청시각을 기준으로 list를 정렬
        Arrays.sort(list, new Comparator<Job>() {
            @Override
            public int compare(Job a, Job b) {
                // 정렬 우선순위 1: 처리 요청 시각
                if (a.stTime == b.stTime) {
                    // 정렬 우선순위 2: 처리 소요 시간
                    if (a.reqTime == b.reqTime) {
                        // 정렬 우선순위 3: 작업 번호
                        return a.order - b.order;
                    }
                    return a.reqTime - b.reqTime;
                }
                return a.stTime - b.stTime;
            }
        });
        
        PriorityQueue<Job> pq = new PriorityQueue<>(new JobComparator());
        
        boolean[] visited = new boolean[jobs.length]; // 해당 프로세스가 작업 완료 됐는지 판단
        
        pq.offer(new Job(list[0].reqTime, list[0].stTime, list[0].order));
        visited[0] = true;
        int cnt = 0;
        int time = list[0].stTime;
        
        while(cnt < jobs.length) {
            // 지금 시간 전에 들어온 프로세스를 찾아서 pq(하드디스크)에 넣음
            for (int i = 0; i < jobs.length; i++) {
                if (!visited[i] && list[i].stTime <= time) {
                    pq.offer(new Job(list[i].reqTime, list[i].stTime, list[i].order));
                    visited[i] = true;
                }
            }
            // pq(하드디스크)가 비어있으면서 작업해야할 프로세스가 남은 경우
            if (pq.isEmpty()) {
                for (int i = 0; i < visited.length; i++) {
                    // 아직 처리하지 않은 다음에 작업해야할 프로세스를 pq에 집어넣음
                    if (!visited[i]) {
                        pq.offer(new Job(list[i].reqTime, list[i].stTime, list[i].order));
                        time = list[i].stTime;
                        visited[i] = true;
                        break;
                    }
                }
            }
            // pq(하드디스크)에 처리해야할 프로세스가 있을 때
            else {
                Job j = pq.poll();
                time += j.reqTime; // 작업한 시간만큼 시간을 더함
                answer += time - j.stTime; // RTT 계산
                cnt++;
            }
        }
        
        // 모든 요청 작업의 반환 시간의 평균의 정수부분을 반환
        return answer/jobs.length;
    }
    
    public class Job {
        int reqTime; // 소요시간
        int stTime; // 시작 시각
        int order; // 요청 순서
        
        public Job (int reqTime, int stTime, int order) {
            this.reqTime = reqTime;
            this.stTime = stTime;
            this.order = order;
        }
    }
    
    // 우선순위 큐의 우선순위 선언
    public class JobComparator implements Comparator<Job> {
        @Override
        public int compare(Job a, Job b) {
            // 정렬 우선순위 1: 처리 소요 시간
            if (a.reqTime == b.reqTime) {
                // 정렬 우선순위 2: 처리 요청 시각
                if (a.stTime == b.stTime) {
                    // 정렬 우선순위 3: 작업 번호
                    return a.order - b.order;
                }
                return a.stTime - b.stTime;
            }
            return a.reqTime - b.reqTime;
        }
    }
}

// 알고리즘
// 자료구조: 무조건 Priority Queue
// 조건
// 1. 대기 큐가 비어있지 않아야 한다.
// 1-1. 큐 안에는 소요시간이 짧은 순, 요청 시각이 빠른 순, 작업 번호가 작은 순 총 3가지를 기준으로 정렬
// 2. 작업을 마친 시점에 큐를 우선순위에 맞게 다시 정렬한다.