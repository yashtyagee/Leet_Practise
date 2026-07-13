class Solution {
    public int minimumCardPickup(int[] cards) {
        HashMap<Integer,Integer>map=new HashMap<>();
        int minCards=Integer.MAX_VALUE;
        for(int i=0;i<cards.length;i++){
            if(map.containsKey(cards[i])){
                int l=map.get(cards[i]);
                int cl=i-l+1;
                minCards=Math.min(minCards,cl);
            }
            map.put(cards[i],i);
        }
        return minCards==Integer.MAX_VALUE?-1:minCards;
        
        
    }
}