import java.util.*;
import java.io.*;

class Solution {
    static char[] A2Z;
    static char[] input;
    public int solution(String name) {
        A2Z = new char[51];
        input= new char[name.length()*2-1];

        //	B C D E F G H I J K L M N O P Q R S T U V W X Y Z     A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
        //B 부터 Z 까지 입력
        for(int i=0;i<25;i++){
            A2Z[i]=(char)('B' + i);
        }
        //A부터 Z까지 입력
        for(int i=25;i<51;i++){
            A2Z[i]=(char)('A'+ i-25);
        }


        //AN  JAN
        //A 부터 N까지 입력
        for(int i=0;i<name.length()-1;i++){
            input[i]=(char)(name.charAt(i+1));
        }
        //J부터 N까지 입력
        for(int i=name.length()-1;i<name.length()*2-1;i++){
            input[i]=(char)(name.charAt(i-(name.length()-1)));
        }

        // for(char c : A2Z){
        //     System.out.printf("%c ",c);
        // }

        //input 의 중앙에서 좌우로 탐색을 해서 현재위치에서 가장가까운 알파벳먼저 뽑도록
        int middle=name.length()/2;
        int left=middle-1;
        int right=middle+1;
        // 중앙먼저 탐색
        if(input[middle]!='A'){
            // 알파벳 배열에서 확인
            checkCountA2z(input[middle]);
        }
        
        while(){

            left=middle-1;
            right=middle+1;
            if(input[left]!='A'){
                // 알파벳 배열에서 확인
            }
            
            if(input[right]!='A'){
                // 알파벳 배열에서 확인
            }

        }

        
        int answer = 0;
        return answer;
    }

    public int checkCountA2z(char c,int index,int dir,int middle){

        int pivot = 25;
        int left = pivot-1;
        int right = pivot+1;

        while(){
            if(A2z[left]==c){
                // input[index] 수정 근데 2개 해줘야함 input[index] 랑 input[index+middle]
                // 근데 middle 이 왼쪽에서 찾은건지 오른쪽에서 찾은건지가 필요함
                // dir ==0 왼쪽 dir ==1 오른쪽
                if(dir==0){
                    input[index]='A';
                    input[index+middle+1]='A';
                }else if(dir==1){
                    input[index]='A';
                    input[index-(middle+1)]='A';
                }
                
            }
        }
    }

}