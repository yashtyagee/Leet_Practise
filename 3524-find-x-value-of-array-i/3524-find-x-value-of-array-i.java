import java.util.Arrays;

class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];
        long[] ndp = new long[k];

        for (int num : nums) {
            Arrays.fill(ndp, 0);
            int mod = num % k;

            // Start a new subarray ending at current index
            ndp[mod] += 1;

            // Extend existing subarrays ending at previous index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newR = (int) ((r * 1L * mod) % k);
                    ndp[newR] += dp[r];
                }
            }

            // Accumulate counts into ans
            for (int r = 0; r < k; r++) {
                ans[r] += ndp[r];
            }

            // Swap arrays
            long[] tmp = dp;
            dp = ndp;
            ndp = tmp;
        }

        return ans;
    }
}