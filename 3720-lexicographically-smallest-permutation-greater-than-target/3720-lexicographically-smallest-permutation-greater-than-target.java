class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        for (int matchedLen = n - 1; matchedLen >= -1; matchedLen--) {
            int[] curFreq = freq.clone();
            boolean possible = true;
            for (int i = 0; i < matchedLen; i++) {
                char c = target.charAt(i);
                if (curFreq[c - 'a'] <= 0) {
                    possible = false;
                    break;
                }
                curFreq[c - 'a']--;
            }

            if (!possible) continue;
            if (matchedLen == n - 1) {
                char targetChar = target.charAt(matchedLen);
                int bumpChar = -1;
                for (int c = targetChar - 'a' + 1; c < 26; c++) {
                    if (curFreq[c] > 0) {
                        bumpChar = c;
                        break;
                    }
                }
                if (bumpChar != -1) {
                    StringBuilder sb = new StringBuilder(target.substring(0, matchedLen));
                    sb.append((char) ('a' + bumpChar));
                    curFreq[bumpChar]--;
                    for (int c = 0; c < 26; c++) {
                        while (curFreq[c] > 0) {
                            sb.append((char) ('a' + c));
                            curFreq[c]--;
                        }
                    }
                    return sb.toString();
                }
            } else if (matchedLen >= 0) {
                char targetChar = target.charAt(matchedLen);
                int bumpChar = -1;
                for (int c = targetChar - 'a' + 1; c < 26; c++) {
                    if (curFreq[c] > 0) {
                        bumpChar = c;
                        break;
                    }
                }
                if (bumpChar != -1) {
                    StringBuilder sb = new StringBuilder(target.substring(0, matchedLen));
                    sb.append((char) ('a' + bumpChar));
                    curFreq[bumpChar]--;
                    for (int c = 0; c < 26; c++) {
                        while (curFreq[c] > 0) {
                            sb.append((char) ('a' + c));
                            curFreq[c]--;
                        }
                    }
                    return sb.toString();
                }
            }
        }

        return "";
    }
}