import java.io.*;
import java.util.*;



// 입력이 from to 로 들어오닌까 이걸 인접 리스트로 만들어서 
// bfs 탐색을 보내면서 레벨단위 탐색에서 가장 큰 숫자로 결과값을 변경해주기
public class Solution {
    static List<Integer>[] graph ;
    static boolean[] visited;
    static int res;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T=10;

        for(int test_case=1;test_case<T+1;test_case++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int n=Integer.parseInt(st.nextToken());
            int start=Integer.parseInt(st.nextToken());
            graph = new ArrayList[101];
            st = new StringTokenizer(br.readLine());

            //인접리스트 사용을 위한 배열에 ArrayList 객체 할당
            for(int i=0;i<=100;i++){
                graph[i]=new ArrayList<>();
            }


            //방향그래프 를 인접리스트로 구현
            for(int i=0;i<n/2;i++){
                
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                
                graph[from].add(to);
                
            }
            visited=new boolean[101];
            res=0;
            bfs(start);

            sb.append("#"+test_case+" "+res+"\n");

        }
        System.out.println(sb);
    } 

    static void bfs(int start){
        Deque<List<Integer>> que = new ArrayDeque<>();
        // 시작점 처리
        que.offer(graph[start]);
        visited[start]=true;
        
        

        while(!que.isEmpty()){
            int size=que.size();
            int max=0;
            
            for(int j=0;j<size;j++){//레벨단위 탐색

                List<Integer> temp = que.poll();
                
                for(int i=0;i<temp.size();i++){
                    if(visited[temp.get(i)]) continue;

                    visited[temp.get(i)]=true; // 방문처리
                    que.offer(graph[temp.get(i)]); //큐에 삽입
                    max=Math.max(temp.get(i),max); //최댓값 갱신
                    
                    
                }

            }
            
            if(max != 0){//큐에 넣을때 최대값을 갱신해줘서 마지막 꺼내는 반복문에서 max가0인 값으로 변경되지못하게
                res=max;
            }
            
        }
    }

} 