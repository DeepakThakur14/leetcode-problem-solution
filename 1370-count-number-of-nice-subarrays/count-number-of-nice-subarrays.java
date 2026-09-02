class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int curr = 0;
        int ele = 0;
        Map <Integer, Integer> map = new HashMap();
        map.put(0,1);
        for(int i: nums){
            curr+= i%2;
            ele+= map.getOrDefault(curr-k, 0);
            map.put(curr, map.getOrDefault(curr, 0)+1);
        }
        return ele;
    }
}