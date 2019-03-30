package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsWithRepetition {
    private List<Object> source;
    private int variationLength;

    public PermutationsWithRepetition(List<Object> source, int variationLength) {
        this.source = source;
        this.variationLength = variationLength;
    }

    public Object[][] getVariations() {
        int srcLength = source.size();
        int permutations = (int) Math.pow(srcLength, variationLength);

        Object[][] table = new Object[permutations][variationLength];

        for (int i = 0; i < variationLength; i++) {
            int t2 = (int) Math.pow(srcLength, i);
            for (int p1 = 0; p1 < permutations; ) {
                for (int al = 0; al < srcLength; al++) {
                    for (int p2 = 0; p2 < t2; p2++) {
                        table[p1][i] = source.get(al);
                        p1++;
                    }
                }
            }
        }

        return table;
    }

    public static void main(String[] args) {
        PermutationsWithRepetition gen = new PermutationsWithRepetition(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0),
                3);

        Object[][] variations = gen.getVariations();

        for (Object[] s : variations) {
            System.out.println(Arrays.toString(s));
        }
    }
}
