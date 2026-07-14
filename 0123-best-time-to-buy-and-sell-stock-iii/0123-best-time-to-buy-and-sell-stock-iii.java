class Solution {
    public int maxProfit(int[] prices) {
        int firstbuy=Integer.MAX_VALUE;
        int secondbuy=Integer.MAX_VALUE;
        int firstsell=0;
        int secondsell=0;
        for(int price:prices){
            firstbuy=Math.min(firstbuy,price);
            firstsell=Math.max(firstsell,price-firstbuy);
            secondbuy=Math.min(secondbuy,price-firstsell);
            secondsell=Math.max(secondsell,price-secondbuy);
            
        }
        return secondsell;


        
    }
}