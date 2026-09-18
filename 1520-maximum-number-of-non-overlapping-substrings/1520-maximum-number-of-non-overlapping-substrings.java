import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] l = new int[26];
        int[] r = new int[26];
        Arrays.fill(l, -1);
        Arrays.fill(r, -1);

        // Step 1: Record first and last occurrence of each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (l[c] == -1) {
                l[c] = i;
            }
            r[c] = i;
        }

        List<String> res = new ArrayList<>();
        int rightmostChosen = -1;

        // Step 2 & 3: Iterate through possible end indices to expand intervals greedily
        for (int i = 0; i < n; i++) {
            // Only attempt starting an interval at the first occurrence of a character
            int c = s.charAt(i) - 'a';
            if (i != l[c]) continue;

            int newR = getValidRightBoundary(s, i, l, r);
            if (newR != -1) {
                // If this valid substring starts after the previously chosen substring, add it
                if (i > rightmostChosen) {
                    res.add(s.substring(i, newR + 1));
                } else {
                    // Otherwise, this valid substring is contained inside or starts before the current last choice,
                    // but ends earlier or equal, so it replaces the previous one for a shorter length / better fit.
                    res.set(res.size() - 1, s.substring(i, newR + 1));
                }
                rightmostChosen = newR;
            }
        }

        return res;
    }

    private int getValidRightBoundary(String s, int left, int[] l, int[] r) {
        int right = r[s.charAt(left) - 'a'];
        for (int i = left; i <= right; i++) {
            int c = s.charAt(i) - 'a';
            // If a character inside starts before 'left', this 'left' cannot form a valid minimal substring
            if (l[c] < left) {
                return -1;
            }
            right = Math.max(right, r[c]);
        }
        return right;
    }
}