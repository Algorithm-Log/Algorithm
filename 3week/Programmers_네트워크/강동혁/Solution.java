// 21:18

// 그래프를 인접행렬 방식으로 표현되어있는데
// 이 방식의 그래프를 가지고 dfs 나 bfs 두 방법중
// (다른 방식으로 탐색해도됨) 탐색하여서 몇번의 dfs 나 bfs 를 최소 
// 몇번 호출 하면 모든 요소를 다 돌수있는지 알아내는 문제
// dfs 를 사용해서 탐색을 하면서 
// dfs 호출을 visited 배열을 만들어서 배열의 모든요소가
// 1이면 그만 호출하도록 dfs의 입력으로는 visited 배열의 
// 인덱스 값을 넘겨줌
// dfs 내부에서는 방문한 노드는 visited 로 체크
// 근데 분기점으로 되돌아 가기위한 visited 배열이 하나더 필요할까?
// 아니 저 visited 하나로 분기점으로 되돌려도 dfs가 종료 되고 난뒤에는
// 결국 방문 했던 모든 노드들이 기록이 될거임
// 아닌가? 
//
// 리스트의 모든요소가 1인지 확인하는 방법
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
  int [] visited;
  List<Integer> visited2;
  int n;
  int [][] computers;
    public int solution(int n, int[][] computers) {
      visited=new int[n];
      visited2=new ArrayList<>();
      for(int i=0;i<n;i++){
        visited2.add(0);
      }
      this.computers=computers;
      int answer = 0;
      while(visited2.contains(0)){
        for(int i=0;i<visited2.size();i++){
          if(visited2.get(i)==0){
            answer++;
            visited2.set(i,1);
            dfs(computers[i]);
          }
        }
        

      }
        
  
      
        return answer;
    }
    void dfs(int[] currentNode){
      
      for(int i=0;i<n;i++){
        
        if(visited[i]==0){
          visited[i]=1;
          visited2.set(i,1);
          dfs(computers[i]);
        }
      }
      
      
    }
}