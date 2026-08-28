class Solution {
    public int solution(int m, int n, int[][] puddles) {
        
        int[][] map= new int [m+1][n+1];
        
        for(int[] xy: puddles) map[xy[0]][xy[1]]= -1;
                
        map[1][1]= 1;

        
        for (int i= 1; i<= m; i++){
          for (int j= 1; j<= n; j++){
              
            // 일단 현재 길이 물웅덩이가 아니여야함
            if(map[i][j] == -1) continue;
              
            // 왼쪽
            if(map[i][j-1] != -1) map[i][j] += (map[i][j-1])% 1000000007;
            
            // 위
            if(map[i-1][j] != -1) map[i][j] += (map[i-1][j])% 1000000007;
              
            }  
            
        }
        
        return map[m][n]% 1000000007;
    }
}