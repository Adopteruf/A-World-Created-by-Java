//Author: Clarence Guo
//By using the combinations of the operator applied on the numbers and a systematically permutation of the numbers, this code can generate the whole solutions of Point24 for a grouo of certain given number.
import java.util.ArrayList;
import java.util.Scanner;
//created for storing data

class base01 {

    double Z;
    double x;
    double y;
    //caculation01 is for storing the calculation procedure
    String caculation01;

    base01(double x, double y, double z, String caculation01) {
        super();
        this.x = x;
        this.y = y;
        //Z is for storing the result of the calculation.
        this.Z = z;
        this.caculation01 = caculation01;
    }
}
//created for storing data

class base02 {

    double sum;
    double left;
    //caculation02 is for storing the calculation procedure
    String caculation02;
    String caculationOFleft;

    base02(double sum, double left, String caculation02, String caculationOFleft) {
        super();
        //sum is for storing the result of the calculations.
        this.sum = sum;
        this.left = left;
        this.caculation02 = caculation02;
        this.caculationOFleft = caculationOFleft;
    }
}

public class Point24 {

    static Scanner kb = new Scanner(System.in);

    // game start, input number first for getting the answer
    public static void main(String[] args) {
        System.out.println("Please, input the four numbers for 24 piont game: ");
        String data = kb.nextLine();
        PotionGame(data);
    }

    // first procedure for finding the suitable calculations
    public static void PotionGame(String data) {
        String number[] = data.split(" ");
        Double[] NUMBER = new Double[4];
        for (int i = 0; i < 4; i++) {
            NUMBER[i] = Double.parseDouble(number[i]);
        }
        //Left is a ArrayList for storing the left number of two number which is under four calculations.
        ArrayList<Integer> Left = new ArrayList();
        //BASE01 is for storing the left number and the new calculations result.
        ArrayList<base01> BASE01 = new ArrayList();
        //X and Y is for storing the left number. Z is for storing the calculations result.
        double X, Y, Z, A, B;
        // A and B is for storing the two number (for four calculations(+ - * /)) by natural order.
        String caculation01;
        for (int x = 0; x < 4; x++) {
            //fix one number of the input numbers.
            caculation01 = null;
            //try each number of the left.
            for (int y = x + 1; y < 4; y++) {
                Left = Left(x, y);
                X = NUMBER[Left.get(0)];
                Y = NUMBER[Left.get(1)];
                A = NUMBER[y];
                B = NUMBER[x];
                //calculation is for recording the calculations procedure.
                Z = A - B;
                caculation01 = A + "-" + B;
                BASE01.add(new base01(X, Y, Z, caculation01));
                Z = B - A;
                caculation01 = B + "-" + A;
                BASE01.add(new base01(X, Y, Z, caculation01));
                Z = A + B;
                caculation01 = A + "+" + B;
                BASE01.add(new base01(X, Y, Z, caculation01));
                Z = A * B;
                caculation01 = A + "*" + B;
                BASE01.add(new base01(X, Y, Z, caculation01));
                if (A != 0 && B != 0) {
                    Z = A / B;
                    caculation01 = A + "/" + B;
                    BASE01.add(new base01(X, Y, Z, caculation01));
                    Z = B / A;
                    caculation01 = B + "/" + A;
                    BASE01.add(new base01(X, Y, Z, caculation01));
                }
            }
        }
        //procedure 2 for getting the result.
        Procedure2(BASE01);
    }

