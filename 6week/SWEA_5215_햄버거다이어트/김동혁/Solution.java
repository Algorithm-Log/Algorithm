import java.util.*;
import java.io.*;

public class Solution {
    static int answer;
    static int n, l;
    static int[][] arr;

    static void dfs(int depth, int calorie, int grade) {
        if (calorie > l) return;

        if (depth == n) {
            if (calorie <= l) {
                answer = Math.max(answer, grade);
                return;
            }
        }

        dfs(depth + 1, calorie + arr[depth][1], grade + arr[depth][0]);
        dfs(depth + 1, calorie, grade);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tcase = 1; tcase <= t; tcase++) {
            answer = Integer.MIN_VALUE;

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());

            // 0점수, 1칼로리
            arr = new int[n][2];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0, 0);
            sb.append("#").append(tcase).append(" ").append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
