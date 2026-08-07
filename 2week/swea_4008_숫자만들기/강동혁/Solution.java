//22:25 ~ 23:34 
//23:47 ~ 00:37

// n개의 숫자 연산자 우선순위는 고려하지 않음
// 왼쪽에서 순서대로
// 어느 케이스가 중복일지는 모르겠고
// 일단 모든 경우의 수를 전부다 돌아서 최대랑 최소를 구하자
// dfs 로 카드의 순서를 변경하면서
// 현재 카드의 배치에서 연산자를 변경해가면서
// 연산자의 순서를 변경해 가면서 최대 최소를 찾는다?
// 그러면 될거같은데
// 아 연산자가 중복으로 올수도있네
// 아 뭐야 숫자는 고정이네
// 그럼 주어진 연산자들로 나올수있는 모든 경우의 수 구하기 문제
// + - * / 를 1234 로 가정하면
// 입력으로 11344 가오면 가능한 경우의수
// 1 1 3 4 4 같은 숫자끼리는 변경을 해도 중복이네
// 반복문을 돌릴때 조건으로 값이 같으면 건너뛰게하기
// 근데 이거 dfs를 써야하나 그냥 반복문으로 완탐하면 안되나?
// 같은값이면 continue 하면 모든 중복이 제거가 가능한가?
// + + - 아 dfs 를 안쓰면 분기점으로 다시 값을 되돌리기가 힘들겠다
// dfs에서는 가져갈 값이 없는거같은데
// 음 operator 를 1차원으로 펴줘야겠는데?
// 으아ㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏㅏ 시간 초과 중복제거해야함

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution{
  static int[] operator;
  static int[] arr;
  static int max;
  static int min;
  static void dfs(int depth){
    for(int i=depth;i<operator.length-1;i++){
      for(int j=i+1;j<operator.length;j++){
        if(operator[i]==operator[j]){
          continue;
        }
        int temp=operator[i];
        operator[i]=operator[j];
        operator[j]=temp;
        //여기서 연산을 해줘야하는데
        int cal=arr[0];
        for(int k=0;k<operator.length;k++){
          if(operator[k]==0){
            cal+=+arr[k+1];
            //System.out.printf("cal :%d %d %d\n",cal,k,operator[k]);
          }else if(operator[k]==1){
            cal-=arr[k+1];
            //System.out.printf("cal :%d %d %d\n",cal,k,operator[k]);
          }else if(operator[k]==2){
            cal*=arr[k+1];
            //System.out.printf("cal :%d %d %d\n",cal,k,operator[k]);
          }else if(operator[k]==3){
            cal/=arr[k+1];
            //System.out.printf("cal :%d %d %d\n",cal,k,operator[k]);
          }
          
        }
        /**for(int a : operator){
        System.out.printf("%d ",a);
        }
        System.out.println();
        System.out.printf("%d\n",cal);
        **/
        max=Integer.max(max, cal);
        min=Integer.min(min, cal);
        dfs(depth+1);
        temp=operator[i];
        operator[i]=operator[j];
        operator[j]=temp;
      }
    }
    

  }

  public static void main(String[] args)throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T=Integer.parseInt(br.readLine());

    for(int test_case=1;test_case<T+1;test_case++){
      int n=Integer.parseInt(br.readLine());
      max=Integer.MIN_VALUE;
      min=Integer.MAX_VALUE;
      int[] temp=new int[4];
      StringTokenizer st= new StringTokenizer(br.readLine());
        
      int operatorLen=0;
      for(int i=0;i<4;i++){
        int count =Integer.parseInt(st.nextToken());
        temp[i]=count;
        operatorLen+=count;
      }
      
      operator=new int[operatorLen];
      int index=0;

      for(int i=0;i<4;i++){
        for(int j=0;j<temp[i];j++){
          operator[index]=i;
          index++;
          //System.out.printf("%d %d %d %d\n",operatorLen,index,index+temp[i],i);
        }  
      }
      
      
      arr=new int[n];
      st= new StringTokenizer(br.readLine());
      for(int i=0;i<n;i++){
        arr[i]=Integer.parseInt(st.nextToken());
      }

      dfs(0);
      System.out.printf("#%d %d\n",test_case,max-min);
    }

    
     
  }
}
