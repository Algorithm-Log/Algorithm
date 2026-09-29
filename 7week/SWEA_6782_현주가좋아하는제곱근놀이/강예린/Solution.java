import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= t; test_case++) {
            st = new StringTokenizer(br.readLine());
            long N = Long.parseLong(st.nextToken());
            long cnt = 0;

            while (N != 2) {
                double sqrtDouble= Math.sqrt(N);
                long sqrtLong= (long) Math.sqrt(N);

                if (sqrtDouble == sqrtLong) {
                    N = (long) Math.sqrt(N);
                    cnt++;
                } else {
                    long newSquare = (sqrtLong+1)*(sqrtLong+1);
                    cnt+= newSquare- N;
                    N= newSquare;
                }

            }
            sb.append("#").append(test_case).append(" ").append(cnt).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

}