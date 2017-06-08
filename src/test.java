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

    }


//System.arraycopy( src, 0, dest, 0, src.length );

}
