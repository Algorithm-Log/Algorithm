import java.util.*;
import java.io.*;

// deque 를 사용하면 뒤에서 뺀거를 앞으로 넣으면
// 회전임... 말도안됨
// Integer.ParseInt(,16) 하면 16진수를 정수로 변환해주는듯 개사기 ㄷㄷ 
public class Solution{
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=Integer.parseInt(br.readLine());

		for(int test_case=1;test_case<T+1;test_case++){
			StringTokenizer st = new StringTokenizer(br.readLine()," ");

			int N= Integer.parseInt(st.nextToken());
			int K= Integer.parseInt(st.nextToken());

			Deque<Character> box =new ArrayDeque<>();
			String temp = br.readLine();
			for(int i=0;i<N;i++){
				box.add(temp.charAt(i));
			}
			
			Set<Integer> set = new TreeSet<>();

			//set 에 N/4 크기만큼 잘랐을때 숫자를 저장하고
			//회전시키고 다시 N/4 크기만큼 자르고 저장하고 회전 시키고 반복
			

			for(int i = 0; i < N/4; i++){

    		List<Character> box2List = new ArrayList<>(box);

				for(int j = 0; j < 4; j++){

						StringBuilder sb = new StringBuilder();

						for(int k = 0; k < N/4; k++){
								sb.append(box2List.get(j * (N/4) + k));
						}

						set.add(Integer.parseInt(sb.toString(), 16));
				}

    box.addFirst(box.pollLast());
}
			int result=0;
			int index=0;
			for(Integer val : set){
				if(index==set.size()-K){
					result=val;
					break;
				}
				index++;
			}
			
			System.out.printf("#%d %d\n",test_case,result);
		}
	}
}