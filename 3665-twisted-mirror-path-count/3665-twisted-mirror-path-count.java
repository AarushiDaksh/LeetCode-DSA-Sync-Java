
class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        int[][] right = new int[m][n];
        int[][] down = new int[m][n];

        // -1 means the path goes out of bounds
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(right[i], -1);
            java.util.Arrays.fill(down[i], -1);
        }

        // Precompute reflection destinations
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (grid[i][j] == 0) {
                    right[i][j] = i * n + j;
                    down[i][j] = i * n + j;
                } else {

                    // Entered mirror while moving right:
                    // reflect down
                    if (i + 1 < m) {
                        right[i][j] = down[i + 1][j];
                    }

                    // Entered mirror while moving down:
                    // reflect right
                    if (j + 1 < n) {
                        down[i][j] = right[i][j + 1];
                    }
                }
            }
        }

        // Count paths using DP
        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (dp[i][j] == 0) continue;

                long ways = dp[i][j];

                // Try moving right
                if (j + 1 < n) {
                    int dest = right[i][j + 1];

                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] = (dp[r][c] + ways) % MOD;
                    }
                }

                // Try moving down
                if (i + 1 < m) {
                    int dest = down[i + 1][j];

                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;

                        dp[r][c] = (dp[r][c] + ways) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}
