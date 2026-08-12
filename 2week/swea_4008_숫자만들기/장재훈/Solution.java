import java.io.*;
import java.util.*;

class Solution {
    static int N;
    static int[] numbers;
    static int max, min;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            // 피연산자 개수
            N = Integer.parseInt(br.readLine());
            
            // 1. 연산자 개수 입력 (순서대로 +, -, *, /)
            int[] ops = new int[4];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < 4; i++) {
                ops[i] = Integer.parseInt(st.nextToken());
            }

            // 2. 피연산자(숫자) 입력
            numbers = new int[N];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            // 3. 최댓값, 최솟값 초기화
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;

            // 4. DFS 탐색 시작 (첫 번째 숫자부터 시작하므로 depth는 1)
            dfs(numbers[0], ops[0], ops[1], ops[2], ops[3], 1);
            
            // 5. 정답 포맷에 맞게 저장 (최댓값 - 최솟값)
            sb.append("#").append(test_case).append(" ").append(max - min).append("\n");
        }
        
        // 전체 결과 출력
        System.out.print(sb.toString());
    }
    
    // 누적 연산 값, + 개수, - 개수, * 개수, / 개수, 깊이
    public static void dfs(int num, int plus, int minus, int mul, int div, int depth) {
        if (numbers.length == depth) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }
        
        // 남은 연산자가 있다면 해당 연산을 수행하고 다음 단계로 재귀 호출
        if (plus > 0) {
            dfs(num + numbers[depth], plus - 1, minus, mul, div, depth + 1);
        }
        if (minus > 0) {
            dfs(num - numbers[depth], plus, minus - 1, mul, div, depth + 1);
        }
        if (mul > 0) {
            dfs(num * numbers[depth], plus, minus, mul - 1, div, depth + 1);
        }
        if (div > 0) {
            dfs(num / numbers[depth], plus, minus, mul, div - 1, depth + 1);
        }
    }
}