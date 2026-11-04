import java.util.*;

class Solution {

    class DualHeap {

        TreeMap<Integer, Integer> low = new TreeMap<>();
        TreeMap<Integer, Integer> high = new TreeMap<>();

        int lowSize = 0, highSize = 0;
        long lowSum = 0, highSum = 0;

        void add(int num) {

            if (low.isEmpty() || num <= low.lastKey()) {
                low.merge(num, 1, Integer::sum);
                lowSize++;
                lowSum += num;
            } else {
                high.merge(num, 1, Integer::sum);
                highSize++;
                highSum += num;
            }

            balance();
        }

        void remove(int num) {

            if (low.containsKey(num)) {
                removeValue(low, num);
                lowSize--;
                lowSum -= num;
            } else {
                removeValue(high, num);
                highSize--;
                highSum -= num;
            }

            balance();
        }

        void removeValue(TreeMap<Integer, Integer> map, int num) {

            if (map.get(num) == 1) {
                map.remove(num);
            } else {
                map.put(num, map.get(num) - 1);
            }
        }

        void balance() {

            while (lowSize > highSize + 1) {

                int val = low.lastKey();

                removeValue(low, val);
                lowSize--;
                lowSum -= val;

                high.merge(val, 1, Integer::sum);
                highSize++;
                highSum += val;
            }

            while (lowSize < highSize) {

                int val = high.firstKey();

                removeValue(high, val);
                highSize--;
                highSum -= val;

                low.merge(val, 1, Integer::sum);
                lowSize++;
                lowSum += val;
            }
        }

        long getCost() {

            int median = low.lastKey();

            return (long) median * lowSize - lowSum
                 + highSum - (long) median * highSize;
        }
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        // Step 1: Calculate cost of every window
        long[] cost = new long[n - x + 1];

        DualHeap dh = new DualHeap();

        for (int i = 0; i < x; i++) {
            dh.add(nums[i]);
        }

        cost[0] = dh.getCost();

        for (int i = x; i < n; i++) {

            dh.remove(nums[i - x]);
            dh.add(nums[i]);

            cost[i - x + 1] = dh.getCost();
        }

        // Step 2: Dynamic Programming
        long INF = Long.MAX_VALUE / 2;

        long[][] dp = new long[k + 1][n + 1];

        for (long[] row : dp) {
            Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j <= k; j++) {

                if (dp[j][i] == INF) {
                    continue;
                }

                // Option 1: Skip current position
                dp[j][i + 1] = Math.min(
                    dp[j][i + 1],
                    dp[j][i]
                );

                // Option 2: Select current window
                if (j < k && i + x <= n) {

                    dp[j + 1][i + x] = Math.min(
                        dp[j + 1][i + x],
                        dp[j][i] + cost[i]
                    );
                }
            }
        }

        return dp[k][n];
    }
}