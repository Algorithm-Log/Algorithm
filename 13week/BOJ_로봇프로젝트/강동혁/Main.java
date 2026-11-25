import java.io.*;
import java.util.*;

public class Main {

    
    public static void main(String[] args) throws IOException {
        

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            if (line.trim().isEmpty()) continue;

            int width= Integer.parseInt(line.trim());
            int count = Integer.parseInt(br.readLine());

            int[] arr = new int[count];
            for(int i=0;i<count;i++){
                arr[i]=Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr); // 오름차순 정렬

            int[] res = new int[2];       
            boolean can=false;

            int start=0;
            int end=count-1;

            while(start<end){ 

                if(arr[start]+arr[end] > width * 10000000){// 두 숫자의 합이 목표값보다 크면 숫자를 줄여야 함으로 오른쪽에 있는 인덱스를 --
                    end--;
                }else if(arr[start]+arr[end] < width * 10000000){// 두 숫자의 합이 목표값보다 작으면 숫자를 늘려야 함으로 왼쪽에 있는 인덱스를 ++
                    start++;
                }else{// 목표값을 찾았을때
                    
                    // 문제에서 합이 조건을 만족하는 경우가 여러개이면 두 숫자의 차가 최대값인 경우로 정답을 출력 해야함
                    // 정렬 이 되어있는 상태에서 투포인터 알고리즘을 사용하면 
                    // 조건을 만족하는 제일 첫번째 경우가 두 숫자의 차가 최대인 경우임으로 바로 종료 한후 출력

                    res[0]=arr[start];
                    res[1]=arr[end];

                    can=true; // 목표값을 찾이 못하였을때 출력 양식이 달라지기 때문에 목표값을 찾은 여부확인을 위한 boolean 변수
                    break;
                }

            }

            if(can){
                sb.append("yes "+res[0]+" "+res[1]+"\n");
            }else{
                sb.append("danger\n");
            }
        }

        
        System.out.println(sb);

    }

    

}