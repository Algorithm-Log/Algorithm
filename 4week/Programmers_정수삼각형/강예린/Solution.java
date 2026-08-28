import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        int n= triangle.length;
        
        for(int i=1; i< n; i++){
        	// 각 행의 첫 원소는 위에서밖에 접근 못함
            triangle[i][0] += triangle[i-1][0];
            // 각 행의 마지막 원소는 왼쪽 대각선에서밖에 접근 못함
            triangle[i][i] += triangle[i-1][i-1];
            
            // 그 사이는 위와 왼쪽 대각선 값과 중에서 큰 값의 합
            for(int j= 1; j< i; j++){
               triangle[i][j] += Math.max(triangle[i-1][j-1], 
                                          triangle[i-1][j]);
            }
        
        }
        
        int [] last= triangle[n-1];
        
        Arrays.sort(last);
        
        return last[n-1];
    }
}