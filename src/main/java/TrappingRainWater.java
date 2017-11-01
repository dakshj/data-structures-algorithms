class TrappingRainWater {

    private int trap(int[] height) {
        int maxLeft = 0;
        int maxRight = 0;

        int left = 0;
        int right = height.length - 1;

        int result = 0;

        // Calculate water storage for each 1-unit-width column on the graph of bars
        while (left <= right) {
            // Add the difference in vertical distance between the max and current
            // of the smaller side, if that side is <= the max of that side, else update max.
            // Thus, if the current height of the smaller side is lower than the max height,
            // then that side (of a partial container) can hold water, because the other side
            // is higher than the current side.
            if (height[left] <= height[right]) {
                if (height[left] >= maxLeft) {
                    maxLeft = height[left];
                } else {
                    result += maxLeft - height[left];
                }

                left++;
            } else {
                if (height[right] >= maxRight) {
                    maxRight = height[right];
                } else {
                    result += maxRight - height[right];
                }

                right--;
            }
        }

        return result;
    }
}
