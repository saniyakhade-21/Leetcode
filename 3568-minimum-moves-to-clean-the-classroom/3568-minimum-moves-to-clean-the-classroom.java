import java.util.*;

class Solution {

    static class State {
        int r, c, mask, energy, moves;

        State(int r, int c, int mask, int energy, int moves) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
            this.moves = moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0, startC = 0;
        int litterCount = 0;

        // Store bit number for every litter cell
        int[][] litterId = new int[m][n];
        for (int[] row : litterId) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int allCollected = (1 << litterCount) - 1;

        if (litterCount == 0) {
            return 0;
        }

        Queue<State> queue = new ArrayDeque<>();

        // visited[r][c][mask][remaining energy]
        boolean[][][][] visited =
                new boolean[m][n][1 << litterCount][energy + 1];

        queue.offer(new State(startR, startC, 0, energy, 0));
        visited[startR][startC][0][energy] = true;

        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        while (!queue.isEmpty()) {

            State cur = queue.poll();

            if (cur.mask == allCollected) {
                return cur.moves;
            }

            // If energy is zero, movement is impossible.
            // Normally entering R already restores energy.
            if (cur.energy == 0) {
                continue;
            }

            for (int[] dir : directions) {

                int nr = cur.r + dir[0];
                int nc = cur.c + dir[1];

                if (nr < 0 || nr >= m ||
                    nc < 0 || nc >= n ||
                    classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                int nextEnergy = cur.energy - 1;
                int nextMask = cur.mask;

                char nextCell = classroom[nr].charAt(nc);

                // Collect litter
                if (nextCell == 'L') {
                    int id = litterId[nr][nc];
                    nextMask |= (1 << id);
                }

                // Reset energy
                if (nextCell == 'R') {
                    nextEnergy = energy;
                }

                // If all litter is collected after this move,
                // this is automatically the shortest path.
                if (nextMask == allCollected) {
                    return cur.moves + 1;
                }

                if (!visited[nr][nc][nextMask][nextEnergy]) {

                    visited[nr][nc][nextMask][nextEnergy] = true;

                    queue.offer(
                        new State(
                            nr,
                            nc,
                            nextMask,
                            nextEnergy,
                            cur.moves + 1
                        )
                    );
                }
            }
        }

        return -1;
    }
}