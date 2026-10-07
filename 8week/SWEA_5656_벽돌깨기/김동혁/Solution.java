import java.util.*;
import java.io.*;

public class Solution {

    /*
     * [풀이 전략]
     * 1. 구슬을 어느 열에 쏠지 DFS로 모든 경우를 탐색한다.
     * 2. 구슬이 맞은 벽돌부터 BFS로 연쇄 폭발시킨다.
     * 3. 폭발이 끝나면 남은 벽돌을 아래로 떨어뜨린다.
     * 4. 다음 경우를 탐색하기 위해 맵을 이전 상태로 원상복구한다.
     *
     * dfs(depth)
     * = 지금까지 depth개의 구슬을 쏜 상태에서
     *   다음 구슬을 어느 열에 쏠지 결정한다.
     */

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static int n, w, h;
    static int[][] map;
    static int answer;


    // =========================================================
    // 1. 구슬을 N번 쏘는 모든 경우 탐색
    // =========================================================

    static void dfs(int depth) {

        // 구슬을 N번 모두 쐈다면
        // 현재 남아있는 벽돌 개수로 정답 갱신
        if (depth == n) {

            int brickCnt = countBricks();
            answer = Math.min(answer, brickCnt);

            return;
        }

        /*
         * 현재 depth에 들어왔을 때의 맵 상태 저장
         *
         * 하나의 열을 선택해서 탐색을 끝낸 뒤
         * 다른 열도 같은 상태에서 시작해야 하기 때문에
         * 현재 map을 따로 저장한다.
         */
        int[][] origin = new int[h][w];
        copyArr(origin);

        // 이번 구슬을 어느 열에 떨어뜨릴지 선택
        // 같은 열을 여러 번 선택할 수도 있다.
        for (int col = 0; col < w; col++) {

            // 현재 열에 구슬 발사
            breakBricks(col);

            // 다음 구슬 발사
            dfs(depth + 1);

            // 다른 열을 선택하기 전에 현재 상태로 복구
            restoreMap(origin);
        }
    }


    // =========================================================
    // 2. 선택한 열에 구슬 발사
    // =========================================================

    static void breakBricks(int col) {

        /*
         * 구슬은 위에서 아래로 떨어지므로
         * 해당 열에서 가장 먼저 만나는 벽돌을 찾는다.
         */
        for (int d = 0; d < h; d++) {

            if (map[d][col] != 0) {

                // 맞은 벽돌부터 연쇄 폭발
                boom(d, col);

                // 폭발이 끝난 뒤 벽돌을 아래로 떨어뜨림
                drop();

                // 가장 위 벽돌 하나만 맞으면 되므로 종료
                break;
            }
        }
    }


    // =========================================================
    // 3. 벽돌 연쇄 폭발
    // =========================================================

    static void boom(int d, int col) {

        Deque<int[]> q = new ArrayDeque<>();

        // 처음 구슬에 맞은 벽돌부터 시작
        q.offerLast(new int[]{d, col});

        while (!q.isEmpty()) {

            int[] curr = q.pollFirst();

            int cr = curr[0];
            int cc = curr[1];

            // 현재 벽돌의 숫자만큼 폭발 범위가 결정된다.
            int power = map[cr][cc];

            // 현재 벽돌 제거
            map[cr][cc] = 0;

            // 상하좌우 4방향 확인
            for (int i = 0; i < 4; i++) {

                // power - 1칸까지 영향
                for (int dist = 1; dist < power; dist++) {

                    int nr = cr + dr[i] * dist;
                    int nc = cc + dc[i] * dist;

                    // 범위를 벗어나면 해당 방향 탐색 종료
                    if (nr < 0 || nr >= h || nc < 0 || nc >= w) break;

                    // 빈 공간은 그냥 통과
                    if (map[nr][nc] == 0) continue;

                    /*
                     * 폭발 범위 안에서 다른 벽돌을 만나면
                     * 그 벽돌 역시 자신의 숫자만큼 폭발해야 하므로
                     * Queue에 추가한다.
                     */
                    q.offerLast(new int[]{nr, nc});
                }
            }
        }
    }


    // =========================================================
    // 4. 폭발 후 벽돌을 아래로 떨어뜨리기
    // =========================================================

    static void drop() {

        int[][] tempMap = new int[h][w];

        List<Integer> list;
        list = new ArrayList<>();

        // 각 열을 하나씩 정리
        for (int i = 0; i < w; i++) {

            /*
             * 아래에서 위로 확인하면서
             * 현재 열에 남아있는 벽돌만 저장한다.
             */
            for (int j = h - 1; j >= 0; j--) {

                if (map[j][i] == 0) continue;

                list.add(map[j][i]);
            }

            // 저장한 벽돌들을 가장 아래부터 다시 채운다.
            int idx = 0;

            for (int j = h - 1; j > (h - 1 - list.size()); j--) {

                tempMap[j][i] = list.get(idx);
                idx++;
            }

            // 다음 열에서 다시 사용
            list.clear();
        }

        // 정리된 맵을 기존 map에 반영
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i][j] = tempMap[i][j];
            }
        }
    }


    // =========================================================
    // 5. 현재 남아있는 벽돌 개수 세기
    // =========================================================

    static int countBricks() {

        int cnt = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {

                // 0이 아니면 모두 벽돌
                if (map[i][j] != 0) cnt++;
            }
        }

        return cnt;
    }


    // =========================================================
    // 6. 현재 map을 origin에 복사
    // =========================================================

    static void copyArr(int[][] origin) {

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                origin[i][j] = map[i][j];
            }
        }
    }


    // =========================================================
    // 7. origin에 저장했던 상태로 map 원상복구
    // =========================================================

    static void restoreMap(int[][] origin) {

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i][j] = origin[i][j];
            }
        }
    }


    // =========================================================
    // 8. 입력 / 출력
    // =========================================================

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            // 남은 벽돌의 최솟값을 구하므로 큰 값으로 초기화
            answer = Integer.MAX_VALUE;

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            map = new int[h][w];

            // 초기 벽돌 상태 입력
            for (int row = 0; row < h; row++) {

                st = new StringTokenizer(br.readLine());

                for (int col = 0; col < w; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            // 첫 번째 구슬부터 탐색
            dfs(0);

            sb.append("#")
              .append(tc)
              .append(" ")
              .append(answer)
              .append("\n");
        }

        System.out.println(sb);
    }
}
