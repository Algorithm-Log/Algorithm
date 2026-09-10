import java.util.*;
import java.io.*;

// 부분집합중에서 부분집합의 요소의 합이 L 보다 작은거중에서 최대값
// 부분집합을 구하는 방법으로는 dfs 를 사용하여서
// 매개변수로는 현재 재료의 갯수,현재칼로리,이전에 선택한 재료의 인덱스
// 3가지를 가지고 가서 반복문으로 하지말고
// 현재재료의 갯수,현재 칼로리 두가지를 들고가서
// 지금재료를 선택할때, 안할때 두가지 경우를 dfs 호출해주어서
// 부분집합을 만들자 dfs 종료부분은 현재칼로리가 L보다 클때
// 근데 dfs 제일 윗부분에서 이걸해버리면 안될거같은데
// dfs 호출할때 현재 재료를 추가햇을때 칼로리가 L보다 작은지 확인을 하고
// dfs 를 호출해주고 dfs 제일 상단에 최대 칼로리를 매번 갱신하는 것으로
// 칼로리가 아니라 점수를 갱신해줘야함
// 그럼 칼로리와 점수를 둘다 매개변수 로 들고 가야할듯
// int[[100,200],[300,500]]
// 종료 조건을 모든 재료의 갯수를 다봣을때
class Solution {
    
    static int[][] arr;
    static int N;
    static int L;
    static int result;
    public static void main(String[] args)throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T=Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<T+1;test_case++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            N=Integer.parseInt(st.nextToken());
            L=Integer.parseInt(st.nextToken());

            
            arr=new int[N][2];
            for(int i=0;i<N;i++){
                st= new StringTokenizer(br.readLine()," ");

                int score = Integer.parseInt(st.nextToken());
                int calorie = Integer.parseInt(st.nextToken());

                arr[i][0]=score;
                arr[i][1]=calorie;
            }

            result=0;
            dfs(0,0,0);

            System.out.printf("#%d %d\n",test_case,result);
        }
        
    
    }

    static void dfs(int count,int score,int calorie){

        if(count==N){ // 모든 재료를 다 탐색하였을때
            result=Math.max(result,score); //점수 최대값 갱신
            return;
        }

        if(calorie+arr[count][1]<L){
            //현재 재료를 선택할때
            dfs(count+1,score+arr[count][0],calorie+arr[count][1]);
        }

        //현재 재료를 선택안할때
        dfs(count+1,score,calorie);
    }


}
