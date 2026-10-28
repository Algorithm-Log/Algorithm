/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// double b = 1.0;
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.io.*;

class Solution
{
    static boolean[] visited;
    static int[] arr, select;
    static boolean answer;

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


        for(int test_case = 1; test_case <= T; test_case++)
        {
            String str = br.readLine();
            arr = new int[6];
            visited = new boolean[10];
            select = new int[6];
            for(int i = 0; i < 6; i++){
                arr[i] = str.charAt(i) - '0';
            }
            answer = false;
            dfs(0);
            System.out.println("#" + test_case + " " + answer);
        }
    }
    public static void dfs(int index){
        if(index == 6){
            if((isRun(0)&&isRun(3))
                    || (isTriplet(0) && isTriplet(3))
                    || (isRun(0) && isTriplet(3))
                    || (isRun(3) && isTriplet(0))){
                answer = true;
            }
            return;
        }
        for(int i = 0; i < 6; i++){
            if(visited[i])   continue;
            visited[i] = true;
            select[index] = arr[i];
            dfs(index + 1);
            visited[i] = false;
        }
    }
    public static boolean isRun(int start){
        int a = select[start];
        int b = select[start+1];
        int c = select[start+2];

        if(a + 1== b && b + 1 == c){
            return true;
        }
        return false;
    }
    public static boolean isTriplet(int start){
        int a = select[start];
        int b = select[start+1];
        int c = select[start+2];

        if(a == b && b == c){
            return true;
        }
        return false;
    }
}