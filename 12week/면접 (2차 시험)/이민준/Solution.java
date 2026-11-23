import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            int[] arr = new int[N + 1];

            int wrongCnt = N - M;
            int idx = 1;

            // 1. 정답과 오답 배치
            if (M > wrongCnt * (K - 1)) { // K-1: 2배가 발생하지 않도록 한 구간에 배치할 수 있는 최대 정답 개수

                // 남는 정답을 첫 번째 구간에 배치
                int firstRun = M - wrongCnt * (K - 1);
                idx += firstRun;

                // 나머지 구간은 K-1개씩 배치
                for (int i = 0; i < wrongCnt; i++) {
                    arr[idx++] = -1; // 오답
                    idx += K - 1;    // 정답 K-1개
                }

            } else {

                // 2배가 발생하지 않도록 정답을 분배
                int remain = M;

                for (int i = 0; i < wrongCnt; i++) {
                    int run = Math.min(K - 1, remain);

                    idx += run;
                    remain -= run;

                    arr[idx++] = -1;
                }
            }

            // 2. 점수 계산
            int counter = 0;
            int total = 0;

            for (int i = 1; i <= N; i++) {

                if (arr[i] == -1) {
                    counter = 0;
                } else {
                    total++;
                    counter++;

                    if (counter == K) {
                        total *= 2;
                        counter = 0;
                    }
                }
            }

            sb.append("#").append(tc).append(" ")
              .append(total).append("\n");
        }

        System.out.print(sb);
    }
}
