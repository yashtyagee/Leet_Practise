class Solution {
    public int compress(char[] chars) {
        int w=0;
        int i=0;
        while(i<chars.length){
            char ch=chars[i];
            int count=0;
            while(i<chars.length && chars[i]==ch){
                count++;
                i++;
            }
            chars[w++]=ch;
            if(count>1){
                String cst=Integer.toString(count);
                for(char c: cst.toCharArray()){
                    chars[w++]=c;
                }
            }
        }
        return w;
        
    }
}