    //try all of the possible calculations for the data got from Procedure1.
    public static void Procedure2(ArrayList<base01> BASE) {
        ArrayList<base02> BASE02 = new ArrayList();
        int len = BASE.size();
        double left, sum, a, b;
        String caculationOFleft;
        Double NUMBER02[];
        //F and S is for recoding down the calculations procedure.
        String caculation02, F, S;
        for (int i = 0; i < len; i++) {
            NUMBER02 = new Double[3];
            NUMBER02[0] = BASE.get(i).x;
            NUMBER02[1] = BASE.get(i).y;
            NUMBER02[2] = BASE.get(i).Z;
            for (int p = 0; p < 3; p++) {
                for (int u = p + 1; u < 3; u++) {
                    caculationOFleft = null;
                    a = NUMBER02[p];
                    b = NUMBER02[u];
                    left = NUMBER02[3 - (p + u)];
                    if (p == 2) {
                        F = "(" + BASE.get(i).caculation01 + ")";
                    } else {
                        F = Double.toString(a);
                    }
                    if (u == 2) {
                        S = "(" + BASE.get(i).caculation01 + ")";
                    } else {
                        S = Double.toString(b);
                    }
                    //when the left number is got from the calculations in the procedure1.
                    if (u != 2 && p != 2) {
                        //use caculationOFleft to record the calculations procedure.
                        caculationOFleft = BASE.get(i).caculation01;
                    }
                    sum = a - b;
                    caculation02 = F + "-" + S;
                    BASE02.add(new base02(sum, left, caculation02, caculationOFleft));
                    sum = b - a;
                    caculation02 = S + "-" + F;
                    BASE02.add(new base02(sum, left, caculation02, caculationOFleft));
                    sum = a + b;
                    caculation02 = F + "+" + S;
                    BASE02.add(new base02(sum, left, caculation02, caculationOFleft));
                    sum = a * b;
                    caculation02 = F + "*" + S;
                    BASE02.add(new base02(sum, left, caculation02, caculationOFleft));
                    if (a != 0 && b != 0) {
                        sum = a / b;
                        caculation02 = F + "/" + S;
                        BASE02.add(new base02(sum, left, caculation02, caculationOFleft));
                        sum = b / a;
                        caculation02 = F + "/" + S;
                        BASE02.add(new base02(sum, left, caculation02, caculationOFleft));
                    }
                }
            }
        }
        RESULT(BASE02);
    }

    //test all of the possible calculations on such four numbers.
    public static void RESULT(ArrayList<base02> BASE02) {
        String caculationOFleft;
        double LEFT, SUM;
        base02 temp01, temp02;
        int length = BASE02.size(), k = 0;
        //here is for removing the repeated solution.
        while (k < length) {
            temp01 = BASE02.get(k);
            for (int l = k + 1; l < length; l++) {
                temp02 = BASE02.get(l);
                if (temp01.caculation02.equalsIgnoreCase(temp02.caculation02)) {
                    BASE02.remove(temp02);
                    l--;
                    length = BASE02.size();
                }
            }
            k++;
        }
        int LEN = BASE02.size(), u = 1;
        for (int r = 0; r < LEN; r++) {
            caculationOFleft = BASE02.get(r).caculationOFleft;
            LEFT = BASE02.get(r).left;
            SUM = BASE02.get(r).sum;
            if (result(LEN, BASE02, LEFT, SUM, BASE02.get(r).caculation02, caculationOFleft, u)) {
                u++;
            }
        }
        if (u == 1) {
            System.out.print("Sorry, the input data is not correct!!");
        } else {
            System.out.print("\r\n\r\nThe above is all of the possible solution.");
        }
    }

    //get all of the correct calculations on the four numbers for 24point game.
    public static boolean result(double LEN, ArrayList<base02> BASE02, double LEFT, double SUM, String CACULATION, String caculationOFleft, int u) {
        boolean result = false;
        //F and S is for recoding down the calculations procedure.
        String S, F;
        S = Double.toString(LEFT);
        F = "(" + CACULATION + ")";
        if (caculationOFleft != null) {
            S = "(" + caculationOFleft + ")";
        }
        if (LEFT + SUM == 24) {
            System.out.print("\r\n\r\n" + "Solution" + u + ": " + S + "+" + F);
            result = true;
            u++;
        }
        if (LEFT * SUM == 24) {
            System.out.print("\r\n\r\n" + "Solution" + u + ": " + S + "*" + F);
            result = true;
        }
        if (LEFT - SUM == 24) {
            System.out.print("\r\n\r\n" + "Solution" + u + ": " + S + "-" + F);
            result = true;
        }
        if (SUM - LEFT == 24) {
            System.out.print("\r\n\r\n" + "Solution" + u + ": " + F + "-" + S);
            result = true;
        }
        if (LEFT != 0 && SUM != 0) {
            if (LEFT / SUM == 24) {
                result = true;
                System.out.print("\r\n\r\n" + "Solution" + u + ": " + S + "/" + F);
            }
            if (SUM / LEFT == 24) {
                result = true;
                System.out.print("\r\n\r\n" + "Solution" + u + ": " + F + "/" + S);
            }
        }
        return result;
    }

    //get the left number of the two numbers under calculations.
    public static ArrayList Left(int x, int y) {
        ArrayList<Integer> left = new ArrayList();
        for (int i = 0; i < 4; i++) {
            if (i != x && i != y) {
                left.add(i);
            }
        }
        return left;
    }
}