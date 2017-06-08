import java.util.Collection;
import java.util.Random;

/**
 * Created by Brendan on 6/8/17.
 *
 */
public class test {


    public static void main(String args[]) {
        int popSize = 32;
        Random rand = new Random();
        String[] X = new String[popSize];

        for (int i = 0; i < X.length; i++) {
            X[i] = String.format("%32s", Integer.toBinaryString(rand.nextInt())).replace(' ', '0');
        }
        int[][] temp;

    }

    private static double rosenbrookFunction(Double[] array) {
        double total = 0;

        for (int i = 0; i < array.length-1; i++) {
            double first = array[i+1] - (array[i] * array[i]);
            double second = 100 * (first * first); //100(xi+1 - xi^2)^2
            double third = (array[i] - 1) * (array[i] - 1); //(xi - 1) ^ 2
            total += (second + third);
        }
        return total;
    }



//System.arraycopy( src, 0, dest, 0, src.length );

}
