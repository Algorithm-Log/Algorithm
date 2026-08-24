class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int[][] dp = new int[triangle.length + 1][triangle.length + 1];
        dp[0][0] = triangle[0][0];
        
        for(int i = 1; i < triangle.length; i++){
            //왼쪽
            dp[i][0] = dp[i-1][0] + triangle[i][0];
            for(int j = 1; j < i; j++){
                //가운데
            	dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + triangle[i][j];
            }
          //오른쪽
            dp[i][i] = dp[i-1][i-1] + triangle[i][i];
        }
        
        int last = triangle.length -1;
        for(int i = 0; i < last; i++) {
        	answer = Math.max(answer, dp[last][i]);
        }
        return answer;
    }
}