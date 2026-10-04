
import java.util.*;
import java.io.*;

// 설계시간 14:48 ~ 15:10
// 구현시간 15:10 ~ 16:38

// 10개 중 6개를 뽑고
// 그때 3장의 카드가 연속적인 번호를 가지면 run
// 3장의 카드가 동일한 번호를 가지면 triplet

// run 과 triplet 로만 구성되면 baby gin
// 입력으로 주어진 6개의 숫자들로
// 순열 만들기 문제 만들어진 순열이 run triplet 검사를 해서 맞으면?
// 맞으면이 run이 2번 , run triple 각각 1번 , tripelt 2 번  이 3가지의 경우이면 baby gin
// 그럼 입력으로 주어진 값을 배열 int[] input에 저장하고
// 순서를 만들기위한 boolean [] visited
// dfs 의 매개변수로 현재 만들고있는 카드정보 int[] curCards 가지고 가면서 갱신할까?
// 그럼 매번 new 해줘야하는데 그냥 static 으로 밖에 만들고 curCards[depth] =  input[i];
// dfs 가 return 문을 만나고 돌와왔을떄는 visited 만 돌려주고 배열은 어차피 덮어써져서 변경안해줘도되고
// 로 갱신 해주고 depth == 6 이면 종료해주면서 curCards 가지고 run 이랑 triplet 검사
// run 검사는 크기 3짜리 윈도우 슬라이딩으로 
// 근데 내가 123456 으로 순열을 만들어서 검사를 하는거닌까
// 123 234 345 456 이렇게 run 검사
// tirplet 검사도 123 234 345 456 이렇게만 해주면 될거같은데
// 그럼 하나의 함수에 같이 할까? 음음음
// 같이 가능한가?
// 일단 구분해서 하는걸로 
// run 은 i=0 ~ 6-3 까지 가면서 j=i ~ i+2 까지
// if(curCards[j]=1+curCards[j+1]) 
// tirplet 은 i=0 ~ 6-3 까지 가면서 j=i ~ i+2 까지
// if(curCards[j]=curCards[j+1]) 
// 짜증나네
// baby gin 검사가 count 로 하는게 아니라
// run run
// run triple
// truple run
// triple triple 
// 이 경우만 되는거였음
// 그러면 검사를 

// 0~2 인덱스까지 검사해서 run 인지 triple 인지보고 아니면 바로 종료
// 3~5 인덱스 까지 검사해서 run 인지 triple 인지 보고 아니면 바로 종료


class Solution {

    static int[] input,curCards;
    static boolean res;
    static StringBuilder sb= new StringBuilder();
    static boolean[] visited;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T= Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<T+1;test_case++){
            input=new int[6];
            String temp = br.readLine();

            //아놔 1 이 계속 49로 들어가고 있었네
            //문자열 숫자로 변환하기 하나로 정해두자 어떻게 할지
            for(int i=0;i<6;i++){
                input[i]=Integer.valueOf(temp.charAt(i)-'0'); 
            }

        

     
            res=false;
            curCards = new int[6];
            visited = new boolean[6];

            dfs(0);
            
            sb.append("#"+test_case);
            if(res){
                //
                sb.append(" true\n");
            }else{
                sb.append(" false\n");
            }
            
        }
        System.out.print(sb);
    }

    static void dfs(int depth){
        // 순열 종료 조건
        if(depth==6){

            //만들어진 순열이 베이비진인지 검사
            if(isBabyGin())res=true;    
            
            return;
        }

        for(int i=0;i<6;i++){

            // 순서를 만들기 위해서 visited 사용
            if(visited[i])continue;

            visited[i]=true;
            //현재 만들어지고 있는 순열을 저장해가면서 dfs 탐색을 진행함
            curCards[depth]=input[i];

            dfs(depth+1);

            // 123456 하나만들면 종료조건을 만나서 return 문 이후 여기로 돌아오고
            // 현재 1 2 3 4 5 6 전부 visited 체크가 되어있고 i=5 depth 도 5
            
            // *********
            // 다시 여기로
            // i=4 depth 도 4
            // 1 2 3 4 까지 visited 체크되어있고
            // 반복문 한번더 가능하니 i=5 depth4
            // 1 2 3 4 6 들어가고
            // 1 2 3 4 6 5 이렇게 계속 반복
            visited[i]=false;
            // 1 2 3 4 5 만 체크되어있고 
            // 코드없으므로 자연스럽게 return 해서 *****으로
        }
    }

    static boolean isBabyGin(){

        boolean leftcheck=false;
        boolean rightcheck=false;

        //인덱스 0~2 까지 카드들이 run 인지 triple 인지 검사
        int i=0;
        
        if(run(i) || triplet(i)){
            leftcheck=true;
        }

        i=3;
        
        //인덱스 3~5 까지 카드들이 run 인지 triple 인지 검사
        if(run(i) ||triplet(i)){
            rightcheck=true;
        }

        //왼쪽 오른쪽 둘다 run이나 triple 이면 true반환
        if(leftcheck && rightcheck){
            return true;
        }else{
            return false;
        }
            
        
    }

    static boolean run(int i){
        boolean check=false;
        
        //오름차순으로 연속인지
        if(curCards[i]==1+curCards[i+1] && curCards[i]==2+curCards[i+2]){
            check=true;
        }
        //내림차순으로 연속인지
        if(curCards[i]==curCards[i+1]-1&& curCards[i]==curCards[i+2]-2){
            check=true;
        }

        if(check)return true;
        return false;
        
    }

    static boolean triplet(int i){
        boolean check=false;
        
        if(curCards[i]==curCards[i+1] && curCards[i]==curCards[i+2]){
            check=true;
        }

        if(check){
            return true;
        }

        return false;
        
    }

}
