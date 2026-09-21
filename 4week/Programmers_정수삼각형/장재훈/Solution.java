class Solution {
    int[][] triangle;
    
    public int solution(int[][] triangle) {
        int answer = 0;
        this.triangle = triangle;
        int len = triangle.length;
        
        // 1. 위에서 아래로 내려간다 1번 인덱스부터 n번 인덱스까지
        for (int i = 1; i < len; i++) {
            // 2. 0번 인덱스부터 i 인덱스까지 돌면서 값 업데이트
            for (int j = 0; j <= i; j++) {
                // 3. 누적합 계산
                triangle[i][j] = memoization(i, j);
            }
        }
        
        // 4. 모든 값을 업데이트한 뒤, 제일 바닥에서 값이 제일 큰걸 answer에 둔다
        for (int i = 0; i < len; i++) {
            answer = Math.max(answer, triangle[len-1][i]);
        }
        
        return answer;
    }
    
    // h : 높이, w : 너비
    public int memoization(int h, int w) {
        int res = triangle[h][w];
        // 3-1. 가장 좌측 한번의 연산만 함
        if (w == 0) {
            res += triangle[h-1][0];
        }
        // 3-2. 우측이라면 한번의 연산만 함
        else if (h == w) {
            res += triangle[h-1][w-1];
        }
        // 3-3. 위의 두 경우가 아니라면 두 부모의 값 중 큰 것과 합한다.
        else {
            int num = Math.max(triangle[h-1][w-1], triangle[h-1][w]);
            res += num;
        }
        return res;
    }
}