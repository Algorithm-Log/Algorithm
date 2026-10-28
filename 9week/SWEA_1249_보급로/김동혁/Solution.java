/*
 * [설계 전략]
 * 1. map[r][c]에는 각 칸의 복구 시간을 저장하고,
 *    time[r][c]에는 시작점에서 해당 칸까지 발견한 최소 누적 복구 시간을 저장한다.
 * 2. time을 Integer.MAX_VALUE로 초기화하고 시작점 (0, 0)만 0으로 설정한다.
 *    실제 복구 시간이 0인 경로도 있으므로 0을 미방문 표시로 사용하지 않는다.
 * 3. FIFO 큐에서 좌표를 꺼내 상하좌우를 확인한다.
 *    다음 좌표가 배열 범위 안인지 검사한 뒤, 다음 칸의 복구 시간을 더한다.
 * 4. 기존 비용보다 작은 비용을 찾았을 때만 time을 갱신하고 다시 큐에 넣는다.
 *    한 번 방문한 칸도 더 저렴한 경로가 발견되면 다시 탐색한다.
 * 5. 칸마다 비용이 다르므로 도착점에 처음 도달했다고 종료하지 않는다.
 *    큐가 빌 때까지 비용을 완화한 뒤 time[n - 1][n - 1]을 출력한다.
 *
 * 일반적인 방문 체크 BFS가 아닌, BFS 형태의 큐를 이용한 최단거리 완화 방식이다.
 */

import java.util.*;
import java.io.*;

public class Solution {

    static int answer;
    static int n;

    // 상, 하, 우, 좌 방향
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int[][] map;
    static int[][] time;

    static void bfs() {
        Deque<int[]> q = new ArrayDeque<>();

        // 시작점에서는 아직 이동하지 않았으므로 누적 복구 시간은 0이다.
        time[0][0] = 0;
        q.offerLast(new int[]{0, 0});

        // 도착점을 꺼내도 바로 종료하지 않고, 더 작은 비용이 전파되도록 계속 탐색한다.
        while (!q.isEmpty()) {
            int[] curr = q.pollFirst();
            int cr = curr[0];
            int cc = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = cr + dr[i];
                int nc = cc + dc[i];

                // 비용이 음수가 아니므로 시작점의 최소 비용 0은 더 줄어들지 않는다.
                if (nr == 0 && nc == 0) continue;

                // map[nr][nc]에 접근하기 전에 반드시 배열 범위를 확인한다.
                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

                // 현재 칸까지의 최신 최소 비용 + 다음 칸을 복구하는 비용
                int temp = time[cr][cc] + map[nr][nc];

                // 같거나 더 비싼 경로는 제외한다. 비용 0인 순환 경로의 반복도 막는다.
                if (time[nr][nc] <= temp) continue;

                // 더 저렴한 경로를 찾았으므로 이 칸에서 주변으로 다시 비용을 전파한다.
                q.offerLast(new int[]{nr, nc});
                time[nr][nc] = temp;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            answer = 0;
            n = Integer.parseInt(br.readLine());

            map = new int[n][n];
            time = new int[n][n];

            // 공백 없이 주어진 각 문자를 정수 복구 시간으로 변환한다.
            for (int row = 0; row < n; row++) {
                String line = br.readLine();
                for (int col = 0; col < n; col++) {
                    map[row][col] = line.charAt(col) - '0';
                }
            }

            // 아직 경로를 찾지 못한 칸은 충분히 큰 값으로 초기화한다.
            // 기본값 0을 그대로 사용하면 양수 비용의 경로를 갱신할 수 없다.
            for (int row = 0; row < n; row++) {
                Arrays.fill(time[row], Integer.MAX_VALUE);
            }

            bfs();
            answer = time[n - 1][n - 1];

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
