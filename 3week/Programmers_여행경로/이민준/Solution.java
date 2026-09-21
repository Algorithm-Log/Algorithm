import java.util.*;

class Solution {
    Map<String, List<String>> map = new HashMap<>();
    List<String> result = new ArrayList<>();
    boolean check = false;
    Set<String> set = new HashSet<>();
    int length = 0;
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        length = tickets.length;
        for(String[] str : tickets){
            
            // computeIfAbsent 메서드를 모를 때
            /*
            List<String> list = new ArrayList<>();
            if(!map.containsKey(str[0])){
                list.add(str[1]);
                map.put(str[0], list);
            }else{
                list = map.get(str[0]);
                list.add(str[1]);
                Collections.sort(list);
                map.put(str[0], list);
            }
            */
            set.add(str[0]);
            set.add(str[1]);
            map.computeIfAbsent(str[0], k -> new ArrayList<>()).add(str[1]);
            Collections.sort(map.get(str[0]));
        }
        
        // result.add("ICN");
        dfs("ICN", new ArrayList<>(Arrays.asList("ICN")), 0);
        
        answer = new String[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
    
    public void dfs(String from, List<String> list, int depth){
        
        if(check){
            return;
        }
        if(depth == length){
            Set<String> s = new HashSet(Arrays.asList(list));
            check = true;
            result = new ArrayList<>(list);
            
            return;
        }
        
        if(!map.containsKey(from)) return;
        
        List<String> destinations = map.get(from);
        
        for(int i = 0; i < destinations.size(); i++){
            String to = destinations.get(i);
            
            destinations.remove(i);
            list.add(to);
            dfs(to, list, depth + 1);
            list.remove(list.size()-1);
            destinations.add(i, to);
            // list.remove(to);
        }
        
        /* 탐색이 실패한다는 경우를 생각하지 못한 코드(무조건 성공하는줄;;;)
        if(!map.containsKey(from)) return;
        if(map.get(from).isEmpty()) return; // 맨 마지막
        
        String to = map.get(from).get(0);
        map.get(from).remove(to);
        result.add(to);
        dfs(to);
        */
    }
    
}

/////////////////////////////////////////////////////
/////////////////////////////////////////////////////

import java.util.*;

class Solution {
    
    Map<String, List<String>> map;
    Set<String> visited;
    List<String> result;
    boolean check;
    int length;
    
    public String[] solution(String[][] tickets) {
        
        length = tickets.length;
        map = new HashMap<>();
        result = new ArrayList<>();
        visited = new HashSet<>();
        check = false;
        
        for(String[] str : tickets){
            map.computeIfAbsent(str[0], k -> new ArrayList<>()).add(str[1]);
            Collections.sort(map.get(str[0]));
        }
        
        dfs(0, "ICN", new ArrayList<>(Arrays.asList("ICN")));
        String[] answer = result.toArray(new String[0]);
        return answer;
    }
    
    
    void dfs(int depth, String from, List<String> path){
        if(check) return;
        
        if(depth == length){
            result = new ArrayList<>(path);
            check = true;
            return;
        }
        
        if(!map.containsKey(from)) return;
        
        for(int i = 0; i < map.get(from).size(); i++){
            String str = map.get(from).get(i);
            String s = from+str+i;
            if(visited.contains(s))
                continue;
            visited.add(s);
            
            path.add(str);
            dfs(depth + 1, str, path);
            path.remove(path.size() - 1);
            
            visited.remove(s);
        }
    }
} 

