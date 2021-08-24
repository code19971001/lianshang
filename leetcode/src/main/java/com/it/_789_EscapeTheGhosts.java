package com.it;

/**
 * @author : code1997
 * @date : 2021/8/22 23:01
 */
public class _789_EscapeTheGhosts {

    public static void main(String[] args) {
        System.out.println(escapeGhosts(new int[][]{{1, 0}, {0, 3}}, new int[]{0, 1}));
    }

    /**
     * 如果拦截者可以比目标更快的到达终点，说明是无法逃脱的。
     */
    public static boolean escapeGhosts(int[][] ghosts, int[] target) {
        int targetLine = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] ghost : ghosts) {
            if (Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1]) <= targetLine) {
                return false;
            }
        }
        return true;
    }
}
