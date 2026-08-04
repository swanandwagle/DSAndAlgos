package in.sbuild.trappedwater;

public class TrappedWaterCalculator {

    public int calulateTrappedWaterWithBrutForeAlgo(int heights[]) {
        int totalWater = 0;
        if(heights != null && heights.length > 0) {
            for(int i = 0; i < heights.length; i++) {
                int maxLeft = 0, maxRight=0;
                int leftCtr = i -1;
                int rightCtr = i + 1;
                while(leftCtr >= 0) {
                    maxLeft = Math.max(maxLeft, heights[leftCtr]);
                    leftCtr--;
                }
                while (rightCtr < heights.length) {
                    maxRight = Math.max(maxRight, heights[rightCtr]);
                    rightCtr++;
                }
                if(Math.min(maxLeft, maxRight) - heights[i] > 0) {
                    totalWater += Math.min(maxLeft, maxRight) - heights[i];
                }
            }
        }
        return totalWater;
    }

    public int calculateTrappedWaterTwoPtrAlgo(int heights[]) {
        int totalWater = 0;
        if(heights != null && heights.length > 0) {
            int maxLeft = 0, maxRight=0, leftCtr = 0;
            int rightCtr = heights.length - 1;
            while(leftCtr < rightCtr) {
                if(heights[leftCtr] <= heights[rightCtr]) {
                    if(heights[leftCtr] >= maxLeft) {
                        maxLeft = heights[leftCtr];
                    } else {
                        totalWater += maxLeft - heights[leftCtr];
                    }
                    leftCtr++;
                } else {
                    if(heights[rightCtr] > maxRight) {
                        maxRight = heights[rightCtr];
                    } else {
                        totalWater += maxRight - heights[rightCtr];
                    }
                    rightCtr--;
                }
            }
        }
        return totalWater;
    }
}
