import java.io.*;
import java.util.*;


class Solution
{
    public static void main(String[] args) throws Exception{

        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        int t= Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int test_case = 1; test_case <= t; test_case++) {
            st= new StringTokenizer(br.readLine());
            int answer=0;

            int N= Integer.parseInt(st.nextToken());
            int M= Integer.parseInt(st.nextToken());

            int[] forbid= new int[M];

            for (int i = 0; i < M; i++) {
                st= new StringTokenizer(br.readLine());
                forbid[i] |= (1<< Integer.parseInt(st.nextToken())-1);
                forbid[i] |= (1<< Integer.parseInt(st.nextToken())-1);
            }

            for (int i = 0; i < (1<<N); i++) {
                boolean makeBurger= true;

                for (int k : forbid) {
                    if ((i & k) == k) {
                        makeBurger = false;
                        break;
                    }
                }

                if(makeBurger) answer++;
            }


            bw.write("#"+test_case+" "+ answer);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}