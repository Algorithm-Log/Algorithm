import java.util.*;
import java.io.*;

class Solution {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {

            long n = Long.parseLong(br.readLine());
            long count = 0;

            while (n != 2) {
                //n 이 9이면 root는3
                //n 이 10이면 root는 3.~~~ 인데 (long) 으로 형변환 하는과정에서 소수점이 사라짐
                // 현재 n의 제곱근의 정수 부분
                long root = (long) Math.sqrt(n);

                // 1. 현재 n이 완전제곱수인 경우
                if (root * root == n) {
                    // n을 루트 연산한 값으로 변경
                    // 9 -> 3
                    n = root;
                    count++;

                }else{

                    // 2. 완전제곱수가 아니라면
                    // 현재 n보다 큰 가장 가까운 제곱수 찾기
                    // 10이면 3 인데
                    // nextRoot 4가 되고
                    // nextSquare 16
                    long nextRoot = root + 1;
                    long nextSquare = nextRoot * nextRoot;

                    // n -> nextSquare 까지 +1 하는 횟수

                    //16-10 = 6
                    //10은 6번 연산하면 10보다 큰 다음 완전 제곱수로 이동이가능
                    count += nextSquare - n;

                    // 다음 제곱수로 이동
                    n = nextSquare;
                }
            }

            System.out.printf("#%d %d\n", test_case, count);
        }
    }
}