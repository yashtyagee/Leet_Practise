class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;
        int[] count = new int[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Validate palindrome condition & identify center character
        char center = 0;
        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                center = (char) ('a' + i);
            }
        }
        
        if (oddCount > 1) {
            return "";
        }
        
        // Available frequencies for the first half
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }
        
        // Build the lexicographically smallest possible palindrome from s
        StringBuilder smallestHalf = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < halfCount[i]; k++) {
                smallestHalf.append((char) ('a' + i));
            }
        }
        String smallestPal = buildPalindrome(smallestHalf.toString(), center);
        if (smallestPal.compareTo(target) > 0) {
            return smallestPal;
        }
        
        // 1. Try exact prefix match of target's first half
        int[] currentHalfCount = halfCount.clone();
        boolean canMatchExactPrefix = true;
        char[] firstHalf = new char[half];
        
        for (int i = 0; i < half; i++) {
            char tChar = target.charAt(i);
            if (currentHalfCount[tChar - 'a'] > 0) {
                firstHalf[i] = tChar;
                currentHalfCount[tChar - 'a']--;
            } else {
                canMatchExactPrefix = false;
                break;
            }
        }
        
        if (canMatchExactPrefix) {
            String candidate = buildPalindrome(new String(firstHalf), center);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }
        
        // 2. Backtrack from right to left in the first half to find a larger character at position i
        for (int i = half - 1; i >= 0; i--) {
            // Count remaining frequencies for positions target[0 ... i-1]
            int[] freq = halfCount.clone();
            boolean validPrefix = true;
            for (int j = 0; j < i; j++) {
                char ch = target.charAt(j);
                if (freq[ch - 'a'] > 0) {
                    freq[ch - 'a']--;
                } else {
                    validPrefix = false;
                    break;
                }
            }
            if (!validPrefix) continue;
            
            // Try placing a character larger than target.charAt(i) at index i
            for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    freq[c]--;
                    
                    StringBuilder prefix = new StringBuilder(target.substring(0, i));
                    prefix.append((char) ('a' + c));
                    
                    for (int j = 0; j < 26; j++) {
                        while (freq[j] > 0) {
                            prefix.append((char) ('a' + j));
                            freq[j]--;
                        }
                    }
                    
                    return buildPalindrome(prefix.toString(), center);
                }
            }
        }
        
        return "";
    }
    
    private String buildPalindrome(String firstHalf, char center) {
        StringBuilder sb = new StringBuilder(firstHalf);
        if (center != 0) {
            sb.append(center);
        }
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            sb.append(firstHalf.charAt(i));
        }
        return sb.toString();
    }
}