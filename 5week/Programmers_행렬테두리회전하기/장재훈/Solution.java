class Solution {
    // 2. 테두리의 칸들만 움직일 수 있도록 dr, dc 정의
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        
        // 1. 2차원 배열에 값들을 집어넣는다.
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = i*columns + j+1;
            }
        }
        
        // 3. 쿼리 전체 순회
        for (int i = 0; i < queries.length; i++) {
            // 인덱스 위치 조정
            int r1 = queries[i][0] - 1;
            int c1 = queries[i][1] - 1;
            int r2 = queries[i][2] - 1;
            int c2 = queries[i][3] - 1;
            
            // 방향 조절을 위한 변수
            int direct = 0;
            // 최솟값 저장
            int min = matrix[r1][c1];
            // 덮어쓰기 방지를 위해 현재값을 미리 들고 있는다.
            int temp = min;           
            
            // 현재 인덱스 값 저장
            int r = r1;
            int c = c1;
            
            // 다시 r1, c1 위치로 돌아올때까지 반복
            while(true) {
                
                int nr = r + dr[direct];
                int nc = c + dc[direct];
                
                // 방향 꺾어야하는지 확인
                if (nc > c2 || nr > r2 || nc < c1 || nr < r1) {
                    direct++;
                    nr = r + dr[direct];
                    nc = c + dc[direct];
                }
                
                // temp 스왑
                int nextTemp = matrix[nr][nc];
                matrix[nr][nc] = temp;
                temp = nextTemp;
                
                min = Math.min(min, temp);
                
                // 행과 열 업데이트
                r = nr;
                c = nc;
                
                if (r == r1 && c == c1) {
                    answer[i] = min;
                    break;
                }
            }
        }
        
        return answer;
    }
    
}
// 2차원 배열에 값을 집어넣지 않고도 만들 수 있을 것 같다.
// 1. 2차원 배열에 값들을 집어넣는다.
// 2. 테두리의 칸들만 움직일 수 있도록 dr, dc 정의
// 3. 쿼리 전체 순회
// 4. 각 쿼리별 테두리를 돈다. (이때, 다음 값은 사라지면 안되므로 tmp로 관리)
// 5. 이동시키며 min(최솟값)을 비교한다.