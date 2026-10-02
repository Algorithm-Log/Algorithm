import java.util.*;
import java.io.*;

public class Solution {
    static int[] dr = {1, 1, -1, -1};
    static int[] dc = {1, -1, -1, 1};
    static int[][] map;
    static boolean[] visited;
    static int answer;
    static int n;

    static void dfs(int dir, int sr, int sc, int cr, int cc, int cnt) {
        for (int nextDir = dir; nextDir <= dir + 1 && nextDir < 4; nextDir++) {
            int nr = cr + dr[nextDir];
            int nc = cc + dc[nextDir];

            if (nr == sr && nc == sc && nextDir == 3) {
                answer = Math.max(answer, cnt);
                continue;
            }

            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;

            int nextDessert = map[nr][nc];
            if (visited[nextDessert]) continue;

            visited[nextDessert] = true;
            dfs(nextDir, sr, sc, nr, nc, cnt + 1);
            visited[nextDessert] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            answer = -1;
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];
            visited = new boolean[101];

            for (int row = 0; row < n; row++) {
                st = new StringTokenizer(br.readLine());
                for (int col = 0; col < n; col++) {
                    map[row][col] = Integer.parseInt(st.nextToken());
                }
            }

            for (int row = 0; row < n - 2; row++) {
                for (int col = 1; col < n - 1; col++) {
                    int dessert = map[row][col];
                    visited[dessert] = true;
                    dfs(0, row, col, row, col, 1);
                    visited[dessert] = false;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
