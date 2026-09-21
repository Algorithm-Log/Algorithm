import java.util.*;
import java.io.*;

// 일꾼의 좌표를 완전탐색으로 찾고 찾은 좌표정보를 dfs로 넘겨줘서
// 좌표값으로 부분집합을 만들어서 부분집합의 합이 c보다 작은데 최대인 경우를 찾기

// 일꾼의 좌표 찾기 완탐을 하는방법 4중 반복문

// dfs 의 매개변수 깊이,현재합 2개만 들고 static int[] 변수에 일꾼이 선택한 벌통정보를 저장이 아니라
// 그냥 map이 있을거아니야 그거사용해서 부분집합 만들기 
// dfs의 종료조건은 깊이가 M보다 클때
// 부분집합의 합이 C보다 클때
// 선택 한다 안한다로 부분집합을 만들어주고 



class Solution {

    static int[][] map;
    static int[]scv1;
    static int[]scv2;
    static int C;
    static int result1;
    static int result2;
    static int M;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T=Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<T+1;test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N=Integer.parseInt(st.nextToken());
            M=Integer.parseInt(st.nextToken());
            C=Integer.parseInt(st.nextToken());

            map=new int[N][N];
            for(int row=0;row<N;row++){
                st=new StringTokenizer(br.readLine());
                for(int col=0;col<N;col++){
                    map[row][col]=Integer.parseInt(st.nextToken());
                }
            }
            scv1=new int[M];
            scv2=new int[M];
            int answer=0;
            for(int scv1Y=0;scv1Y<N;scv1Y++){
                for(int scv1X=0;scv1X<=N-M;scv1X++){
                    for(int i=0;i<M;i++){
                        scv1[i]=map[scv1Y][scv1X+i];
                    }
                    for(int scv2Y=scv1Y;scv2Y<N;scv2Y++){
                        int j=0;
                        if(scv1Y==scv2Y){
                            j=scv1X;
                        }
                        for(int scv2X=j;scv2X<=N-M;scv2X++){
                            if(scv1Y==scv2Y && scv2X<scv1X+M && scv2X>=scv1X){
                                continue;
                            }

                            for(int i=0;i<M;i++){
                                scv2[i]=map[scv2Y][scv2X+i];
                            }
                            // for(int temp : scv1){
                            //     System.out.printf("%d ",temp);
                            // }
                            // System.out.println();
                            // for(int temp : scv2){
                            //     System.out.printf("%d ",temp);
                            // }
                            // System.out.println();
                            //System.out.printf("%d %d\n",scv2X,scv2Y);
                            result1=0;
                            result2=0;
                            dfs1(0,0,0);
                            dfs2(0,0,0);
                            answer = Math.max(answer, result1 + result2);
                            
                        }
                    }
                }
            }
            System.out.printf("#%d %d\n",test_case,answer);

        
        }
    
    }

    static void dfs1(int depth,int sum,int calValue){
        

        if(sum>C){
            return;
        }
        if(depth==M){
            result1=Math.max(result1, calValue);
            return;
        }
        //System.out.printf("scv1 %d %d %d\n",depth,sum,calValue);
        

        dfs1(depth+1,sum+scv1[depth],calValue+(scv1[depth]*scv1[depth]));
        dfs1(depth+1,sum,calValue);
        
    }
    static void dfs2(int depth,int sum,int calValue){
        
        

        if(sum>C){
            return;
        }
        if(depth==M){
            result2=Math.max(result2, calValue);
            return;
        }
        //System.out.printf("scv2 %d %d %d\n",depth,sum,calValue);
        

        dfs2(depth+1,sum+scv2[depth],calValue+(scv2[depth]*scv2[depth]));
        dfs2(depth+1,sum,calValue);
        
    }
}
