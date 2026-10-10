import java.util.*;

class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] diff = new int[n];

        long sum = 0;
        int max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            sum += diff[i];
            max = Math.max(max, diff[i]);
        }

        if (sum <= k) return 0;

        int left = 0, right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long required = 0;

            for (int d : diff) {
                if (d > mid) {
                    required += d - mid;
                }
            }

            if (required <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int limit = left;
        long remaining = k;
        long answer = 0;

        for (int d : diff) {
            if (d > limit) {
                remaining -= d - limit;
                answer += (long) limit * limit;
            } else {
                answer += (long) d * d;
            }
        }

        // Use remaining operations to reduce differences at the limit by one.
        for (int d : diff) {
            if (remaining > 0 && d >= limit && d > 0) {
                answer -= 2L * limit - 1;
                remaining--;
            }
        }

        return answer;
    }
}