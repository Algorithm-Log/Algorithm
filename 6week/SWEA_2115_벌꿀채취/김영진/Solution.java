    import java.util.*;
    import java.io.*;

    class Solution
    {
        public static int n,m,c,max,tempMax;
        public static int[][] arr;
        
        public static void main(String args[]) throws Exception
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int T = Integer.parseInt(br.readLine());

            for(int test_case = 1; test_case <= T; test_case++)
            {
                StringTokenizer st = new StringTokenizer(br.readLine());
                n = Integer.parseInt(st.nextToken());
                m = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                arr = new int[n][n];
                for(int i = 0; i < n; i++){
                    st = new StringTokenizer(br.readLine());
                    for (int j = 0; j < n; j++) {
                        arr[i][j] = Integer.parseInt(st.nextToken());
                    }
                }

                max = 0;

                for (int r1 = 0; r1 < n; r1++) {
                    for (int c1 = 0; c1 <= n-m; c1++) {
                        for (int r2 = 0; r2 < n; r2++) {
                            for (int c2 = 0; c2 <= n-m; c2++) {
                                if(r1==r2){
                                    if(c2 < c1 + m && c1 < c2 + m){
                                        continue;
                                    }
                                }
                                tempMax = 0;
                                dfs(r1,c1,0,0,0);
                                int profit1 = tempMax;

                                tempMax = 0;
                                dfs(r2,c2,0,0,0);
                                int profit2 = tempMax;

                                max = Math.max(max , profit1 + profit2);
                            }
                        }
                    }
                }
                System.out.println("#" + test_case + " " + max);
            }
        }
        public static void dfs(int i, int j, int index, int sum, int profit){
            if(sum > c){
                return ;
            }
            if(index == m){
                tempMax = Math.max(tempMax, profit);
                return ;
            }
            int honey = arr[i][j+index];

            dfs(i, j, index+1, sum + honey, profit + honey * honey);
            dfs(i, j, index+1, sum, profit);
        }
    }