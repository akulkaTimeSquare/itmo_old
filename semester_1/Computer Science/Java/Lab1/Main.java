public class Main {
    public static void main (String[] args) {
        //Task1: fill array with numbers from 5 to 25
        short start = 5;
        short end = 25;
        short[] a = new short[(int) (Math.abs(start - end)) / 2 + 1];
        short c = start;

        for (short i = 0; i < a.length; i++) {
            a[i] = c;
            //move right -> left if start < end, left -> right if start < end 
            c += 2 * (start < end ? 1 : -1); 
        }
        
        for (short element: a) {
            System.out.printf("%3d", element);
        }

        System.out.println("\n");


        //Task2: fill array with random numbers (len = 19)
        double left = -15.0;
        double right = 7.0;
        double[] x = new double[19];

        for (int i = 0; i < x.length; i++) {
            x[i] = (Math.random() * (right - left)) + left; 
        }

        for (double element: x) {
            System.out.printf("%4.2f ", element);
        }

        System.out.println("\n");


        //Task3: fill two-dimensial array by rules
        double array[][] = new double[a.length][x.length];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < x.length; j++) {
                array[i][j] = (double) switch(a[i]) {
                    //rule for a[i] = 23
                    case 23 -> Math.cos(Math.tan(Math.exp(x[j])));
                    //rule for a[i] = 5 || 9 || 15 || 17 || 25
                    case 5, 9, 15, 17, 25 -> Math.atan(1 / (Math.exp(Math.sqrt(Math.sqrt(x[j])))));
                    //rest cases
                    default -> Math.sin(Math.tan(Math.pow(((Math.pow(x[j], (0.5 - x[j]) / x[j]))) / (2 + x[j]), Math.asin((x[j] - 4) / 22))));

                };
            }
        }


        //Task4: print two-dimensional array
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < x.length; j++) {
                System.out.printf("%8.4f ", array[i][j]);
            }
            System.out.println();
        }
   }
}
