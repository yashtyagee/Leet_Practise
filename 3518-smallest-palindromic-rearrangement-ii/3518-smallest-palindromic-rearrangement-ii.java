import java.util.Arrays;

class Solution {
    public String smallestPalindrome(String s, int k) {
        int n = s.length();
        int m = n / 2;
        int[] count = new int[26];
        for (int i = 0; i < m; i++) {
            count[s.charAt(i) - 'a']++;
        }
        char mid = ' ';
        if (n % 2 != 0) {
            mid = s.charAt(m);
        }
        int CAP = k + 1;
        int[][] C = new int[m + 1][m + 1];
        for (int i = 0; i <= m; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = Math.min(CAP, C[i - 1][j - 1] + C[i - 1][j]);
            }
        }
        long totalPerms = countPermutations(count, m, C, CAP);
        if (totalPerms < k) {
            return "";
        }
        char[] half = new char[m];
        int remLen = m;
        for (int pos = 0; pos < m; pos++) {
            for (int c = 0; c < 26; c++) {
                if (count[c] > 0) {
                    count[c]--;
                    long ways = countPermutations(count, remLen - 1, C, CAP);
                    if (ways >= k) {
                        half[pos] = (char) ('a' + c);
                        remLen--;
                        break;
                    } else {
                        k -= ways;
                        count[c]++;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(half);
        if (n % 2 != 0) {
            sb.append(mid);
        }
        for (int i = m - 1; i >= 0; i--) {
            sb.append(half[i]);
        }

        return sb.toString();
    }
    private long countPermutations(int[] count, int totalLen, int[][] C, int cap) {
        long ways = 1;
        int rem = totalLen;
        for (int c : count) {
            if (c > 0) {
                ways = Math.min(cap, ways * C[rem][c]);
                rem -= c;
            }
        }
        return ways;
    }
}