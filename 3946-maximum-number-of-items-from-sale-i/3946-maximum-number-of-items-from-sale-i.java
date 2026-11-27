class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            minPrice = Math.min(minPrice, item[1]);
        }

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1000000);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int free = 0;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    free++;
                }
            }

            int cost = items[i][1];
            int value = free + 1;

            for (int b = budget; b >= cost; b--) {
                dp[b] = Math.max(dp[b], dp[b - cost] + value);
            }
        }

        int ans = 0;

        for (int b = 0; b <= budget; b++) {
            if (dp[b] >= 0) {
                ans = Math.max(ans,
                    dp[b] + (budget - b) / minPrice);
            }
        }

        return ans;
    }
}