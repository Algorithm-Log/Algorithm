
import java.util.*;
import java.io.*;

// 12:53

// 길이가 10인 카운팅 배열에 카드를 받을때마다 카운팅을 해주고
// 카드를 받을때마다 그 인덱스 누적합이 3인지 검사 (run)검사
// 받은 카드 기준으로 왼쪽으로 2칸 오른쪽으로 2칸 검사해서 같은 숫자가 3개이상인지 검사(triplet)검사
// 카드를 p1 부터 받고 p2 가 받으닌까
// run검사와 triplet 검사를 하는 함수를 만들고 매개변수로
// int[] 을 넘겨줘서 들어온 배열에 따라 검사하도록 만들고
// 순서를 p1 먼저 그다음 p2 가 하도록 하고 
// 검사하는 함수 안에서 바로 결과값을 바꾸도록 하고 함수 종료후 반복문을 탈출이 가능한가?
// static int 변수하나 만들어서 함수안에서 이 값을 변경하고
// p1의 2가지 검사를 한뒤에 저 int 변수가 변경되었다면 카드를 나눠주는 반복문종료
// run 검사함수는 입력받은 배열을 순회 하면서 값이 3이상이면 int 배열 값을 p1 배열이면 1 p2 배열이면 2 로수정
// 입력받은 배열이 p1인지 p2 인지 어떻게 구분해주지
// int[][] 이렇게 만들고 col 부분 크기를 1로 하고 여기에 p1 이면 1 p2 이면 2 이렇게 넣어주자
// 그래서 int[] 값으로 어떤 플레이어 입력인지 구분하는걸로
// 근데 그럼 하나의 int[][] 로 가능하겠는데
// int [2][6] 인 배열에






class Solution {

    static int[][] playerInput;
    static StringBuilder sb = new StringBuilder();
    static int res;
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T= Integer.parseInt(br.readLine());

        for(int test_case=1;test_case<T+1;test_case++){
            
            StringTokenizer st =new StringTokenizer(br.readLine());

            playerInput = new int[3][10];
            res=-1;
            for(int i=0;i<6;i++){

                playerInput[1][Integer.parseInt(st.nextToken())]++;
                run(1);
                triplet(1);
                if(res!=-1)break; //p1이 이겼으면

                playerInput[2][Integer.parseInt(st.nextToken())]++;
                run(2);
                triplet(2);
                if(res!=-1)break; //p2가 이겼으면
                

            }

            if(res==-1){
                res=0;
            }

            sb.append("#"+test_case+" "+res+"\n");
        }
        System.out.print(sb);
    }

    static void run(int player){

        System.out.println("player : "+player);
        for(int i=0;i<playerInput[player].length;i++){
            System.out.printf("%d ",playerInput[player][i]);
        }
        System.out.println();

        for(int i=0;i<playerInput[player].length;i++){
            if(playerInput[player][i]>=3){
                res=player;
                break;
            }
        }
        
    }

    static void triplet(int player){

        System.out.println("player : "+player);
        for(int i=0;i<playerInput[player].length;i++){
            System.out.printf("%d ",playerInput[player][i]);
        }
        System.out.println();

         for(int i=0;i<playerInput[player].length-2;i++){

            if(playerInput[player][i]==0)continue;
            
            if(playerInput[player][i]!=0 && playerInput[player][i+1] !=0
                && playerInput[player][i+2] !=0
            ){
                res=player;
                break;
            }

        }
    }
}   
