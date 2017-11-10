import java.util.ArrayList;
import java.util.List;

class BoardsOfDifferentLengthsUsingKPieces {

    private List<Integer> get(final int k, final int shorter, final int longer) {
        final List<Integer> result = new ArrayList<>();

        for (int i = 0; i <= k; i++) {
            final int shorterPiecesLength = i * shorter;
            final int longerPiecesLength = (k - i) * longer;
            result.add(shorterPiecesLength + longerPiecesLength);
        }

        return result;
    }

    public static void main(String[] args) {
        final BoardsOfDifferentLengthsUsingKPieces b = new BoardsOfDifferentLengthsUsingKPieces();
        System.out.println(b.get(2, 3, 7));
        System.out.println(b.get(10, 3, 7));
    }
}
