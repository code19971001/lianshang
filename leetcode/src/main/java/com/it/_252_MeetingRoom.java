package com.it;

import java.util.Arrays;

/**
 * 会议室：给定一个会议时间安排的数组，每个会议时间都包含开始和结束时间{[s1,e1],[s2,e2]}，请你判断一个人能否参与全部的会议.
 * <p>
 * 解题思路：按照开始时间进行升序排序，然后遍历，结束时间要大于前一个会议的结束时间
 *
 * @author : code1997
 * @date : 2021/8/31 22:45
 */
public class _252_MeetingRoom {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return true;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new _252_MeetingRoom().canAttendMeetings(new int[][]{{3, 2}, {2, 1}});
    }
}
