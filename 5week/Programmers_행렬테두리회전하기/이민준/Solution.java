import java.util.*;

class Solution {
    
    int min, prev, temp;
    int[][] arr;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        arr = new int[rows+1][columns+1];
        
        int cnt = 1;
        for(int i = 1; i <= rows; i++){
            for(int j = 1; j <= columns; j++){
                arr[i][j] = cnt++;
            }   
        }
        
        int[] answer = new int[queries.length];
        int idx = 0;
        for(int[] q : queries){
            min = 10001;
            int x1 = q[0];
            int y1 = q[1];
            int x2 = q[2];
            int y2 = q[3];
            
            prev = arr[x1][y1];
            
            // 처음 가로 오른쪽 모서리 제외 값 옮기기
            for(int i = y1+1; i <= y2; i++){
                f(x1, i);
            }
            
            // 오른쪽 측면 진행
            for(int i = x1+1; i <= x2; i++){
                f(i, y2);
            }
            
            // 하단 가로
            for(int i = y2-1; i >= y1; i--){
                f(x2, i);
            }
            
            // 왼쪽 측면
            for(int i = x2-1; i >= x1; i--){
                f(i, y1);
            }
            
            answer[idx++] = min;
        }
        
        return answer;
    }
    
    public void f(int x, int y) {
        temp = arr[x][y];
        arr[x][y] = prev;
        prev = temp;
        min = Math.min(min, arr[x][y]);
    }
}