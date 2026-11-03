import java.util.*;
import java.io.*;

class Solution {
    public int solution(String name) {
        

        //J E R O E N
        char[] inputR = name.toCharArray();
        //j N E O R E
        char[] inputL = new char[inputR.length];

        inputL[0]=inputR[0];
        for(int i=1;i<inputR.length;i++){
            inputL[i]=inputR[inputR.length-i];
        }

        int res=Integer.MAX_VALUE;

        //1 오른쪽,왼쪽으로 쭉 밀었을때
        for(int i=inputL.length-1;i>=0;i--){
            //왼쪽으로 밀었을때
            if(inputL[i]!='A'){
                res=Math.min(res,i);
                break;
            }

        }
        for(int i=inputR.length-1;i>=0;i--){
            //오른쪽으로 밀었을때
            if(inputR[i]!='A'){
                res=Math.min(res,i);
                break;
            }

        }
        
        //2 오른쪽으로 가다가 왼쪽으로 전환했을때
        for(int i=1;i<inputR.length-1;i++){
            int temp=0;
            
                // i*2 만큼 횟수 더하고
                temp+=i*2;
                //왼쪽으로 얼만큼 가야 전부 변경한지 계산
                for(int j=inputL.length-1-i;j>=0;j--){
                    
                    if(inputL[j]!='A'){
                        
                        temp+=j;
                        res=Math.min(res,temp);
                        break;
                    }

                
                }
        }
        //3 왼쪽으로 가다가 오른쪽으로 전환했을때
        for(int i=1;i<inputL.length-1;i++){
            int temp=0;
            
                // i*2 만큼 횟수 더하고
                temp+=i*2;
                //오른쪽으로 얼만큼 가야 전부 변경한지 계산
                for(int j=inputR.length-1-i;j>=0;j--){
                    
                    if(inputR[j]!='A'){
                        
                        temp+=j;
                        res=Math.min(res,temp);
                        break;
                    }

                }
            
        }
        // 커서이동 끝 문자 변경시 필요한 카운트 계산후 더하기
        for(int i=0;i<inputR.length;i++){
            int temp2 =(int)(inputR[i]-'A');
            if(temp2>12){
                res+=26-temp2 ;
            }else{
                res+=temp2;
            }
            
        }

        if(res ==Integer.MAX_VALUE)res=0;
        int answer = res;
        return answer;
    }
}