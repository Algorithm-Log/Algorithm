//16:15

// 음 일단 단어를 변경할때 조건이 있음
// 두 단어를 비교했을때 다른 알파벳의 갯수가 1개 일때만 변환이 가능
// 갯수가 1개일때만 변경하고 그 단어를 기준으로 다시 변경
// dfs 로 가능하지 않을까? 
// 근데 hot 이 dot 이됬다가 다시 hot 으로 못가게 어떻게하지
// 지나온 단어들을 기록을 할까? 그래서 contains 로 있으면 그걸로는 변경할수없도록
// 지나온 단어중에서 첫번째 요소를 변경해서 했는데
// 나중에 세번째 요소만 변경하면 목표단어가 되는 경우가 있으면 어떡하지?
// 그럼 단어와 바꾼 요소의 자리수도 같이 기록하자
// viisted 는 string 배열 타입으로 하고 값은 word + index
// dfs의 매개변수로는 현재 깊이,현재단어,만 있으면 될듯
// 종료조건은 현재단어가 목표단어일때
// 그리고 또 더 해줘야하나? 반복문으로 words의 모든 요소들과 
// 변경이 가능한지 비교해볼거닌까 언제가는 끝나긴할듯
// 반복문으로 if words 에서 꺼낸 단어와 현재 단어가 
// 다른 카운트가 2개 이상이면 continue
// 다돌았는데 continue 안당했으면 단어를 교체해주고 
// 그 단어로 dfs() 호출

import java.util.HashSet;
import java.util.Set;








class Solution {
    String target;
    int result=Integer.MAX_VALUE;
    String[] words;
    int wordLen;

    Set visited;

    public int solution(String begin, String target, String[] words) {
        this.target=target;
        this.words=words;
        wordLen=begin.length();
        
        visited= new HashSet<String>();
        dfs(0,begin);
        int answer;
        if(result==Integer.MAX_VALUE){
            answer=0;
        }else{
            answer = result;
        }
       
        return answer;
    }


    void dfs(int depth,String word){
        //System.out.println(visited);
        //System.out.printf("%s %d\n", word,depth);
        //System.out.println(word);
        if(word.equals(target)){
            System.out.printf("----%s %d-----\n", word,depth);
            result=Math.min(result,depth);
        }
        
        
        for(int i=0;i<words.length;i++){
            int count=0;
            int index=0;
            for(int j=0;j<wordLen;j++){
                if(words[i].charAt(j)!=word.charAt(j)){
                    count++;
                    index=j;
                    if(count>1){
                        break;
                    }
                }
            }

            if(count==1){
                char[] tempString= word.toCharArray();
                char temp =tempString[index];
                tempString[index]=words[i].charAt(index);
                word = String.valueOf(tempString);
                
                if(!visited.contains(words[i])){
                    visited.add(words[i]);
                    dfs(depth+1,words[i]);
                    visited.remove(word);
                    tempString[index]=temp;
                    word = String.valueOf(tempString);
                }
                
            }
            
            
        }


    }
}