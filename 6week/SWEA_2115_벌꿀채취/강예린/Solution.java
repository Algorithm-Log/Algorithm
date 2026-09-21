import java.io.*;
import java.util.*;


class Solution {

    private static int calMax(int[] honeys, int c) {
        int max=0;
        for (int i = 0; i < (1<< honeys.length); i++) {
            int sum=0;
            int profit=0;
            for (int j = 0; j < honeys.length; j++) {
                if ((i & 1<< j) !=0 ) {
                    sum += honeys[j];
                    profit+= honeys[j]*honeys[j];
                }

            }
            if (sum <= c) max= Math.max(max, profit);
        }

        return max;
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
		StringBuilder sb= new StringBuilder();
        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            int answer = Integer.MIN_VALUE;

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            int[][] honeys = new int[N][N];

            // 시작점에서 M 크기만큼
            int[][] dp = new int[N][N-M+1];
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) honeys[i][j] = Integer.parseInt(st.nextToken());
            }
            
            // 합 미리 넣어놓기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= N - M; j++) {
                    int tmp= 0;

                    for (int k = 0; k < M; k++) {
                        tmp += honeys[i][j+k];
                    }

                    if (tmp <= C) {
                        for (int k = 0; k < M; k++) dp[i][j] += (honeys[i][j+k])*(honeys[i][j+k]);
                    }
                    else {
                        int[] arr= new int[M];
                        for (int k = 0; k < M; k++) arr[k]= honeys[i][j+k];

                        // c를 넘지 않는 최대 수집 수익
                        dp[i][j]= calMax(arr,C);
                    }

                }
            }

            for (int i = 0; i < N ; i++) {
                for (int j = 0; j <= N-M ; j++) {
                    int first= dp[i][j];

                    // 같은 행일 때
                    int second;
                    for (int l = j+M; l <= N-M; l++) {
                        second= dp[i][l];
                        answer= Math.max(answer, first+ second);
                    }

                    // 다른 행일 때
                    for (int k = i+1; k < N; k++) {
                        for (int l = 0; l <= N-M; l++) {
                            second= dp[k][l];
                            answer= Math.max(answer, first+ second);
                        }
                    }
                    
                }
            }
			
            
            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }
		bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}