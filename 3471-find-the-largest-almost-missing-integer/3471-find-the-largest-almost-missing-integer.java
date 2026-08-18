class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        // count[x] = number of size-k subarrays containing x
        int[] count = new int[51];

        // Generate every subarray of size k
        for (int i = 0; i <= n - k; i++) {

            // Track distinct elements in this subarray
            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {
                seen[nums[j]] = true;
            }

            // Count this subarray once for each distinct element
            for (int x = 0; x <= 50; x++) {
                if (seen[x]) {
                    count[x]++;
                }
            }
        }

        // Find the largest integer appearing in exactly one subarray
        for (int x = 50; x >= 0; x--) {
            if (count[x] == 1) {
                return x;
            }
        }

        return -1;
    }
}