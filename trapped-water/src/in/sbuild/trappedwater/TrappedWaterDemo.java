package in.sbuild.trappedwater;

public class TrappedWaterDemo {
    public static void main(String[] args) {
        int[] heights = {0, 1, 0, 2, 1, 0, 3, 1, 0, 1, 2};
        TrappedWaterCalculator calculator = new TrappedWaterCalculator();
        System.out.println("Total water trapped with brute force Algo= " + calculator.calulateTrappedWaterWithBrutForeAlgo(heights));
        System.out.println("Total water trapped with optimized Algo= " + calculator.calculateTrappedWaterTwoPtrAlgo(heights));
    }
}
