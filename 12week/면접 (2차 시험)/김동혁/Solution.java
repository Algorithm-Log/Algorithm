import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            boolean[] result = new boolean[n+1];

            int lose = n-m;
            int win = m;

            for (int i = n; i > 0; i--) {
                if (lose>0 && i%k==0) {
                    result[i] = false;
                    lose--;
                } else if (lose==0) {
                    result[i] = true;
                    win--;
                } else if (win==0) {
                    result[i] = false;
                    lose--;
                } else if (win>0 && i%k!=0) {
                    result[i] = true;
                    win--;
                }
            }

            int answer = 0;

            for (int i = 1; i < result.length; i++) {
                if (result[i] && i%k==0) {
                    answer = 2*(answer+1);
                } else if (result[i] && i%k!=0) {
                    answer = answer+1;
                }
            }

            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.println(sb);
    }
}
