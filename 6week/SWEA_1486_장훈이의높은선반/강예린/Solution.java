import java.io.*;
import java.util.*;


class Solution {


    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb= new StringBuilder();

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());

            // 탑의 높이
            int B = Integer.parseInt(st.nextToken());

            int[] persons= new int[N];
            int min= 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                persons[i] = Integer.parseInt(st.nextToken());
                min += persons[i];
            }

            min-= B;
            for (int i = 0; i < 1<<N; i++) {
                int h=0;
                for (int j = 0; j < N; j++) {
                    if((i & 1<< j) !=0) h+= persons[j];
                }

                if (h>= B) min= Math.min(min, h-B);

            }

            sb.append("#").append(test_case).append(" ").append(min).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}