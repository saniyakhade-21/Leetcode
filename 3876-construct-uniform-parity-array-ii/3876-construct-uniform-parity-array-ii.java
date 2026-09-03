class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for (int num : nums1) {
            min = Math.min(min, num);

            if (num % 2 != 0) {
                hasOdd = true;
            }
        }

        // If minimum is odd, all elements can be made odd.
        if (min % 2 != 0) {
            return true;
        }

        // If minimum is even, an odd element cannot be converted
        // appropriately at the first odd occurrence.
        return !hasOdd;
    }
}