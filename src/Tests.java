import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Lib.ONB.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    @Test
    void DistributionForDism3(){
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(1,0));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(2,1));
        ArrayList<Integer> c = new ArrayList<>(List.of(1,0));
        getMatrix();
        assertEquals(
                multiply(add(a,b),c),
                add(multiply(b,c),multiply(a,c))); //(a+b)*c = a*c + b*c
    }

    @Test
    void ErrorTest(){
        ArrayList<Integer> a = new ArrayList<>(List.of(0));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(3,2));
        ArrayList<Integer> c = new ArrayList<>(Arrays.asList(1,0));
        getMatrix();
        ArrayList<Integer> muladd = multiply(add(a,b),c);
        System.out.println(muladd);
        ArrayList<Integer> addmulmul = add(multiply(b,c),multiply(a,c));
        System.out.println(addmulmul);
        assertEquals(muladd, addmulmul);
    }

    @Test
    void DistributionForDism239(){
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(200,36));
        ArrayList<Integer> b = new ArrayList<>(Arrays.asList(184,56));
        ArrayList<Integer> c = new ArrayList<>(Arrays.asList(196, 14, 13));
        getMatrix();
        assertEquals(
                multiply(add(a,b),c),
                add(multiply(b,c),multiply(a,c))); //(a+b)*c = a*c + b*c
    }

    @Test
    void ExponentiationForDism3_Power2(){
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(2,1));
        getMatrix();
        assertEquals(exponentiate(a,2), multiply(a,a));
    }

    @Test
    void ExponentiationForDism3_Power3(){
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(2,1));
        getMatrix();
        assertEquals(exponentiate(a,3), multiply(multiply(a,a),a));
    }

    @Test
    void ExponentiationForDism3_Power4(){
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(2,1));
        getMatrix();
        assertEquals(exponentiate(a,4), multiply(multiply(a,a),multiply(a,a)));
    }

    @Test
    void ExponentiationForDism239_Power2(){
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(137,42,41,18,10,11));
        getMatrix();
        assertEquals(exponentiate(a,2), multiply(a,a));
    }

    @Test
    void ExponentiationForDism239_Power3(){
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(137,42,41,18,10,11));
        getMatrix();
        assertEquals(exponentiate(a,3), multiply(multiply(a,a),a));
    }

    @Test
    void ExponentiationForDism239_Power4(){
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(137,42,41,18,10,11));
        getMatrix();
        assertEquals(exponentiate(a,4), multiply(multiply(a,a),multiply(a,a)));
    }
//    @Test
//    void UnitsArrToStrBinTestForDism3(){
//        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(2,1));
//        String expected = "011";
//        assertEquals(UnitsArrToStrBin(a), expected);
//    }
//
//    @Test
//    void StrBinVecToUnitsArrTestForDism3(){
//        String a = "101";
//        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0,2));
//        assertEquals(StrBinVecToUnitsArr(a), expected);
//    }
//
//    @Test
//    void ArrBinVecToUnitsArrTestForDism3(){
//        int[] a = {1,1,0};
//        ArrayList<Integer> expected = new ArrayList<>(Arrays.asList(0,1));
//        assertEquals(ArrBinVecToUnitsArr(a), expected);
//    }
//
//    @Test
//    void UnitsArrToArrBinVecForDism3(){
//        ArrayList<Integer> a = new ArrayList<>(List.of(2));
//        int[] expected = {0,0,1};
//        assertEquals(Arrays.toString(UnitsArrToArrBinVec(a)), Arrays.toString(expected));
//    }
}
