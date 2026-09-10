
import java.util.*;
import java.io.*;

// dfs 를 타고가면서 경우의 수를 구하는데
// 이전에 선택한 숫자에 따라서 이후에 선택할수 있는 숫자들이 변경이 가능하도록
// visited 를 만들어서 
// dfs 내부에서 재료를 선택했을때 그재료를 선택하면 제외해야하는 재료도 전부 제외하도록 어떻게 만들까
// 1을 선택하면 1을 선택했을때 제외되어야하는
// visited 는 1차원 배열로 두고 인덱스가 재료번호로
// 1번 재료는 이재료들과 사용이불가능 합니다
// 이걸 어디에 넣어서 관리하지
// dfs 내부의 for 문안에서 i번째 재료를 선택하면
// map에서 키값이 i값에 해당하는 value를 가져와서
// 반복문으로 visited 방문처리를 해주면될듯 
// visited 를 잘못 생각했다
// List<Integer> 배열로 만들고
// 입력으로 1 2 가 들어오면 1 인덱스에 2값추가 2인덱스에도 1값 추가
// 이렇게 만들어야함 근데 이렇게 어캐만듬?
// 그래프 할때 해본거같은데 기억이 안나노 ㅠㅠ
// 걍 해주면 되지
// arr[left].add(right)
// arr[right].add(left)
// 근데 중복이 들어갈거같은데 중복 검사 해주고 없으면 넣기

class Solution {
    static int N;
    static int result;
    static int visited[];
    static Map<Integer,List<Integer>> map;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T=Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<T+1;test_case++){
            StringTokenizer st= new StringTokenizer(br.readLine()," ");

            N=Integer.parseInt(st.nextToken());
            int M=Integer.parseInt(st.nextToken());

            map = new HashMap<>();

            for(int i=0;i<M;i++){
                st=new StringTokenizer(br.readLine()," ");
                // left 재료를 쓰면 right재료를 못씀
                
                int left=Integer.parseInt(st.nextToken());
                int right=Integer.parseInt(st.nextToken());


                // 중복이 들어갈까봐 값이 있으면 못들어가게함
                if(!map.computeIfAbsent(left,k->new ArrayList<Integer>()).contains(right)){

                    map.computeIfAbsent(left,k->new ArrayList<Integer>()).add(right);

                    }
                // left 재료를 사용할때 right를 못쓰는것도 추가해주고
                // right 재료에도 left 를 못쓴다고 추가해줘야함
                if(!map.computeIfAbsent(right,k->new ArrayList<Integer>()).contains(left)){
                    
                    map.computeIfAbsent(right,k->new ArrayList<Integer>()).add(left);

                    }
            }

            


            visited=new int[N+1];
            result=0;

            // 카드 갯수가 0개에서 N개 까지의 경우의수를 구해야해서
            // dfs 의 종료 조건을 반복문으로 1씩 증가하면서 탐색함
            for(int i=0;i<=N;i++){
                dfs(0,i,1);
            }
            
            

            System.out.printf("#%d %d\n",test_case,result);
        }
    
    }

    static void dfs(int depth,int target,int start){

        
        if(depth==target){
            result++;
            // for(int temp : visited){
            //     System.out.print(temp+"  ");
            // }
            // System.out.println();
            return;
        }
        
        
        // (1,3) (3,1) 이 같은 경우의수라고 조합되기 위해서
        // i를 0부터가 아닌 start부터
        for(int i=start;i<=N;i++){


            if(visited[i]==0){ //이 재료가 사용가능할때


                if(map.containsKey(i)){ //이 재료를 사용하면 못사용하게되는 재료가 있는지 확인
                    visited[i]++; // 이 재료를 못쓴다
                    for(int value : map.get(i)){ //이재료를 사용하면 못쓰는 재료들을 가지고와서
                                                //사용할수 없는 재료로 표시
                                                // ++ 해주는 이유는 이 재료가 사용을 못하게하는
                                                // 재료가 여러개 일수있기 때문에
                                                // 1번재료의 사용못함을
                                                // 3번,4번,5번 재료 3개에서 못사용하게 할수도있기때문에
                                                // 지금 사용못하게 하는 재료가 몇개인지 카운트해줘야함
                        visited[value]++;
                    }
                    
                    dfs(depth+1,target,i+1); //재료하나 선택했으니 depth +1
                                             //최대길이는 그대로
                                             //선택한 재료번호 보다 +1 해줘야 순서가 없는 경우의수 조합이 가능

                    for(int value : map.get(i)){ // 분기점에서 i번째 재료를 사용못하게 하고 넘어갔는데
                                                 // 그걸 풀어줘야지 다른 경우의수가 탐색이 가능
                        visited[value]--;
                    }
                    visited[i]--;

                }else{
                    //이건 이 재료를 사용해도 이재료만 못쓴다고 해주면 될때
                    visited[i]++;
                    dfs(depth+1,target,i+1);
                    visited[i]--;
                }
                
                
            }
        }


    }

}
