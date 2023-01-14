import java.util.ArrayList;
import java.util.Arrays;

import static Lib.ONB.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(2,1));
        getMatrix();
        System.out.println(multiply(a,onbONE()));
    }
}
