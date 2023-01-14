package Lib;

import java.util.*;

import static java.lang.Math.pow;
import static Lib.Matrix.*;
public class ONB {
    private static final int DIMENSION = 239;
    private static int[][] MATRIX;

    public static boolean ExistingONB(){
        int p = 2 * DIMENSION + 1;
        if(isPrime(p)){

            if(pow(2, 2*DIMENSION) % p == 1) { // 1 condition -- k = 2m
                return true;
            }
            return p % 4 == 3 && pow(2, DIMENSION) % p == 1; // 2 condition -- p = 3 (mod 4) and k = m
        }
        return false;
    }

    private static boolean isPrime(int number)
    {
        for (int i = 2; i < number; i++)
        {
            if((number% i) == 0)
            {
                return false;
            }
        }

        return true;
    }

    public static void MatrixFinding(){
        if(!ExistingONB()){
            return;
        }
        MATRIX = new int[DIMENSION][DIMENSION];
        int p = 2*DIMENSION + 1;
        for(int i = 0; i < DIMENSION; i++){
            for(int j = 0; j < DIMENSION; j++){
                double condition1 = pow(2, i) + pow(2, j);
                double condition2 = pow(2, i) - pow(2, j);
                double condition3 = -pow(2, i) + pow(2, j);
                double condition4 =  (-pow(2, i) - pow(2, j)) % p + p;
                if(condition2 < 0){
                    condition2 = condition2 % p + p;
                }
                if(condition3 < 0){
                    condition3 = condition3 % p + p;
                }

                if(condition1 %p==1){
                    MATRIX[i][j] = 1;
                } else if (condition2 %p==1) {
                    MATRIX[i][j] = 1;
                } else if (condition3 %p==1) {
                    MATRIX[i][j] = 1;
                } else if (condition4 %p==1) {
                    MATRIX[i][j] = 1;
                } else {
                    MATRIX[i][j] = 0;
                }
            }
        }
    }

    public static void getMatrix(){
        if(MATRIX == null){
            MatrixFinding();
        }
    }

    public static int[] UnitsArrToArrBinVec(ArrayList<Integer> elem){ // [1,2,3] -> [0,1,1,1]
        int[] finishedElem = new int[DIMENSION];
        for (int i = 0; i < DIMENSION; i++) {
            if(elem.contains(i)){
                finishedElem[i] = 1;
            }
        }
        return finishedElem;
    }

    public static ArrayList<Integer> ArrBinVecToUnitsArr(int[] elem){ // [0,0,1,1] -> [2,3]
        ArrayList<Integer> res = new ArrayList<>(DIMENSION);
        for (int i = 0; i < DIMENSION; i++) {
            if(elem[i] == 1){
                res.add(i);
            }
        }
        return res;
    }

    public static ArrayList<Integer> StrBinVecToUnitsArr(String elem){ // 0011 -> [2,3]
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < DIMENSION; i++){
            if(elem.charAt(i) == '1'){
                res.add(i);
            }
        }
        return res;
    }

    public static String UnitsArrToStrBin(ArrayList<Integer> elem){ //[1,0] -> 1100
        StringBuilder res = new StringBuilder(DIMENSION);
        for (int i = 0; i < DIMENSION; i++){
            if(elem.contains(i)){
                res.append("1");
            }
            else{
                res.append("0");
            }
        }
        return res.toString();
    }

    public static ArrayList<Integer> add(ArrayList<Integer> elem1, ArrayList<Integer> elem2){
        ArrayList<Integer> res = new ArrayList<>(elem1);
        res.addAll(elem2);
        res = mod2(res);
        res.sort(Collections.reverseOrder());
        return res;
    }

    private static ArrayList<Integer> mod2(ArrayList<Integer> elem){
        Set<Integer> setElem = new HashSet<>(elem);
        setElem.removeIf(integer -> Collections.frequency(elem, integer) % 2 == 0);

        return new ArrayList<>(setElem);
    }

    public static ArrayList<Integer> multiply(ArrayList<Integer> elem1, ArrayList<Integer> elem2){
        int[] res = new int[DIMENSION];
        for(int i = 0; i < DIMENSION; i++){
            int [][] a = multiplyByMatrix(
                    Objects.requireNonNull(multiplyByMatrix(ArrayToMatrix(elem1), MATRIX)),
                    transpose(ArrayToMatrix(elem2)));
            assert a != null;
            res[i] = a[0][0];
            elem1 = ShiftLeft(elem1);
            elem2 = ShiftLeft(elem2);

//            elem1 = ShiftLeft(elem1);
//            elem2 = ShiftLeft(elem2);
        }
        ArrayList<Integer> UnitsArrRes = ArrBinVecToUnitsArr(res);
        UnitsArrRes.sort(Collections.reverseOrder());
        return UnitsArrRes;
    }

    private static int[][] ArrayToMatrix(ArrayList<Integer> arr){
        int[][] matrix = new int[DIMENSION][DIMENSION];
        int[] temp = UnitsArrToArrBinVec(arr);
        System.arraycopy(temp, 0, matrix[0], 0, DIMENSION);
        return matrix;
    }

    private static ArrayList<Integer> ShiftLeft(ArrayList<Integer> arr){
        StringBuilder StringBuilderArr = new StringBuilder(UnitsArrToStrBin(arr));
        String firstElement = StringBuilderArr.substring(0,1);
        StringBuilderArr.deleteCharAt(0);
        StringBuilderArr.append(firstElement);
        return StrBinVecToUnitsArr(StringBuilderArr.toString());
    }

    private static ArrayList<Integer> ShiftRight(ArrayList<Integer> arr){
        StringBuilder StringBuilderArr = new StringBuilder(UnitsArrToStrBin(arr));
        String lastElement = StringBuilderArr.substring(StringBuilderArr.length()-1, StringBuilderArr.length());
        StringBuilderArr.deleteCharAt(StringBuilderArr.length()-1);
        StringBuilderArr.insert(0, lastElement);
        return StrBinVecToUnitsArr(StringBuilderArr.toString());
    }

    private static int[][] transpose(int[][] matrix){
        int[][] res = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                res[j][i] = matrix[i][j];
        return res;
    }

//    public static ArrayList<Integer> exponentiate(ArrayList<Integer> elem, int power){
//        if(power==0) return onbONE();
//
//        else if((power & 1) == 1) return multiply(elem, ShiftRight(exponentiate(elem,(power - 1)/2)));
//
//        else return ShiftRight(exponentiate(elem, power/2));
//    }

    public static ArrayList<Integer> onbONE(){
        ArrayList<Integer> res = new ArrayList<>(DIMENSION);
        for (int i = 0; i < DIMENSION; i++){
            res.add(i);
        }
        return res;
    }

    public static ArrayList<Integer> onbZERO(){

        return new ArrayList<>(DIMENSION);
    }

    public static ArrayList<Integer> exponentiate(ArrayList<Integer> elem, int power){ ////?????
        ArrayList<Integer> res = onbONE();
        while (power > 0){
            if((power & 1) == 1){
                res = multiply(res, elem);
            }
            elem = ShiftRight(elem);
            power >>= 1;
        }
        return res;
    }

    public static int Trace(ArrayList<Integer> elem){
        return elem.size()%2; // 0011 - > [2,3]
    }
}
