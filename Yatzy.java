import java.util.Map;
public class Yatzy {

    protected int[] dice;

    public Yatzy(int d1, int d2, int d3, int d4, int d5) {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = d5;
    }

    public int chance() {
        int sum = 0;
        for (int value : dice) {
            sum += value;
        }
        return sum;
    }

    public int yatzy() {
        if (dice[0] == dice[1] &&
            dice[1] == dice[2] &&
            dice[2] == dice[3] &&
            dice[3] == dice[4]) {
            return 50;
        }
        return 0;
    }

    private int simpleNumbers(int num) {
        int sum = 0;
        for (int die : dice) {
            if (die == num) {
                sum += num;
            }
        }
        return sum;
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

    private int[] getTallies() {
        // what does tallies mean?
        int[] tallies = new int[6];
        tallies[dice[0] - 1]++;
        tallies[dice[1] - 1]++;
        tallies[dice[2] - 1]++;
        tallies[dice[3] - 1]++;
        tallies[dice[4] - 1]++;
        return tallies;
    }

    private Map<Integer, Integer> getNumberCounts() {
        Map<Integer, Integer> numberCounts = new java.util.HashMap<>(Map.of(
                1, 0,
                2, 0,
                3, 0,
                4, 0,
                5, 0,
                6, 0
        ));
        for (int die : dice) {
            int newCount = numberCounts.get(die) + 1;
            numberCounts.replace(die, newCount);
        }
        return numberCounts;
    }

    private int nOfAKind(int n, Map<Integer, Integer> numberCounts) {
        for (int dieValue = 6; dieValue > 0; dieValue--) {
            if (numberCounts.get(dieValue) >= n) {
                return dieValue * n;
            }
        }
        return 0;
    }

    public int pair() {
        return nOfAKind(2, getNumberCounts());
    }

    public int twoPairs() {
        Map<Integer, Integer> numberCounts = getNumberCounts();
        int scoreHighestPair = nOfAKind(2, numberCounts);
        if (scoreHighestPair != 0) {
            // we remove the highestPair dice values so we don't get the same result for secondHighestPair
            numberCounts.replace(scoreHighestPair / 2, 0);
            int scoreSecondHighestPair = nOfAKind(2, numberCounts);
            if (scoreSecondHighestPair != 0)
                return scoreHighestPair + scoreSecondHighestPair;
        }
        return 0;
    }

    public int threeOfAKind() {
        return nOfAKind(3, getNumberCounts());
    }

    public int fourOfAKind() {
        return nOfAKind(4, getNumberCounts());
    }

    public int smallStraight() {
        Map<Integer, Integer> numberCounts = getNumberCounts();
        if (numberCounts.get(1) == 1 &&
                numberCounts.get(2) == 1 &&
                numberCounts.get(3) == 1 &&
                numberCounts.get(4) == 1 &&
                numberCounts.get(5) == 1)
            return 15;
        return 0;
    }

    public int largeStraight() {
        Map<Integer, Integer> numberCounts = getNumberCounts();
        if (numberCounts.get(2) == 1 &&
                numberCounts.get(3) == 1 &&
                numberCounts.get(4) == 1 &&
                numberCounts.get(5) == 1 &&
                numberCounts.get(6) == 1)
            return 20;
        return 0;
    }

    public int fullHouse() {
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;

        int[] tallies = getTallies();

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }
}
