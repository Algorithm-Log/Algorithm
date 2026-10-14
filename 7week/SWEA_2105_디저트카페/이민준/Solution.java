import java.io.*;
import java.util.*;

public class Solution {
    static int n, map[][];
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};
    static int startR, startC;
    static int maxCnt;
    static int[] arr = new int[101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];

            for (int i = 0; i < n; i++) { // map 입력
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            maxCnt = -1; // 조건으로 인한 -1 설정
            int cnt = 0;
            for (int i = 0; i < n-2; i++) { // 첫 번째 줄부터 아래서 두번째 줄까지만
                for (int j = 1; j < n-1; j++) { // 양 옆 맨 끝줄 생략
                    startR = i; startC = j;
                    arr = new int[101];
                    dfs(i, j, 0, 0);
                }
            }

            sb.append('#').append(tc).append(' ').append(maxCnt).append('\n');
        }
        System.out.print(sb.toString());
    }

private static void dfs(int r, int c, int dir, int cnt) {
    arr[map[r][c]] = 1; // 방문

    // 직진(dir) 또는 꺾기(dir+1), 단 3을 넘지 않게
    for (int d = dir; d <= Math.min(dir + 1, 3); d++) {
        int nr = r + dr[d];
        int nc = c + dc[d];

        // 마지막 방향으로 한 칸 가서 시작점이면 한 바퀴 완성
        if (d == 3 && nr == startR && nc == startC) {
            maxCnt = Math.max(maxCnt, cnt + 1);
            continue;
        }
        if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue; // return 대신 continue
        if (arr[map[nr][nc]] == 1) continue;

        dfs(nr, nc, d, cnt + 1);
    }

    arr[map[r][c]] = 0; // 항상 되돌리기
}
}