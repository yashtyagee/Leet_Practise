class Solution {
    public String smallestPalindrome(String s) {
        int n=s.length();
        int halfLen=n/2;
        int[]count=new int[26];
        for(int i=0;i<halfLen;i++){
            count[s.charAt(i)-'a']++;

        }
        StringBuilder left=new StringBuilder(halfLen);
        for(int i=0;i<26;i++){
            while(count[i]>0){
                left.append((char)('a'+i));
                count[i]--;

            }
        }
        StringBuilder result=new StringBuilder(n);
        result.append(left);
        if(n%2!=0){
            result.append(s.charAt(halfLen));
        }
        result.append(new StringBuilder(left).reverse());
        return result.toString();

    }
}