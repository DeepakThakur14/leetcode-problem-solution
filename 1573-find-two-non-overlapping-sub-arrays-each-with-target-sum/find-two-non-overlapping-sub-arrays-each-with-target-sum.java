class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;

        int[] best = new int[n];
        java.util.Arrays.fill(best, INF);

        java.util.HashMap<Integer, Integer> map = new java.util.HashMap<>();
        map.put(0, -1);

        int prefix = 0;
        int ans = INF;

        for (int i = 0; i < n; i++) {
            prefix += arr[i];

            if (map.containsKey(prefix - target)) {

                int start = map.get(prefix - target);
                int len = i - start;

                if (start >= 0 && best[start] != INF) {
                    ans = Math.min(ans, len + best[start]);
                }

                best[i] = len;
            }

            if (i > 0) {
                best[i] = Math.min(best[i], best[i - 1]);
            }

            map.put(prefix, i);
        }

        return ans == INF ? -1 : ans;
    }
}