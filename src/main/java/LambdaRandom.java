import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class LambdaRandom {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 9, 6, 2, 0, 9, 1, 3, 5, 7);
        for (int i = 0; i < 20; i++) {
            System.out.println(Arrays.toString(getRandomSubset(list).toArray()));
        }
    }

    private static List<Integer> getRandomSubset(List<Integer> list) {
        return list.stream().filter(integer -> new Random().nextBoolean())
                .collect(Collectors.toList());
    }
}
