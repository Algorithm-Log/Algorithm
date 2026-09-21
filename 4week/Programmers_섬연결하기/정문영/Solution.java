import java.util.*;

class Solution {
    
    int[] parent;
    
    public int solution(int n, int[][] costs) {
        parent = new int[n];
        
        // 각 섬을 독립된 집합으로 초기화
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        // 비용 기준 오름차순 정렬
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        
        int answer = 0;
        int count = 0;
        
        for (int[] cost : costs) {
            int island1 = cost[0];
            int island2 = cost[1];
            
            // 두 섬의 대표가 다르면 아직 연결되지 않은 상태
            if (find(island1) != find(island2)) {
                union(island1, island2);
                answer += cost[2];
                count++;
                
                // 모든 섬 연결 완료
                if (count == n - 1) {
                    break;
                }
            }
        }
        
        return answer;
    }
    
    // 해당 섬이 속한 집합의 대표 찾기
    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        
        return parent[x] = find(parent[x]);
    }
    
    // 두 집합 합치기
    private void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}