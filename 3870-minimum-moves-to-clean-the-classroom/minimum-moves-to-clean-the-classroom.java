class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int[][] index = new int[m][n];
        int litterCount = 0;
        int startX = 0, startY = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startX = i;
                    startY = j;
                } else if (ch == 'L') {
                    index[i][j] = litterCount++;
                }
            }
        }

        if (litterCount == 0) {
            return 0;
        }

        int totalMask = (1 << litterCount) - 1;

        boolean[][][][] visited =
            new boolean[m][n][energy + 1][1 << litterCount];

        java.util.Queue<int[]> queue = new java.util.LinkedList<>();

        queue.offer(new int[]{startX, startY, energy, 0});
        visited[startX][startY][energy][0] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                int[] cur = queue.poll();

                int x = cur[0];
                int y = cur[1];
                int currEnergy = cur[2];
                int mask = cur[3];

                if (mask == totalMask) {
                    return moves;
                }

                if (currEnergy == 0) {
                    continue;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                        continue;
                    }

                    if (classroom[nx].charAt(ny) == 'X') {
                        continue;
                    }

                    int nextEnergy = currEnergy - 1;
                    int nextMask = mask;

                    char cell = classroom[nx].charAt(ny);

                    if (cell == 'R') {
                        nextEnergy = energy;
                    }

                    if (cell == 'L') {
                        nextMask |= (1 << index[nx][ny]);
                    }

                    if (!visited[nx][ny][nextEnergy][nextMask]) {
                        visited[nx][ny][nextEnergy][nextMask] = true;
                        queue.offer(new int[]{
                            nx, ny, nextEnergy, nextMask
                        });
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}