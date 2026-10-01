import java.util.*;
import java.io.*;

//11:40

// 입력이 1 2
//       1 3 
// 이런식으로 주어지는데 이거 인접행렬이나 인접리스트 로 구현해서
// 무리의 갯수를 구하는거닌까 bfs 나 dfs 로 탐색해서 탐색한 요소들은 방문처리하고
// 모든 요소를 다방문할때까지 순회 해서 무리의 갯수를 구하면될듯
// 인접리스트로 구현을 하자 List<Integer>[] village 로 마을을 구현하고
// 방문 체크는 음 boolean[][] 이걸로 되려나
// visited[i][vilage[i].size()] i=0 ~ vilage.length()
// 이렇게 하면될듯 
// 그럼 for(int i=0 ~length) for(int j=0 ~ vilage[i].size()) que.add(vilage[i].get(j))
// 음 큐에 다 넣고 값을 꺼낼때 방문 처리해주고 꺼낸값으로 또 접근해서 방문안한곳이면 또 큐에 넣고
// 이걸 for(int i=0 ~length) for(int j=0 ~ vilage[i].size()) 이 반복문 한번한번에 다 반복해서
// while() 끝나면 count++ 해주고
// 모든 요소를 다 순회 했으면 결과 count를 출력하면 될듯?
class Solution {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T=Integer.parseInt(br.readLine());
        
        
        for(int test_case=1;test_case<T+1;test_case++) {
            StringTokenizer st= new StringTokenizer(br.readLine());
            int N=Integer.parseInt(st.nextToken());
            int M=Integer.parseInt(st.nextToken());

            List<Integer>[] vilage = new ArrayList[N+1];

            for(int i=1;i<=N;i++){
                vilage[i]=new ArrayList<>();
            }
            for(int i=0;i<M;i++){
                
                st = new StringTokenizer(br.readLine());
                int left = Integer.parseInt(st.nextToken());
                int right = Integer.parseInt(st.nextToken());
                
                //무방향 그래프 구현
                vilage[left].add(right);
                vilage[right].add(left);

            }

            // for(int i=1;i<vilage.length;i++){
            //     System.out.print(i+": ");
            //     for(int j=0;j<vilage[i].size();j++){
            //         System.out.printf("%d ",vilage[i].get(j));
            //     }
            //     System.out.println();
            // }

            boolean[] visited = new boolean[N+1];

            

            Deque<Integer> que = new ArrayDeque<>();
            int count=0;
            for(int i=1;i<=N;i++){
                //System.out.println(i);
                
                //방문체크
                if(!visited[i]){
                    visited[i]=true;
                    que.offer(i);

                    //bfs
                    while(!que.isEmpty()){

                        int val = que.poll();

                        for(int k=0;k<vilage[val].size();k++){
                            // 리스트 안의 값들중 방문 하지 않은 놈들 전부 큐에 넣기
                            if(!visited[vilage[val].get(k)]){
                                visited[vilage[val].get(k)]=true;
                                que.offer(vilage[val].get(k));
                            }
                        }

                    }

                    count++;
                }
            }

            sb.append("#"+test_case+" "+count+"\n");

        }

        System.out.print(sb);

    }

}
