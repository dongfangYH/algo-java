package com.dongfangyh.algo;

import java.util.*;

/**
 * 描述
 * 给定一组 N 人（编号为 1, 2, ..., N）， 我们想把每个人分进任意大小的两组。
 *
 * 每个人都可能不喜欢其他人，那么他们不应该属于同一组。
 *
 * 形式上，如果 dislikes[i] = [a, b]，表示不允许将编号为 a 和 b 的人归入同一组。
 *
 * 当可以用这种方法将每个人分进两组时，返回 true；否则返回 false。
 *
 * 1 <= N <= 2000
 * 0 <= dislikes.length <= 10000
 * 1 <= dislikes[i][j] <= N
 * dislikes[i][0] < dislikes[i][1]
 * 对于 dislikes[i] == dislikes[j] 不存在 i != j
 *
 * 1:
 * 输入：N = 4, dislikes = [[1,2],[1,3],[2,4]]
 * 输出：true
 * 解释：group1 [1,4], group2 [2,3]
 *
 * 2:
 * 输入：N = 3, dislikes = [[1,2],[1,3],[2,3]]
 * 输出：false
 *
 * 3:
 * 输入：N = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
 * 输出：false
 *
 * @author henry.liu
 * @date 2023/7/11
 **/
public class PossibleBipartition {

    private static final Integer groupA = 1;
    private static final Integer groupB = 2;

