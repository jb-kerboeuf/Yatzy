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

    public int pair() {
        Map<Integer, Integer> numberCounts = getNumberCounts();
        for (int dieValue = 6; dieValue > 0; dieValue--) {
            if (numberCounts.get(dieValue) >= 2) {
                return dieValue * 2;
            }
        }
        return 0;
    }

    public int twoPairs() {
        Map<Integer, Integer> numberCounts = getNumberCounts();
        int pairs = 0;
        int score = 0;
        for (int dieValue = 6; dieValue > 0; dieValue--) {
            if (numberCounts.get(dieValue) >= 2) {
                pairs ++;
                score += dieValue * 2;
            }
        }
        if (pairs == 2)
            return score;
        else
            return 0;
    }

    public int threeOfAKind() {
        Map<Integer, Integer> numberCounts = getNumberCounts();
        for (int dieValue = 6; dieValue > 0; dieValue--) {
            if (numberCounts.get(dieValue) >= 3) {
                return dieValue * 3;
            }
        }
        return 0;
    }

    public int fourOfAKind() {
        Map<Integer, Integer> numberCounts = getNumberCounts();
        for (int dieValue = 6; dieValue > 0; dieValue--) {
            if (numberCounts.get(dieValue) >= 4) {
                return dieValue * 4;
            }
        }
        return 0;
    }

    public int smallStraight() {
        int[] tallies = getTallies();
        if (tallies[0] == 1 &&
                tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1)
            return 15;
        return 0;
    }

    public int largeStraight() {
        int[] tallies = getTallies();
        if (tallies[1] == 1 &&
                tallies[2] == 1 &&
                tallies[3] == 1 &&
                tallies[4] == 1
                && tallies[5] == 1)
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
