class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        boolean[][] check = new boolean[n+1][m+1];
        for(int[] a : puddles){
            int x = a[0];
            int y = a[1];
            
            check[y][x] = true;
        }
        
        int[][] dp = new int[n + 1][m + 1];
        
        dp[1][1] = 1;
        
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(i == 1 && j == 1) continue;
                if(check[i][j]) continue;
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007;
            }
              
        }
        
        return dp[n][m];
    }
}