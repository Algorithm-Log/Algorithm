import java.util.*;

class Solution {
    List<List<Integer>> list = new ArrayList<>();
    boolean[] visited;

    public boolean solution(int n, int[][] path, int[][] order) {
        visited = new boolean[n];

        // 전처리
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        // 양방향 그래프 만들기
        for (int i = 0; i < n - 1; i++) {
            list.get(path[i][0]).add(path[i][1]);
            list.get(path[i][1]).add(path[i][0]);
        }

        // 선방문 조건 및 대기 방 관리
        int[] before = new int[n];
        int[] waiting = new int[n];

        Arrays.fill(before, -1);
        Arrays.fill(waiting, -1);

        for (int i = 0; i < order.length; i++) {
            before[order[i][1]] = order[i][0];
        }

        // 시작점 0번에 선방문 조건이 있으면 불가능
        if (before[0] != -1) return false;

        Queue<Integer> que = new ArrayDeque<>();
        que.offer(0);

        int cnt = 0;

        while (!que.isEmpty()) {
            int v = que.poll();

            if (visited[v]) continue;

            // 선방문 조건 확인
            int pre = before[v];

            if (pre != -1 && !visited[pre]) {
                waiting[pre] = v;
                continue;
            }

            visited[v] = true;
            cnt++;

            if (cnt == n) return true;

            // 현재 방을 기다리고 있던 방을 큐에 추가
            if (waiting[v] != -1) {
                que.offer(waiting[v]);
                waiting[v] = -1;
            }

            // 연결된 방 탐색
            for (int nextV : list.get(v)) {
                if (visited[nextV]) continue;

                que.offer(nextV);
            }
        }

        return false;
    }
}