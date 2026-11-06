import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {

        List<Integer>[] graph = new ArrayList[n];
        List<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        // 1. 양방향 그래프 생성
        for (int[] p : path) {
            tree[p[0]].add(p[1]);
            tree[p[1]].add(p[0]);
        }

        int[] indegree = new int[n];
        boolean[] visited = new boolean[n];

        Queue<Integer> que = new ArrayDeque<>();
        que.offer(0);
        visited[0] = true;

        // 2. 0번을 루트로 방향 그래프 생성
        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int next : tree[cur]) {
                if (visited[next]) continue;

                visited[next] = true;

                graph[cur].add(next);
                indegree[next]++;

                que.offer(next);
            }
        }

        // 3. 선행 방문 조건 추가
        for (int[] o : order) {
            graph[o[0]].add(o[1]);
            indegree[o[1]]++;
        }

        // 4. 위상정렬
        que.offer(0);
        int cnt = 0;

        while (!que.isEmpty()) {
            int cur = que.poll();
            cnt++;

            for (int next : graph[cur]) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    que.offer(next);
                }
            }
        }

        return cnt == n;
    }
}