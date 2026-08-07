class Solution {
    public String smallestNumber(String num, long t) {
        int[] req = new int[4]; // Stores counts for primes 2, 3, 5, 7
        long tempT = t;

        int[] primes = {2, 3, 5, 7};
        for (int i = 0; i < 4; i++) {
            while (tempT % primes[i] == 0) {
                req[i]++;
                tempT /= primes[i];
            }
        }

        if (tempT > 1) return "-1"; // Invalid prime factor in t

        int n = num.length();
        int firstZero = num.indexOf('0');
        int maxPrefix = (firstZero == -1) ? n : firstZero;

        // Prefix factor counts
        int[][] prefixCounts = new int[n + 1][4];
        for (int i = 0; i < maxPrefix; i++) {
            int d = num.charAt(i) - '0';
            for (int k = 0; k < 4; k++) {
                prefixCounts[i + 1][k] = prefixCounts[i][k] + countFactor(d, primes[k]);
            }
        }

        // Try prefix lengths from maxPrefix down to 0
        for (int i = maxPrefix; i >= 0; i--) {
            int[] needed = new int[4];
            for (int k = 0; k < 4; k++) {
                needed[k] = Math.max(0, req[k] - prefixCounts[i][k]);
            }

            if (i == n) {
                if (minDigitsNeeded(needed[0], needed[1], needed[2], needed[3]) == 0) {
                    return num; // num itself is valid
                }
                continue;
            }

            int startDigit = (num.charAt(i) - '0') + 1;
            for (int d = startDigit; d <= 9; d++) {
                int[] nextNeeded = new int[4];
                for (int k = 0; k < 4; k++) {
                    nextNeeded[k] = Math.max(0, needed[k] - countFactor(d, primes[k]));
                }

                int remLen = n - 1 - i;
                if (minDigitsNeeded(nextNeeded[0], nextNeeded[1], nextNeeded[2], nextNeeded[3]) <= remLen) {
                    StringBuilder sb = new StringBuilder(num.substring(0, i));
                    sb.append(d);
                    sb.append(getMinSuffix(remLen, nextNeeded));
                    return sb.toString();
                }
            }
        }
        int minLen = Math.max(n + 1, minDigitsNeeded(req[0], req[1], req[2], req[3]));
        return getMinSuffix(minLen, req);
    }

    private int countFactor(int d, int p) {
        int cnt = 0;
        while (d > 0 && d % p == 0) {
            cnt++;
            d /= p;
        }
        return cnt;
    }
    private int minDigitsNeeded(int c2, int c3, int c5, int c7) {
        int n9 = c3 / 2;
        c3 %= 2;

        int n8 = c2 / 3;
        c2 %= 3;

        int n6 = 0;
        if (c3 == 1 && c2 == 1) {
            n6 = 1; c3 = 0; c2 = 0;
        } else if (c3 == 1 && c2 == 2) {
            n6 = 1; c3 = 0; c2 = 1;
        }

        int n4 = c2 / 2;
        c2 %= 2;

        return n9 + n8 + n6 + n4 + c3 + c2 + c5 + c7;
    }
    private String getMinSuffix(int L, int[] needed) {
        StringBuilder res = new StringBuilder();
        int[] curNeeded = Arrays.copyOf(needed, 4);
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < L; i++) {
            for (int d = 1; d <= 9; d++) {
                int[] nextNeeded = new int[4];
                for (int k = 0; k < 4; k++) {
                    nextNeeded[k] = Math.max(0, curNeeded[k] - countFactor(d, primes[k]));
                }

                if (minDigitsNeeded(nextNeeded[0], nextNeeded[1], nextNeeded[2], nextNeeded[3]) <= L - 1 - i) {
                    res.append(d);
                    curNeeded = nextNeeded;
                    break;
                }
            }
        }
        return res.toString();
    }
}