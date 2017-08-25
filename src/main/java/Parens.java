import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parens {

    public static void main(String[] args) {
        System.out.println(new Parens().get(4));
    }

    private List<String> get(int n) {
        if (n == 1) return Collections.singletonList("()");

        List<String> tempList = get(n - 1);
        List<String> retList = new ArrayList<>();

        for (String s : tempList) {
            retList.add("()" + s);
            retList.add("(" + s + ")");
            String temp = s + "()";
            if (!retList.contains(temp)) {
                retList.add(temp);
            }
        }

        return retList;
    }
}
