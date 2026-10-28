import java.util.*;
import java.io.*;

// 최소신장트리 문제 parent 배열에 자신의 루트노드를 기록하고
// 입력이 이상하게 주어지기 때문에
// 이걸 잘저장해야함
// 입력으로 간선 정보가 주어지지 않음
// 섬의 갯수가 1000개가 넘는데 간선정보를 입력받은 섬갯수만큼 만든다?
// 음 섬이 1000개 이면 500500 개의 간선정보 배열을 만들어서
// 관리해야하는데 가능할듯

class Solution {
    static StringBuilder sb = new StringBuilder();
    static int[] parent;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T= Integer.parseInt(br.readLine());
        
        
        for(int test_case=1;test_case<T+1;test_case++){

            int n = Integer.parseInt(br.readLine());
            long edgesLen=0;
            
            for(int i=0;i<n;i++){
                edgesLen+=n-i;
            }

            edges = new int[edgesLen+1];

            



        }
        
    }

    static void make_set(){
        for(int i=1;i<parentLen;i++){
            parent[i]=i;
        }
    }

    static int find_set(int node){
        if(parent[node]==node) return parent[node];

        return parent[node] = find_set(node);
    }

    static void union_find(int left,int right){
        int leftRoot = find(left);
        int rightRoot = find(right);

        if(leftRoot!=rightRoot){
            parent[rightRoot]=leftRoot;
            // 연결

            // 가중치 계산
        } 
    }
    
    
}
