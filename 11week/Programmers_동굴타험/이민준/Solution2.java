import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {

        /*
         * tree  : 실제 동굴의 연결 관계 (양방향 그래프)
         * graph : 위상정렬에 사용할 선행 관계 (방향 그래프)
         *
         * 예시)
         * 실제 동굴: 0 - 1 - 2
         *
         * tree[0] = [1]
         * tree[1] = [0, 2]
         * tree[2] = [1]
         *
         * 0번부터 탐험해야 하므로 방향을 정하면
         *
         * graph[0] = [1]
         * graph[1] = [2]
         * graph[2] = []
         */

        List<Integer>[] graph = new ArrayList[n];
        List<Integer>[] tree = new ArrayList[n];

        // 각 정점마다 인접 리스트 생성
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            tree[i] = new ArrayList<>();
        }

        /*
         * 1. 양방향 그래프 생성
         *
         * path는 실제 동굴의 연결 통로를 나타냄.
         *
         * 예) path = [[0,1], [1,2]]
         *
         * 0 <-> 1 <-> 2
         *
         * 양쪽으로 이동할 수 있으므로
         * 두 정점 모두에 상대 정점을 저장한다.
         */
        for (int[] p : path) {
            tree[p[0]].add(p[1]);
            tree[p[1]].add(p[0]);
        }

        /*
         * indegree[v]
         * = v번 방을 방문하기 전에 먼저 처리해야 하는 방의 개수
         *
         * 예)
         * 0 -> 1
         * 2 -> 1
         *
         * indegree[1] = 2
         *
         * 0번과 2번을 모두 방문해야
         * 1번의 진입차수가 0이 된다.
         */
        int[] indegree = new int[n];

        // 방향 그래프를 생성할 때 중복 탐색 방지
        boolean[] visited = new boolean[n];

        Queue<Integer> que = new ArrayDeque<>();

        /*
         * 2. 동굴의 양방향 그래프를 방향 그래프로 변환
         *
         * 실제 동굴에서는 양방향 이동이 가능하지만
         * 탐험은 반드시 0번 방부터 시작한다.
         *
         * 따라서 0번을 루트로 하는 트리를 만들고,
         * 부모 -> 자식 방향으로 간선을 설정한다.
         *
         * 이 방향은 실제 이동 가능 방향이 아니라
         * "부모 방을 먼저 방문해야 자식 방에 도달할 수 있다"
         * 라는 선행 관계를 의미한다.
         */

        que.offer(0);
        visited[0] = true;

        while (!que.isEmpty()) {
            int cur = que.poll();

            // 현재 방과 연결된 모든 방 확인
            for (int next : tree[cur]) {

                // 이미 확인한 방이면 건너뜀
                // 부모 방향으로 되돌아가는 것도 방지
                if (visited[next]) continue;

                visited[next] = true;

                /*
                 * cur -> next 방향 간선 추가
                 *
                 * next에 도달하려면 cur을 먼저 방문해야 함.
                 */
                graph[cur].add(next);

                /*
                 * next의 진입차수 증가
                 *
                 * next를 방문하기 전에
                 * cur을 먼저 방문해야 한다는 조건이 추가됨.
                 */
                indegree[next]++;

                // next의 자식 방도 탐색하기 위해 큐에 추가
                que.offer(next);
            }
        }

        /*
         * 3. 문제에서 주어진 선행 방문 조건 추가
         *
         * order[i] = [a, b]
         *
         * a번 방을 방문한 뒤 b번 방을 방문할 수 있음.
         *
         * 따라서 a -> b 방향 간선을 추가하고
         * b의 진입차수를 1 증가시킨다.
         *
         * 예)
         * 기존 그래프:
         *
         * 0 -> 1
         * 0 -> 2
         *
         * order = [[2,1]]
         *
         * 추가되는 간선:
         * 2 -> 1
         *
         * indegree[1] = 2
         *
         * 이제 1번을 방문하려면
         * 0번과 2번을 모두 먼저 방문해야 한다.
         */
        for (int[] o : order) {
            graph[o[0]].add(o[1]);
            indegree[o[1]]++;
        }

        /*
         * 4. 시작점 방문 가능 여부 확인
         *
         * 동굴 탐험은 반드시 0번부터 시작해야 한다.
         *
         * 만약 0번의 진입차수가 1 이상이라면
         * 다른 방을 먼저 방문해야 0번에 들어갈 수 있다는 뜻.
         *
         * 시작점이 잠겨 있으므로 탐험 자체가 불가능하다.
         */
        if (indegree[0] != 0) return false;

        /*
         * 5. 위상정렬 (Kahn 알고리즘)
         *
         * 핵심 원리:
         *
         * 진입차수가 0인 정점은
         * 먼저 방문해야 하는 모든 방을 처리한 상태다.
         *
         * 따라서 해당 방을 큐에서 꺼내 방문 처리하고
         * 연결된 다음 방들의 진입차수를 감소시킨다.
         *
         * 진입차수가 0이 된 방은
         * 모든 선행 조건을 충족했으므로 큐에 추가한다.
         */

        // 앞의 BFS가 종료되었으므로 큐는 비어 있음
        que.offer(0);

        // 위상정렬로 처리한 방의 개수
        int cnt = 0;

        while (!que.isEmpty()) {
            int cur = que.poll();

            // 현재 방의 모든 선행 조건이 충족되었음
            cnt++;

            // 현재 방을 먼저 방문해야 하는 다음 방들 확인
            for (int next : graph[cur]) {

                /*
                 * cur을 방문했으므로
                 * next의 선행 조건 하나가 충족됨.
                 *
                 * 예)
                 * indegree[next] = 2
                 *
                 * cur 방문 후
                 * indegree[next] = 1
                 */
                indegree[next]--;

                /*
                 * 진입차수가 0이라는 것은
                 * next의 모든 선행 조건이 충족되었다는 의미.
                 *
                 * 따라서 이제 next를 방문할 수 있음.
                 */
                if (indegree[next] == 0) {
                    que.offer(next);
                }
            }
        }

        /*
         * 6. 모든 방 방문 가능 여부 판단
         *
         * cnt == n
         * -> 모든 방을 처리했으므로 true
         *
         * cnt < n
         * -> 방문하지 못한 방이 존재하므로 false
         *
         * 선행 조건에 사이클이 발생하면
         * 해당 방들의 진입차수가 0이 되지 않아
         * 큐에 들어가지 못한다.
         */
        return cnt == n;
    }
}
