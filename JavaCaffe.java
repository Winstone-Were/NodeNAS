public class PredictRunTime {
    public static void main(String[] args) {
        // Input parameters
        double estimated_time = 10; // seconds
        int sample_n = 10000;
        int target_n = 30000;
        double sample_time = 10; // seconds

        // Solve for c
        double c = sample_time / Math.pow(sample_n, 2);

        // Predicted time for target_n
        double predicted_time = c * Math.pow(target_n, 2);
        System.out.println("Predicted time for " + target_n + " elements: " + predicted_time + " seconds");
    }
}
