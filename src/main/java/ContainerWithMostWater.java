class ContainerWithMostWater {

    public int maxArea(final int[] heights) {
        int maxArea = 0;

        int start = 0;
        int end = heights.length - 1;

        while (start < end) {
            maxArea = Math.max(maxArea, getArea(heights, start, end));

            // Keep the higher wall still in consideration, and go to the
            // next index of the smaller wall.
            if (heights[start] < heights[end]) {
                start++;
            } else {
                end--;
            }
        }

        return maxArea;
    }

    private int getArea(final int[] heights, final int start, final int end) {
        // Height of the container is the minimum of the two vertical lines,
        // since water will overflow from the lower wall if we try to fill it more than its height.
        final int height = Math.min(heights[start], heights[end]);

        // Length of the base of the container
        final int width = end - start;

        return height * width;
    }
}
