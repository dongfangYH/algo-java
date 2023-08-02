package com.dongfangyh.algo;

import java.util.*;

/**
 * 描述
 * 给定一个机票的字符串二维数组 [from, to]，子数组中的两个成员分别表示飞机出发和降落的机场地点，对该行程进行重新规划排序。
 * 所有这些机票都属于一个从JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 出发。
 *
 * 1.如果存在多种有效的行程，你可以按字符自然排序返回最小的行程组合。例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 2.所有的机场都用三个大写字母表示（机场代码）。
 * 3.假定所有机票至少存在一种合理的行程并且所有的机票都必须被使用。
 *
 * 输入： [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
 * 输出： ["JFK", "MUC", "LHR", "SFO", "SJC"].
 *
 * 输入：[["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"].
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"]。但是它自然排序更大更靠后。
 * @author henry.liu
 * @date 2023/7/3
 **/
public class FindItinerary {

    public List<String> findItinerary3(List<List<String>> tickets) {
        if (tickets == null || tickets.size() == 0) {
            return Collections.emptyList();
        }

        return null;
    }

    public List<String> findItinerary(List<List<String>> tickets){
        if (tickets == null || tickets.size() == 0){
            return Collections.emptyList();
        }
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> ticket: tickets){
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!map.containsKey(from)){
                map.put(from, new PriorityQueue<>());
            }
            map.get(from).offer(to);
        }
        List<String> result = new LinkedList<>();
        travel(map, result, "JFK");
        Collections.reverse(result);
        return result;
    }

    private void travel(Map<String, PriorityQueue<String>> map, List<String> result, String site) {
        while (map.containsKey(site) && map.get(site).size() > 0){
            travel(map, result, map.get(site).poll());
        }
        result.add(site);
    }


    public List<String> findItinerary2(List<List<String>> tickets) {
        if (tickets == null || tickets.size() == 0){
            return Collections.emptyList();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ft : tickets) {
            String from = ft.get(0);
            String to = ft.get(1);
            if (!map.containsKey(from)) {
                List<String> list = new LinkedList<>();
                list.add(to);
                map.put(from, list);
            } else {
                map.get(from).add(to);
            }
            if (!map.containsKey(to)) {
                List<String> list = new LinkedList<>();
                map.put(to, list);
            }
        }
        map.values().forEach(Collections::sort);
        LinkedList<String> stack = new LinkedList<>();
        stack.push("JFK");
        Map<String, Integer> lastVisitMap = new HashMap<>();
        try{
            while (stack.size() != tickets.size() + 1){
                String current = stack.peek();
                List<String> sites = map.get(current);
                if (sites.size() == 0){
                    stack.pop();
                }else {
                    Integer lastVisit = lastVisitMap.get(current);
                    int idx = (lastVisit == null ? 0 : lastVisit + 1);
                    idx = (idx >= sites.size() ? idx - sites.size() : idx);
                    String to = sites.get(idx);
                    lastVisitMap.put(current, idx);
                    stack.push(to);
                }

            }
        }catch (Exception e){
            throw new RuntimeException(tickets.toString());
        }
        Collections.reverse(stack);
        return stack;
    }



    public static void main(String[] args) {
        List<List<String>> lists = new ArrayList<>();

        // 1
        //lists.add(Arrays.asList("JFK", "KUL"));
        //lists.add(Arrays.asList("JFK", "NRT"));
        //lists.add(Arrays.asList("NRT", "JFK"));

        //2
        lists.add(Arrays.asList("JFK", "SFO"));
        lists.add(Arrays.asList("JFK", "ATL"));
        lists.add(Arrays.asList("SFO", "ATL"));
        lists.add(Arrays.asList("ATL", "JFK"));
        lists.add(Arrays.asList("ATL", "SFO"));

        //3
        //lists.add(Arrays.asList("MUC", "LHR"));
        //lists.add(Arrays.asList("JFK", "MUC"));
        //lists.add(Arrays.asList("SFO", "SJC"));
        //lists.add(Arrays.asList("LHR", "SFO"));
        FindItinerary findItinerary = new FindItinerary();
        System.out.println(findItinerary.findItinerary(lists));
    }
}
