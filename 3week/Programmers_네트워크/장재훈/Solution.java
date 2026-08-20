class Solution {
    int n;
    int[][] computers;
    int answer;
    boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        this.n = n;
        this.computers = computers;
        
        visited = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                // 현재 최초의 방문한 노드를 부모 노드로 카운트
                answer++;
                findNetwork(i);   
            }
        }
        
        return answer;
    }
    
    public void findNetwork(int node) {
        // 이미 해당 노드를 방문했다면 즉시 리턴
        if (visited[node]) return;
        // 연결된 노드 방문 처리
        visited[node] = true;
        
        for (int i = 0; i < n; i++) {
            // 아직 방문하지 않았으면서 연결된 노드가 있는지 확인
            if (!visited[i] && computers[node][i] == 1) findNetwork(i);
        }
    }
}