    /**
     * @param n:  sum of the set
     * @param dislikes: dislikes peoples
     * @return:  if it is possible to split everyone into two groups in this way
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        boolean possible = true;
        if (n <= 2){
            return possible;
        }
        // 用户dislike的用户map
        Map<Integer, Set<Integer>> dislikeMap = new HashMap<>();
        for (int[] dislike : dislikes) {
            int from = dislike[0];
            int to = dislike[1];
            dislikeMap.putIfAbsent(from, new HashSet<>());
            dislikeMap.get(from).add(to);
            dislikeMap.putIfAbsent(to, new HashSet<>());
            dislikeMap.get(to).add(from);
        }
        Map<Integer, Integer> visitMap = new HashMap<>();
        for (Integer user : dislikeMap.keySet()){
            if (!visitMap.containsKey(user)){
                visitMap.put(user, groupA);
            }
            possible &= assignGroup(dislikeMap, visitMap, user);
        }
        return possible;
    }

    public boolean assignGroup(Map<Integer, Set<Integer>> dislikeMap, Map<Integer, Integer> visitMap, Integer currentUser){
        Integer group = visitMap.get(currentUser);
        Set<Integer> dislikeUsers = dislikeMap.get(currentUser);
        boolean result = true;
        for (Integer dislikeUser : dislikeUsers){
            if (!visitMap.containsKey(dislikeUser)){
                Integer dislikeUserGroup = groupA.equals(group) ? groupB : groupA;
                visitMap.put(dislikeUser, dislikeUserGroup);
                result &= assignGroup(dislikeMap, visitMap, dislikeUser);
            }else {
                Integer dislikeUserGroup = visitMap.get(dislikeUser);
                if (Objects.equals(group, dislikeUserGroup)){
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private String arraysToString(int n, int[][] dislikes) {
        StringBuilder builder = new StringBuilder();
        builder.append("n:= ").append(n).append("\r\n");
        for (int[] dislike : dislikes) {
            builder.append(Arrays.toString(dislike));
            builder.append("\r\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        int n = 100;
        int [][] dislike = {
                {97,56},{32,9},{90,52},{9,99},{31,93},{29,83},{60,17},{94,31},{20,84},{61,58},{54,33},{36,44},{33,55},{69,73},{6,84},
                {35,47},{53,89},{21,35},{56,74},{37,47},{27,94},{15,47},{61,65},{55,69},{94,35},{86,40},{58,89},{42,81},{38,93},
                {15,99},{21,29},{72,61},{34,18},{3,97},{64,31},{9,23},{51,60},{3,98},{90,62},{3,61},{56,27},{63,80},{72,9},{7,33},
                {69,10},{22,98},{9,47},{85,69},{63,9},{20,80},{38,59},{42,45},{58,29},{10,77},{16,47},{63,87},{6,37},{58,35},{7,100},
                {34,97},{79,69},{93,35},{61,83},{38,22},{45,44},{44,37},{70,53},{52,33},{3,15},{72,27},{62,34},{85,72},{73,99},{80,53},
                {31,47},{72,84},{40,51},{16,90},{64,16},{6,10},{84,99},{41,55},{29,65},{59,60},{54,59},{64,66},{47,10},{47,79},{2,41},
                {32,2},{84,32},{27,83},{33,62},{52,51},{32,16},{86,62},{99,7},{53,74},{87,57},{31,65},{55,51},
                {77,18},{93,84},{99,29},{36,86},{70,3},{35,41},{38,51},{60,77},{100,9},{6,66},{61,64},{70,57},
                {73,22},{90,95},{20,40},{73,3},{24,59},{71,31},{59,85},{58,95},{16,93},{47,67},{9,22},
                {78,53},{10,100},{54,71},{27,53},{53,29},{40,23},{97,86},{7,20},{20,31},{42,18},{93,52},{86,55},{42,36},{64,2},
                {71,98},{100,62},{55,99},{44,27},{100,36},{90,45},{34,29},{23,84},{64,38},{9,20},{4,71},{98,51},
                {66,47},{36,23},{72,60},{9,86},{67,99},{6,55},{51,31},{87,71},{44,60},{79,72},{65,81},{36,51},
                {34,66},{27,99},{33,87},{65,98},{78,41},{84,83},{34,45},{83,62},{23,18},{37,90},{51,16},{17,31},
                {2,59},{63,62},{24,64},{31,23},{23,55},{35,59},{81,99},{47,27},{6,74},{89,17},{98,99},{24,42},
                {37,63},{74,90},{11,64},{57,66},{52,58},{64,67},{61,99},{85,47},{56,81},{15,64},{79,65},{81,21},{55,100},
                {24,47},{44,62},{34,11},{13,44},{58,97},{100,24},{47,87},{21,85},{51,62},{15,100},{2,21},{10,56},{69,74},
                {6,73},{65,97},{42,67},{53,37},{56,16},{16,44},{98,64},{64,97},{77,52},{99,80},{38,20},{74,86},{89,42},{77,27},
                {2,93},{95,59},{2,33},{44,97},{38,83},{83,37},{97,20},{65,78},{55,32},{53,9},{65,9},{62,3},{77,81},{90,4},
                {61,22},{99,95},{61,77},{85,32},{7,86},{52,41},{17,81},{77,2},{69,38},{56,15},{53,11},{17,67},{62,69},{15,77},
                {17,70},{23,45},{3,9},{90,2},{60,53},{40,83},{11,63},{99,11},{22,13},{6,7},{64,74},{84,53},{45,51},{81,32},
                {67,93},{29,64},{41,74},{58,9},{80,71},{80,23},{90,66},{2,53},{41,27},{86,15},{84,64},{93,40},{17,52},{31,32},{64,52},
                {98,56},{42,37},{72,87},{47,74},{37,69},{65,35},{61,59},{100,31},{78,32},{87,21},{66,69},{89,64},{36,90},{74,32},
                {53,45},{56,95},{72,95},{36,69},{73,57},{95,77},{84,77},{90,61},{4,100},{94,4},{81,41},{32,66},{3,95},{90,89},{42,15},
                {44,89},{87,90},{24,72},{78,33},{61,42},{90,29},{97,47},{63,29},{58,98},{56,24},{77,16},{73,44},{7,22},{98,83},{57,27},
                {35,64},{32,40},{51,79},{13,69},{100,84},{33,37},{51,2},{94,24},{77,73},{56,4},{90,7},{57,52},{21,9},{36,64},{87,23},{53,52},
                {42,54},{59,55},{85,51},{3,66},{74,77},{34,85},{32,95},{18,57},{85,56},{33,24},{69,54},{11,59},{29,59},{31,21},{56,60},{94,61},
                {65,89},{23,7},{67,23},{57,29},{13,57},{100,52},{81,53},{59,4},{42,2},{61,57},{79,94},{15,34},{32,11},{83,74},{87,56},{37,58},
                {57,2},{9,44},{10,64},{83,18},{97,22},{73,34},{56,89},{78,20},{54,6},{60,42},{62,23},{44,80},{99,18},{2,47},{64,18},{41,16},
                {34,27},{78,3},{65,67},{18,22},{81,93},{78,42},{32,18},{23,35},{23,74}
        };
        PossibleBipartition ps = new PossibleBipartition();
        System.out.println(ps.possibleBipartition(n, dislike));
    }
}
