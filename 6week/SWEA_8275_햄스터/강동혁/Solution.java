import java.util.*;
import java.io.*;

//20:29 

// 햄스터 우리가 N개 각 우리에 0마리 이상 x 마리 이하
// M개의 기록 (i번 우리에서 r 번 우리까지 햄스터 수가 s 마리였다)
// 출력을 사전순으로 오름차순으로 할것
// 무슨 유형 문제일까? 주어진 조건은 몇번우리에서 몇번 우리까지 몇마리
// dfs 유형인거 같고 예시로 1번부터 2번까지 5마리 이면
// 뒤에서 부터 값을 채우고 x-채운값 만큼 남은값으로 나머지를 채워야할거같은데
// 뒤에서 부터 최대로 채우면 자동적으로 오름차순인 경우의수로 만들어지고
// 뒤에서 부터 채우는 규칙을 찾아보자 
// 근데 이거 dfs 가 아닌데 그럼
// int[] 에 start,end,count 3개 를 저장해서
// int[] 의 마지막요소 부터 꺼내서
// end 에 if(x<count) 면 end=x start=count-x 근데 count-x<x 를 만족해야함 
// 근데 결과를 저장할 int [] 을 하나만들어서 [end]=x [start]=count-x 이렇게 저장을 해야겠다
// 그래서 []의 값이 -1이 아니면 초기화가 된적이 있으면 넘어가고 start로 가는걸로

// 아니야 이거 dfs 해야해
// dfs의 깊이가 우리의 번호이고 지금 우리에서 몇마리를 선택할지
// 지금우리가 기록에 있는 우리인지 검사하고
// 기록 사이에 있는 우리이면 지금 우리에서 i=0에서 count 까지 증가하는 반복문으로 지금 우리에 몇마리 선택할지
// 기록은 똑같이 int[] 에 저장해서 dfs 의 내부에서 지금 우리번호가 기록에 저장된
// 범위 인지 아닌지 어떻게 알지 범위인지 알아도 기록의 시작점인지 끝점인지 도 어떻게 알지?
// dfs 매개변수로 우리번호,기록번호,

// 아니다 그냥 기록에서 겹친 부분부터 값을 0에서부터 올려가면서 가능한지 검사를 해보자
// 겹치는 부분

// 그냥 dfs 로 조합 만들고 다만들고 검사만 하면된다고?
// 그럼 매개변수로 우리번호,햄스터수 를 가지고 가자 
// 종료조건이 현재우리번호가 N 보다 크면 검사하고 return
// 반복문으로 현재 우리에 0에서 X마리 가 들어가는 경우가 다 탐색이 되도록
// visited 처럼 현재 우리에 들어있는 햄스터 수를 기록해서
// 종료조건에서 검사하는걸로
// 검사는 int[][] 에서 start 와 end 범위를 가져와서 visited 에 더한값이 만족을 하는지 확인하는걸로
// 검사시에 현재 들어있는 햄스터수 보다 크거나 같으면 갱신 하도록
// 사전순서로는 어떻게 출력하지 그냥 아무거나 하나 잡아서 정렬 후 출력하자
class Solution {
    
    static int[][] log;
    static List<int[]> result;
    static int maxSum;
    static int[] temp;
    static int N;
    static int X;
    static int M;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T=Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<T+1;test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            N=Integer.parseInt(st.nextToken());
            X=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            temp=new int[N];
            log=new int[M][3];

            for(int i=0;i<M;i++){
                st=new StringTokenizer(br.readLine());
                log[i][0]=Integer.parseInt(st.nextToken()); // start
                log[i][1]=Integer.parseInt(st.nextToken()); // end
                log[i][2]=Integer.parseInt(st.nextToken()); // count 
            }

            result=new ArrayList<>();
            maxSum=0;

            dfs(0,0);

            //result.sort((a,b)->Integer.compare(a[0], b[0]));
            //System.out.println(result.size());


            System.out.printf("#%d",test_case);
            if(result.size()>0){
                for(int i=0;i<result.get(0).length;i++){
                    System.out.printf(" %d",result.get(0)[i]);
                }
                System.out.println();
            }else{
                System.out.printf(" %d\n",-1);
            }
            
        }
       
    }

    static void dfs(int depth,int currentSum){

        if(depth==N){ //우리 갯수만큼 조합을 만들었을때 종료

            boolean isOk=true; //검사통과를 확인 하기위한 변수

            for(int i=0;i<M;i++){  //기록 갯수 만큼 가져와서

                int start=log[i][0]; //시작점
                int end=log[i][1]; //종료점
                int count=log[i][2]; // 시작~종료 까지 count 갯수만큼 햄스터가 있어야함
                int rangeSum=0; //현재 만들어진 조합에서의 start 부터 end 까지 햄스터수를 더한후 저장할 변수

                for(int j=start;j<=end;j++){ // 현재 조합에서 start 부터 end 까지 더한후 
                    rangeSum+=temp[j-1];
                    //System.out.println(temp[j]);
                }
                if(rangeSum!=count){ //더한게 count 랑 다르면 조건을 만족하지 못함
                    isOk=false;
                    break;
                }
            }
            if(isOk){  //조건을 만족하였을때만 결과를 갱신해줌

                if(currentSum>maxSum){  //근데 최대한 많은 햄스터를 담은 조합을 원하니 더크거나 같을경우만 추가

                    result.clear(); // 만약에 더큰 값으로 조합이 만들어지면 그전에 만들어진 조합은 필요가없음
                    result.add(temp.clone()); //여기서 temp변수 자체를 던져주면 참조를 던져주는거라 static 변수temp 그자체를 던져주면 안되고 값을 복사해서 추가해줘야함
                    // for(int aa : temp){
                    //     System.out.printf("%d ",aa);
                    // }
                    // System.out.println(currentSum);
                    maxSum=currentSum; //최대 햄스터수 갱신

                }else if(currentSum==maxSum){ // 햄스터 수가 같을 때도 추가해줘야하나? 보닌까 제일 처음이 사전순이긴하던데
                                            // 일단 구현할땐 몰라서 추가해줌
                    result.add(temp.clone());
                    // for(int aa : temp){
                    //     System.out.printf("%d ",aa);
                    // }
                    // System.out.println(currentSum);
                    maxSum=currentSum;
                }
                
            }
            return;
        }


        for(int i=0;i<=X;i++){ // 조합만들기 반복문 현재 우리가 0에서 x 까지의 햄스터수를 가지고 탐색이 가능하도록
            temp[depth]=i; //현재 우리에 i 만큼의 햄스터수 추가
            dfs(depth+1,currentSum+i);
            //temp[depth]=0; //주석 달다보니 이거 필요없네 어차피 temp 값을 검사하는 조건으로 반복문을 건너뛰거나 하는 경우가 없으니
        }
    }
}
