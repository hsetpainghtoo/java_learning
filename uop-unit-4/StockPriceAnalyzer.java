import java.util.ArrayList;

/**
 * StockPriceAnalyzer
 *
 * Demonstrates array and ArrayList processing in Java by calculating
 * the average, maximum, occurrence count, and cumulative sum of a
 * 10-day set of stock opening prices.
 */
public class StockPriceAnalyzer {

    /**
     * Calculates the average of all prices in the array.
     * @param prices array of stock prices
     * @return the average price
     */
    public static float calculateAveragePrice(float[] prices) {
        float sum = 0;
        for (int i = 0; i < prices.length; i++) {
            sum += prices[i];
        }
        return sum / prices.length;
    }

    /**
     * Finds the highest price in the array.
     * @param prices array of stock prices
     * @return the maximum price
     */
    public static float findMaximumPrice(float[] prices) {
        float max = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > max) {
                max = prices[i];
            }
        }
        return max;
    }

    /**
     * Counts how many times a specific target price appears in the array.
     * @param prices array of stock prices
     * @param targetPrice the price to search for
     * @return the number of occurrences of targetPrice
     */
    public static int countOccurrences(float[] prices, float targetPrice) {
        int count = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] == targetPrice) {
                count++;
            }
        }
        return count;
    }

    /**
     * Computes a running (cumulative) sum of the prices in an ArrayList.
     * @param prices ArrayList of stock prices
     * @return a new ArrayList where each position holds the sum of all
     *         prices up to and including that position
     */
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> prices) {
        ArrayList<Float> cumulativeSum = new ArrayList<Float>();
        float runningTotal = 0;
        for (int i = 0; i < prices.size(); i++) {
            runningTotal += prices.get(i);
            cumulativeSum.add(runningTotal);
        }
        return cumulativeSum;
    }

    public static void main(String[] args) {
        // 10 days of opening stock prices, stored as a float array
        float[] stockPrices = {102.5f, 105.0f, 101.75f, 105.0f, 108.25f,
                                110.0f, 107.5f, 105.0f, 109.75f, 111.0f};

        // The same data stored as an ArrayList, used for the cumulative sum task
        ArrayList<Float> stockPriceList = new ArrayList<Float>();
        for (int i = 0; i < stockPrices.length; i++) {
            stockPriceList.add(stockPrices[i]);
        }

        // Task 1: average price
        float averagePrice = calculateAveragePrice(stockPrices);
        System.out.println("Average stock price: " + averagePrice);

        // Task 2: maximum price
        float maximumPrice = findMaximumPrice(stockPrices);
        System.out.println("Maximum stock price: " + maximumPrice);

        // Task 3: occurrences of a target price
        float targetPrice = 105.0f;
        int occurrences = countOccurrences(stockPrices, targetPrice);
        System.out.println("Occurrences of " + targetPrice + ": " + occurrences);

        // Task 4: cumulative sum
        ArrayList<Float> cumulativeSum = computeCumulativeSum(stockPriceList);
        System.out.println("Cumulative sum: " + cumulativeSum);
    }
}