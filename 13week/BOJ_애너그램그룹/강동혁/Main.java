import java.io.*;
import java.util.*;

import javax.print.DocFlavor.STRING;

public class Main {

    
    public static void main(String[] args) throws IOException {
        

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line;

        Map<String,List<String>> map = new HashMap<>();

        while ((line = br.readLine()) != null) {

            if (line.trim().isEmpty()) continue;

            // 문자열을 단어 하나하나 분리해서 정렬하기 위해서 char 배열에 담은다음 Arrays.sort 사용해서 정렬후 다시 문자열로 변환
            char[] tempC = line.toCharArray();
            Arrays.sort(tempC);
            String temp = String.valueOf(tempC);

            //map 의 value로 리스트 자료구조 사용후 key에 해당하는 값이 존재하면 해당 리스트를 가져와서 add
            //key 에 해당하는 value가 없으면(처음 등록하는 경우이면) 새로운 리스트를 만들어서 map에 저장후 해당리스트 에 add
            map.computeIfAbsent(temp, k -> new ArrayList<>()).add(line);

        }


        //map의 value 값을 기준으로 정렬을 해야하는데 map은 정렬하기 너무 힘들어서
        //map의 value 값으로 이루어진 list 로 변환 후 list 를 정렬
        
        List<List<String>> list = new ArrayList<>(map.values()); //map의 value 값을로 이루어진 list로 변환

            // value 들의 사전편찬 순으로 먼저 정렬
            for(int i=0;i<list.size();i++){
                list.get(i).sort((a,b) -> a.compareTo(b));
            }

            // list의 요소를 요소들의 갯수크기기준으로 내림차순 정렬
            list.sort((a, b) -> {

                if (a.size() == b.size()) {// 크기가 같다면 문자의 사전편찬 을 기준으로 정렬

                    return a.get(0).compareTo(b.get(0)); //문자열 을 사전편찬 순으로 비교하는 compareTo 메서드
                }
                return Integer.compare(b.size(), a.size());

            });

            
            //출력시 5개의 애너그룹 그룹만 출력하고
            //애너그룹 내에서 중복 단어는 한번만 출력해야함

            for(int i=0;i<Math.min(list.size(),5);i++){// 애너그룹 이 5개 이상이면 5개만 출력되도록

                sb.append("Group of size "+list.get(i).size()+": ");
                for(int j=0;j<list.get(i).size();j++){

                    if(j<list.get(i).size()-1){// 현재 단어와 다음단어를 비교해서 중복 검사중이라 인덱스가 마지막보다 하나 앞까지만 중복 검사를 해줘야함

                        if(list.get(i).get(j).equals(list.get(i).get(j+1))) continue;//중복 단어있을시 하나만 출력되도록
                    }
                    
                    sb.append(list.get(i).get(j)+" ");// 단어 추가
                }
                sb.append(".\n");
            }

        
        System.out.println(sb);

    }

    

}