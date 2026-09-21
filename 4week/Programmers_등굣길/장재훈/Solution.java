class Solution {
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        int[][] land = new int[m][n];
        
        // 1. 전체 2차원 배열을 1로 초기화
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                land[i][j] = 1;
            }
        }
        
        // 2. 웅덩이가 있는지 확인
        if (puddles[0].length != 0) {
            // 2-1. 웅덩이가 있다면 웅덩이를 0으로 바꿈
            for (int i = 0; i < puddles.length; i++) {
                int x = puddles[i][0] - 1;
                int y = puddles[i][1] - 1;
                land[x][y] = 0;
            }   
        }
        
        // 3. 각 위치마다 갈 수 있는 경우의 수를 누적
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                // 3-1. 웅덩이는 0으로 놔둠
                if (land[r][c] == 0 || r == 0 && c == 0) continue;
                // 3-2. 누적 방식은 누적합을 구할 위치 기준 왼쪽과 위쪽
                // 0번 행이나 열이라면 예외처리
                if (r == 0) {
                    land[r][c] = land[0][c-1];
                }
                else if (c == 0) {
                    land[r][c] = land[r-1][0];
                }
                else {
                    // 매번 값을 누적할 때마다 최단 경로의 개수를 나눠줘야 값이 커졌을 때, 오버플로우가 안난다.
                    land[r][c] = (land[r-1][c] + land[r][c-1]) % 1000000007;
                }
            }
        }
        
        answer = land[m-1][n-1];
        
        return answer;
    }
}

// HashMap으로 푼 코드
// 위의 코드와 동일하게 O(m * n)의 시간 복잡도가 소요된다.
// 근데 접근하는 속도가 배열이 압도적으로 빠르다.
// HashMap은 Integer로 Boxing하고 Hash를 계산하는 비용이 들고 메모리가 흩어져있어 소요되는 시간이 더 많다.
// 배열은 인덱스로 바로 접근하기에 O(1)로 바로 접근 가능해 속도가 빠르다.

// import java.util.*;

// class Solution {
    
//     public int solution(int m, int n, int[][] puddles) {
//         int answer = 0;
//         // 총 땅의 개수
//         int land = m*n;
        
//         HashMap<Integer, Integer> map = new HashMap<>();
        
//         for (int i = 1; i <= land; i++) {
//             map.put(i, 1);
//         }
        
//         // 2. 웅덩이가 있는지 확인
//         if (puddles[0].length != 0) {
//             // 2-1. 웅덩이가 있다면 웅덩이를 0으로 바꿈
//             for (int i = 0; i < puddles.length; i++) {
//                 int key = m*(puddles[i][1]-1) + puddles[i][0];
//                 map.put(key, 0);
//             }   
//         }
        
//         for (int i = 2; i <= land; i++) {
//             // 웅덩이라면 skip
//             if (map.get(i) == 0) continue;
//             // 첫번째 행인 경우
//             int value;
//             if (i / m == 0 || i == m) {
//                 // 현재 땅의 왼쪽 칸의 값을 가져옴
//                 value = map.get(i-1);
//                 map.put(i, value);
//             }
//             // 첫번째 열인 경우
//             else if (i % m == 1) {
//                 // 현재 땅의 위의 칸의 값을 가져옴
//                 value = map.get(i-m);
//                 map.put(i, value);
//             }
//             else {
//                 value = map.get(i-1) + map.get(i-m);
//             }
//             map.put(i, value);
//             System.out.println(i + ": " + value);
//         }
        
//         answer = map.get(land);
            
//         return answer;
//     }
// }