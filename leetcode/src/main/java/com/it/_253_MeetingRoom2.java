package com.it;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 会议室：给定一个会议时间安排的数组，每个会议时间都包含开始和结束时间{[s1,e1],[s2,e2]}，为了避免会议冲突，哦同时需要充分利用
 * 会议室资源，请你计算最少需要多少间会议室，才可以满足这些会议安排.
 * <p>
 * 解题思路：按照开始时间进行升序排序，然后遍历，遍历之前会议查看是否需要开辟新的会议室。
 *
 * @author : code1997
 * @date : 2021/8/31 22:45
 */
public class _253_MeetingRoom2 {

    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        List<Integer[]> rooms = new ArrayList<>();
        rooms.add(new Integer[]{intervals[0][0], intervals[0][1]});
        //是否需要再开一个房间
        boolean flag = true;
        for (int i = 1; i < intervals.length; i++) {
            //遍历room:这里可以优化，使用最小堆来实现寻找最早结束的堆时间，然后进行比较就可以了(O(log(n))).其中n为room的数量.
            for (Integer[] room : rooms) {
                if (intervals[i][0] >= room[1]) {
                    //不需要开房间
                    flag = false;
                    //更新结束时间
                    room[1] = intervals[i][1];
                    break;
                }
            }
            if (flag) {
                rooms.add(new Integer[]{intervals[i][0], intervals[i][1]});
            }
            flag = true;
        }
        return rooms.size();
    }

    /**
     * 使用最小堆来实现
     */
    public int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        //nlog(n)
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(intervals[0][1]);
        //是否需要再开一个房间
        //时间：2nlog(n)-->nlog(n),空间O(n)
        for (int i = 1; i < intervals.length; i++) {
            //遍历room:这里可以优化，使用最小堆来实现寻找最早结束的堆时间，然后进行比较就可以了(O(log(n))).其中n为room的数量.
            if (intervals[i][0] > heap.peek()) {
                //log(n)
                heap.remove();
            }
            //log(n)
            heap.add(intervals[i][1]);
        }
        return heap.size();
    }

    /**
     * 方法3：对开始时间和结束时间分别进行排序
     */
    public int minMeetingRooms3(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int[] startTimes = new int[intervals.length];
        int[] endTimes = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }
        Arrays.sort(startTimes);
        Arrays.sort(endTimes);
        int rooms = 0;
        int endIndex = 0;
        for (int startTime : startTimes) {
            if (startTime < endTimes[endIndex]) {
                rooms++;
            } else {
                endIndex++;
            }
        }
        return rooms;
    }

    public static void main(String[] args) {
        System.out.println(new _253_MeetingRoom2().minMeetingRooms(new int[][]{{0, 6}, {4, 14}, {8, 24}, {16, 22}, {20, 26}}));
        System.out.println(new _253_MeetingRoom2().minMeetingRooms2(new int[][]{{0, 6}, {4, 14}, {8, 24}, {16, 22}, {20, 26}}));
        System.out.println(new _253_MeetingRoom2().minMeetingRooms3(new int[][]{{0, 6}, {4, 14}, {8, 24}, {16, 22}, {20, 26}}));
    }
}
