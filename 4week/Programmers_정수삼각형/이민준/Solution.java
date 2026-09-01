class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        // 직각 삼각형으로 생각하고 구성
        int[] dr = {1, 1};
        int[] dc = {1, 0};
        
        // 아래부터 위로 올라가며 최대값을 현재 위치의 값과 더함.
        for(int i = triangle.length - 2; i >= 0; i--){
            for(int j = 0; j < triangle[i].length; j++){
                triangle[i][j] += Math.max(triangle[i+dr[0]][j+dc[0]],
                                           triangle[i+dr[1]][j+dc[1]]);
            }
        }
        
        // 마지막 위치값인 [0][0]에서 최대값이 구해짐
        return triangle[0][0];
    }
}