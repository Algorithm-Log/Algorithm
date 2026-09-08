import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int n = jobs.length;
        int[][] tasks = new int[n][3];

        for (int i = 0; i < n; i++) {
            tasks[i][0] = jobs[i][0];
            tasks[i][1] = jobs[i][1];
            tasks[i][2] = i;
        }

        Arrays.sort(tasks, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[2], b[2]);
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[2], b[2]);
        });

        int time = 0;
        int idx = 0;
        int end = 0;
        long total = 0;

        while (end < n) {
            while (idx < n && tasks[idx][0] <= time) {
                pq.offer(tasks[idx]);
                idx++;
            }

            if (pq.isEmpty()) {
                time = tasks[idx][0];
                continue;
            }

            int[] job = pq.poll();
            time += job[1];
            total += time - job[0];
            end++;
        }

        answer = (int) total / n;
        return answer;
    }
}
