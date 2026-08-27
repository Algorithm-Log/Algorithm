class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int mod = 1_000_000_007; // 문제 조건때문에 정의
        
        int[][] arr = new int[n][m];
        for(int[] nums : puddles){
            int j = nums[0] - 1;
            int i = nums[1] - 1;
            arr[i][j] = -1;
        }
        
        arr[0][0] = 1;
        // 해당 위치에서 왼쪽과 위의 가중치를 보기 위해 이렇게 구성
        int[] dr = {-1, 0};
        int[] dc = {0, -1};
        
        // DP로, 해당 위치에 올 수 있는 경로 수를 더해줌.
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(arr[i][j] == -1) continue; // 막혀있으면 패스
                if(i == 0 && j == 0) continue; // 시작점 패스
                
                int sum = 0;
                for(int d = 0; d < 2; d++){
                    int prevI = i + dr[d];
                    int prevJ = j + dc[d];
                    
                    if(prevI < 0 || prevI >= n || prevJ < 0 || prevJ >= m) continue;
                    if(arr[prevI][prevJ] == -1) continue;
                    
                    sum += arr[prevI][prevJ];
                    sum %= mod; // 문제의 조건
                }
                
                arr[i][j] = sum;
            }
        }
        
        return arr[n-1][m-1];
    }
}