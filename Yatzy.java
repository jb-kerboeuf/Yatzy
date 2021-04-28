import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Yatzy {

    protected List<Integer> diceValues;
    protected Map<Integer, Integer> diceFrequency ;

    public Yatzy(int d1, int d2, int d3, int d4, int d5) {
        diceValues = Arrays.asList(d1, d2, d3, d4, d5);
        diceFrequency = new java.util.HashMap<>(Map.of(
                1, 0,
                2, 0,
                3, 0,
                4, 0,
                5, 0,
                6, 0
        ));
        updateDiceFrequency();
    }

    private void updateDiceFrequency() {
        for (int die : diceValues) {
            int newCount = diceFrequency.get(die) + 1;
            diceFrequency.replace(die, newCount);
        }
    }

    public int chance() {
        return diceValues.stream().reduce(0,
                Integer::sum);
    }

    public int yatzy() {
        if (diceValues.stream().allMatch(
                diceValues.get(0)::equals))
            return 50;
        return 0;
    }

    private int simpleNumbers(int num) {
        return diceValues.stream().reduce(0,
                (sum, die) -> die == num ? sum + die : sum);
    }

    public int ones() {
        return simpleNumbers(1);
    }

    public int twos() {
        return simpleNumbers(2);
    }

    public int threes() {
        return simpleNumbers(3);
    }

    public int fours() {
        return simpleNumbers(4);
    }

    public int fives() {
        return simpleNumbers(5);
    }

    public int sixes() {
        return simpleNumbers(6);
    }

    private static int nOfAKind(int n, Map<Integer, Integer> _diceFrequency) {
        for (int dieValue = 6; dieValue > 0; dieValue--) {
            if (_diceFrequency.get(dieValue) >= n) {
                return dieValue;
            }
        }
        return 0;
    }

    public int pair() {
        return nOfAKind(2, diceFrequency) * 2;
    }

    public int twoPairs() {
        int highestPair = nOfAKind(2, diceFrequency);
        if (highestPair != 0) {
            Map<Integer, Integer> newFrequency = new java.util.HashMap<>(diceFrequency);
            // we remove the highestPair dice values so we don't get the same result for secondHighestPair
            newFrequency.replace(highestPair, 0);
            int secondHighestPair = nOfAKind(2, newFrequency);
            if (secondHighestPair != 0)
                return highestPair * 2 + secondHighestPair * 2;
        }
        return 0;
    }

    public int threeOfAKind() {
        return nOfAKind(3, diceFrequency) * 3;
    }

    public int fourOfAKind() {
        return nOfAKind(4, diceFrequency) * 4;
    }

    public int smallStraight() {
        if (diceFrequency.get(1) == 1 &&
                diceFrequency.get(2) == 1 &&
                diceFrequency.get(3) == 1 &&
                diceFrequency.get(4) == 1 &&
                diceFrequency.get(5) == 1)
            return 15;
        return 0;
    }

    public int largeStraight() {
        if (diceFrequency.get(2) == 1 &&
                diceFrequency.get(3) == 1 &&
                diceFrequency.get(4) == 1 &&
                diceFrequency.get(5) == 1 &&
                diceFrequency.get(6) == 1)
            return 20;
        return 0;
    }

    public int fullHouse() {
        int threeOfAKind = nOfAKind(3, diceFrequency);
        if (threeOfAKind != 0) {
            Map<Integer, Integer> newFrequency = new java.util.HashMap<>(diceFrequency);
            // we remove the threeOfAKind dice values so we don't get the same result for the pair
            newFrequency.replace(threeOfAKind, 0);
            int pair = nOfAKind(2, newFrequency);
            if (pair != 0)
                return threeOfAKind * 3 + pair * 2;
        }
        return 0;
    }
}
