import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Lib.ONB.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(List.of(0));
        getMatrix();

        System.out.println(multiply(a,onbONE()));
    }
}
