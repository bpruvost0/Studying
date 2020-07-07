package LeetCode.Medium;

/**
 * In a gold mine grid of size m * n, each cell in this mine has an integer representing the amount of gold in that cell, 0 if it is empty.
 *
 * Return the maximum amount of gold you can collect under the conditions:
 *
 * Every time you are located in a cell you will collect all the gold in that cell.
 * From your position you can walk one step to the left, right, up or down.
 * You can't visit the same cell more than once.
 * Never visit a cell with 0 gold.
 * You can start and stop collecting gold from any position in the grid that has some gold.
 *
 *
 * Example 1: *
 * Input: grid = [[0,6,0],[5,8,7],[0,9,0]]
 * Output: 24
 * Explanation:
 * [[0,6,0],
 *  [5,8,7],
 *  [0,9,0]]
 * Path to get the maximum gold, 9 -> 8 -> 7.
 *
 * Example 2: *
 * Input: grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
 * Output: 28
 * Explanation:
 * [[1,0,7],
 *  [2,0,6],
 *  [3,4,5],
 *  [0,3,0],
 *  [9,0,20]]
 * Path to get the maximum gold, 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7.
 *
 *
 * Constraints:
 *
 * 1 <= grid.length, grid[i].length <= 15
 * 0 <= grid[i][j] <= 100
 * There are at most 25 cells containing gold.
 * */


public class PathWithMaximumGold {
  private static final int[] directions = new int[] {0,1,0,-1,0};

  public int getMaximumGold(int[][] grid) {
    int m = grid.length;
    int n = grid[0].length;
    int maxGold = 0;
    for(int r = 0; r < m; r++) {
      for(int c = 0; c < n; c++) {
        maxGold = Math.max(maxGold, findMaxGold2(grid, r, c));
      }
    }
    return maxGold;
  }

  private int findMaxGold(int[][] grid, int r, int c) {
    if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
      return 0;
    }

    int origin = grid[r][c];
    grid[r][c] = 0; //Mark as visited

    int maxGold = 0;
    for(int i = 0; i < 4; i ++) {
      maxGold = Math.max(maxGold,
          findMaxGold2(grid,
              directions[i] + r,
              directions[i+1] + c));
    }

    grid[r][c] = origin;
    return maxGold + origin;
  }

  private int findMaxGold2(int[][] grid, int r, int c) {
    if(r < 0 || c < 0 || r >= grid.length || c >= grid[0].length || grid[r][c] == 0) {
      return 0;
    }

    int origin = grid[r][c];
    grid[r][c] = 0; //Mark as visited

    int maxGold = 0;
    maxGold = Math.max(maxGold, findMaxGold2(grid, r + 1, c));
    maxGold = Math.max(maxGold, findMaxGold2(grid, r - 1, c));
    maxGold = Math.max(maxGold, findMaxGold2(grid, r, c + 1));
    maxGold = Math.max(maxGold, findMaxGold2(grid, r, c - 1));


    grid[r][c] = origin;
    return maxGold + origin;
  }
}
