import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int T= Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();
        char[] text, pattern;
        int[] pi;
        int answer;

        for (int test_case = 1; test_case <= T; test_case++) {
            text= br.readLine().toCharArray();
            pattern= br.readLine().toCharArray();
            int len= pattern.length;

            pi= new int[len];
            makePi(pattern, pi);
            answer= matchText(text, pattern, pi);

            sb.append("#").append(test_case).append(" ").append(answer).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    } //end of main

    private static int matchText(char[] text, char[] pattern, int[] pi) {
        int j= 0;
        int i= 0;
        int cnt= 0;

        while (i < text.length){


            if (text[i]== pattern[j]) {
                j++;
                i++;

                // 다 찾았다고 끝내지말고 쭉 해야함
                // 끝까지 다봤으면 j가 마지막 인덱스를 넘어가기에 j-1
                if (j== pi.length) {
                    cnt ++;
                    j= pi[j-1];
                }

            } else if (j==0) {
                i++;
            } else j= pi[j-1];

        }

        return cnt;
    }

    private static void makePi(char[] pattern, int[] pi) {
        pi[0]= 0; // 자기 자신은 포함 시키지 않기에 첫글자의 LPS는 무조건 0

        int i= 1;

        // j는 현재 어디까지 맞았다를 표시
        int j= 0;

        while (i < pi.length) {
            if (pattern[i] == pattern[j]) {
                pi[i] = j + 1; // 현재까지 맞춘 인덱스에서 1을 더함
                j++;
                i++;
            } else if (j == 0) {
                pi[i] = 0;
                i++;
            } else {
                // 어디까지 맞았는지 이전 pi배열을 보고 돌아감
                j = pi[j - 1];
            }
        }

    }
} // end of class