import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int n = jobs.length;

        // [요청 시각, 소요 시간, 작업 번호]
        // 정렬 이후에도 원래 작업 번호를 유지하기 위해 별도 배열 사용
        int[][] tasks = new int[n][3];

        for (int i = 0; i < n; i++) {
            tasks[i][0] = jobs[i][0];
            tasks[i][1] = jobs[i][1];
            tasks[i][2] = i;
        }

        // 아직 대기 큐에 들어오지 않은 작업을 순서대로 확인할 수 있도록
        // 요청 시각이 빠른 순으로 정렬
        Arrays.sort(tasks, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[2], b[2]);
        });

        // 현재 시각까지 요청된 작업들을 저장하는 대기 큐
        // 우선순위: 소요 시간 -> 요청 시각 -> 작업 번호
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[2], b[2]);
        });

        int time = 0;       // 현재 시각
        int idx = 0;        // 아직 대기 큐에 넣지 않은 다음 작업의 인덱스
        int end = 0;        // 처리가 완료된 작업 수
        long total = 0;     // 모든 작업의 반환 시간 합

        while (end < n) {

            // 현재 시각까지 요청된 작업을 모두 대기 큐에 추가
            // 작업이 끝나는 시각과 요청 시각이 같아도 바로 처리 후보가 됨
            while (idx < n && tasks[idx][0] <= time) {
                pq.offer(tasks[idx]);
                idx++;
            }

            // 현재 처리할 수 있는 작업이 없다면
            // 다음 작업의 요청 시각까지 시간을 바로 이동
            if (pq.isEmpty()) {
                time = tasks[idx][0];
                continue;
            }

            // 현재 대기 중인 작업 중 우선순위가 가장 높은 작업 처리
            int[] job = pq.poll();

            time += job[1];

            // 반환 시간 = 작업 종료 시각 - 작업 요청 시각
            total += time - job[0];

            end++;
        }

        // 모든 작업의 평균 반환 시간의 정수 부분
        answer = (int) (total / n);

        return answer;
    }
}
