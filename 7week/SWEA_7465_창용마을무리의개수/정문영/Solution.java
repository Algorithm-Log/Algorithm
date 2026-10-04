import java.util.*;
import java.io.*;

public class Solution {
    static int[] people;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        for (int testcase = 1; testcase <= tc; testcase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            answer = 0;

            people = new int[N + 1]; // 사람 번호가 1부터 시작
            for (int i = 1; i <= N; i++) { // 본인 번호로 parent 초기 설정
                people[i] = i;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            for (int i = 1; i < people.length; i++) {
                if (people[i] == i) // parent와 인덱스가 같은 것만 출력
                    answer += 1;
            }

            System.out.println("#" + testcase + " " + answer);
        }
    }

    static void union(int a, int b) {
        int parent_a = findParent(a);
        int parent_b = findParent(b);

        if (parent_a < parent_b) { // 더 작은 값의 부모로 통일
            people[parent_b] = parent_a;
        }

        else if (parent_a > parent_b) {
            people[parent_a] = parent_b;
        }

        return;
    }

    static int findParent(int idx) {

        // 종료 조건
        if (people[idx] == idx)
            return idx;

        else
            return findParent(people[idx]);
    }
}