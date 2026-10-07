import java.util.*;

class Solution {

    public List<String> removeInvalidParentheses(String s) {

        List<String> result = new ArrayList<>();

        // Find minimum number of removals
        int leftRemove = 0;
        int rightRemove = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                leftRemove++;
            }

            else if (c == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                }
                else {
                    rightRemove++;
                }
            }
        }

        // HashSet automatically removes duplicates
        Set<String> set = new HashSet<>();

        backtrack(
            s,
            0,
            0,
            leftRemove,
            rightRemove,
            new StringBuilder(),
            set
        );

        result.addAll(set);

        return result;
    }


    private void backtrack(
        String s,
        int index,
        int balance,
        int leftRemove,
        int rightRemove,
        StringBuilder current,
        Set<String> result) {

        // Invalid: too many ')'
        if (balance < 0) {
            return;
        }

        // Reached end
        if (index == s.length()) {

            if (balance == 0 &&
                leftRemove == 0 &&
                rightRemove == 0) {

                result.add(current.toString());
            }

            return;
        }

        char c = s.charAt(index);

        // -------------------------
        // CASE 1: '('
        // -------------------------
        if (c == '(') {

            // Option 1: Remove '('
            if (leftRemove > 0) {

                backtrack(
                    s,
                    index + 1,
                    balance,
                    leftRemove - 1,
                    rightRemove,
                    current,
                    result
                );
            }

            // Option 2: Keep '('
            current.append('(');

            backtrack(
                s,
                index + 1,
                balance + 1,
                leftRemove,
                rightRemove,
                current,
                result
            );

            current.deleteCharAt(current.length() - 1);
        }

        // -------------------------
        // CASE 2: ')'
        // -------------------------
        else if (c == ')') {

            // Option 1: Remove ')'
            if (rightRemove > 0) {

                backtrack(
                    s,
                    index + 1,
                    balance,
                    leftRemove,
                    rightRemove - 1,
                    current,
                    result
                );
            }

            // Option 2: Keep ')'
            if (balance > 0) {

                current.append(')');

                backtrack(
                    s,
                    index + 1,
                    balance - 1,
                    leftRemove,
                    rightRemove,
                    current,
                    result
                );

                current.deleteCharAt(current.length() - 1);
            }
        }

        // -------------------------
        // CASE 3: Letter
        // -------------------------
        else {

            current.append(c);

            backtrack(
                s,
                index + 1,
                balance,
                leftRemove,
                rightRemove,
                current,
                result
            );

            current.deleteCharAt(current.length() - 1);
        }
    }
}