import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Lib.ONB.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(List.of(9,10,11));
        getMatrix();
        System.out.println(Trace(a));
    }
}
