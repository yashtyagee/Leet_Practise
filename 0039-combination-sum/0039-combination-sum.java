class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(0, candidates, target, new ArrayList<>(), ans);
        return ans;
    }
    private void findCombinations(int index, int[] candidates, int target, List<Integer> currentList, List<List<Integer>> ans) {
        if (target == 0) {
            ans.add(new ArrayList<>(currentList));
            return;
        }
        if (index == candidates.length || target < 0) {
            return;
        }
        currentList.add(candidates[index]);
        findCombinations(index, candidates, target - candidates[index], currentList, ans);
        currentList.remove(currentList.size() - 1);
        findCombinations(index + 1, candidates, target, currentList, ans);
    }
}