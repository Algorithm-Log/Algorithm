import java.util.*;
import java.io.*;



class Solution {
    static StringBuilder sb = new StringBuilder();
    static int[] parent;
    static int[][] edges;
    static long res,count;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T= Integer.parseInt(br.readLine());
        
        StringTokenizer st ;
        for(int test_case=1;test_case<T+1;test_case++){

            st = new StringTokenizer(br.readLine());

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            parent = new int[v+1];
            edges = new int[e][3];

            // 각 노드의 루트 노드가 뭔지 저장하는 배열의 초기값을 자기자신의 노드 번호로 초기화
            make_set();

            for(int i=0;i<e;i++){
                st= new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edges[i][0]=start;
                edges[i][1]=end;
                edges[i][2]=weight;

            }

            // 최소신장트리를 구하기 위해서 가중치를 기준으로 오름차순으로 정렬 후 앞에서 부터 꺼낼거임
            Arrays.sort(edges,(a,b)->{return Integer.compare(a[2],b[2]);});
            res=0;
            count=0;
            int index=0;

            // 간선들의 크기보다 작고 선택한 간선이 v-1(노드의 갯수-1)보다 작을때만 반복
            while(index<e && count<v-1){

                //두정점을 연결시 싸이클이 발생하는지 확인후 조건 만족시 연결
                union(index,edges[index][0],edges[index][1]);

                //다음 간선으로 넘어가기위한 인덱스++
                index++;
            }
            sb.append("#"+test_case+" "+res+"\n");
        }
        System.out.println(sb);
    }

    

    static void make_set(){
        // 각 노드들의 루트노드를 자기자신으로 초기화
        for(int i=1;i<parent.length;i++){
            parent[i]=i;
        }
    }

    static int find_set(int node){
        // 입력으로 들어온 노드의 루트 노드를 찾는 함수
        if(parent[node]==node) return node;

        // 재귀함수로 루트노드를 찾은후 입력받은 노드의 루트노드를 find_set 으로 찾은 루트 노드로 변경
        // 경로압축
        return parent[node] = find_set(parent[node]);
    }

    static void union(int i,int left,int right){
        // 노드들을 연결시켜서 신장트리로 만들어주는 함수
        

        int leftRoot = find_set(left);
        int rightRoot = find_set(right);

        if(leftRoot != rightRoot){
            // 두 노드가 같은 트리가 아닐때

            
            parent[rightRoot]=leftRoot; 
            // 왼쪽의 노드에 오른쪽 노드를 합쳐줌
            // 합치는 과정에서 깊이비교를해서 큰쪽노드에 작은쪽 노드를 붙이는 최적화는 하지않았음

            res+=edges[i][2];
            // 연결에 사용된 간서의 가중치를 결과값에 갱신
            count++;
            // 사용한 간선 갯수더하기1
        }
        
    }
    
}
