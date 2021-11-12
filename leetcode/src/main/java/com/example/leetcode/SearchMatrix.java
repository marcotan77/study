package com.example.leetcode;

/**
 * @version 1.0
 * @description:搜索二维矩阵
 * @author: Tcs
 * @date: 2021-03-27 10:09
 **/
public class SearchMatrix {

    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 5;
        boolean b = searchMatrix2(matrix, target);
        System.out.println(b);
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 左下角开始查找
     *
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix2(int[][] matrix, int target) {
        int col = 0, row = matrix.length - 1;
        while (row >= 0 && col < matrix.length) {
            if (target > matrix[row][col]) {
                col++;
            } else if (target < matrix[row][col]) {
                row--;
            } else {
                return true;
            }
        }
        return false;
    }
}
