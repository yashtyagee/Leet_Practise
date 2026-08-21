class Solution {
    public long findKthSmallest(int[] coins, int k) {
        // coins ko sort karke redundant coins hata do
        java.util.Arrays.sort(coins);

        long left = 1, right = (long) k * coins[0];

        while (left < right) {
            long mid = (left + right) / 2;
            if (count(mid, coins) >= k) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    long count(long x, int[] coins) {
        return dfs(0, 1L, x, coins);
    }

    long dfs(int idx, long curLcm, long x, int[] coins) {
        if (idx == coins.length) return 0;
        long total = 0;
        for (int i = idx; i < coins.length; i++) {
            long newLcm = lcm(curLcm, coins[i]);
            if (newLcm > x || newLcm <= 0) continue;
            total += x / newLcm - dfs(i + 1, newLcm, x, coins);
        }
        return total;
    }

    long lcm(long a, long b) { return a / gcd(a,b) * b; }
    long gcd(long a, long b) { return b == 0? a : gcd(b, a % b); }
}