class ContainerWithMostWater {

    public int maxArea(final int[] heights) {
        int maxArea = 0;

        int start = 0;
        int end = heights.length - 1;

        while (start < end) {
            maxArea = Math.max(maxArea, getArea(heights, start, end));

            if (heights[start] < heights[end]) {
                start++;
            } else {
                end--;
            }
        }

        return maxArea;
    }

    private int getArea(final int[] heights, final int start, final int end) {
        final int height = Math.min(heights[start], heights[end]);
        final int width = end - start;

        return height * width;
    }
}
