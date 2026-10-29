class Solution {
    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] graph = new ArrayList[n];
        for(int i=0; i<n; i++){
            graph[i] = new ArrayList<>();

        }
        for(int[] edge: edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        int LOG = 1;
        while((1 << LOG)<=n){
            LOG++;
        }
        int[][] up = new int[LOG][n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] depth = new int[n];
        int timer =0;
        int [] parent = new int[n];
        int [] order = new int[n];
        int [] iterator = new int[n];

        Arrays.fill(parent, -1);

        int[] stack = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while(top>0){
            int u = stack[top-1];

            if(iterator[u]==0){
                tin[u] = ++timer;
                order[timer - 1] = u;
                up[0][u] = parent[u];

                for (int j = 1; j < LOG; j++) {
                    up[j][u] = up[j - 1][up[j - 1][u]];
                }
            }

            if (iterator[u] < graph[u].size()) {
                int v = graph[u].get(iterator[u]++);

                if (v == parent[u]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                stack[top++] = v;

            } else {
                tout[u] = timer;
                top--;
            }
        }

        int[] bit = new int[n + 2];
        char[] chars = s.toCharArray();

        // Initialize Fenwick Tree
        for (int i = 0; i < n; i++) {
            int mask = 1 << (chars[i] - 'a');

            update(bit, tin[i], mask);
            update(bit, tout[i] + 1, mask);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {
                int node = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);

                if (chars[node] == newChar) {
                    continue;
                }

                int oldMask = 1 << (chars[node] - 'a');
                int newMask = 1 << (newChar - 'a');

                int change = oldMask ^ newMask;

                update(bit, tin[node], change);
                update(bit, tout[node] + 1, change);

                chars[node] = newChar;

            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int ancestor = lca(
                    u, v, up, tin, tout, depth, LOG
                );

                int mask = queryBIT(bit, tin[u])
                         ^ queryBIT(bit, tin[v])
                         ^ (1 << (chars[ancestor] - 'a'));

                answer.add((mask & (mask - 1)) == 0);
            }
        }

        return answer;
    }

    private void update(int[] bit, int index, int mask) {
        for (int i = index; i < bit.length; i += i & -i) {
            bit[i] ^= mask;
        }
    }

    private int queryBIT(int[] bit, int index) {
        int mask = 0;

        for (int i = index; i > 0; i -= i & -i) {
            mask ^= bit[i];
        }

        return mask;
    }

    private int lca(
            int u, int v, int[][] up,
            int[] tin, int[] tout, int[] depth, int LOG) {

        if (isAncestor(u, v, tin, tout)) {
            return u;
        }

        if (isAncestor(v, u, tin, tout)) {
            return v;
        }

        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int difference = depth[u] - depth[v];

        for (int j = LOG - 1; j >= 0; j--) {
            if ((difference & (1 << j)) != 0) {
                u = up[j][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int j = LOG - 1; j >= 0; j--) {
            if (up[j][u] != up[j][v]) {
                u = up[j][u];
                v = up[j][v];
            }
        }

        return up[0][u];
    }
            
        private boolean isAncestor(int u, int v, int[] tin, int[] tout){
            return tin[u]<= tin[v]&& tout[v]<= tout[u];
        }
    
}