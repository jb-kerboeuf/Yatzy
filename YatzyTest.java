import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class YatzyTest {

    @Test
    public void chance_scores_sum_of_all_dice() {
        assertEquals(15, new Yatzy(2, 3, 4, 5, 1).chance());
        assertEquals(16, new Yatzy(3, 3, 4, 5, 1).chance());
    }

    @Test
    public void yatzy_scores_50() {
        assertEquals(50, new Yatzy(4, 4, 4, 4, 4).yatzy());
        assertEquals(50, new Yatzy(6, 6, 6, 6, 6).yatzy());
        assertEquals(0, new Yatzy(6, 6, 6, 6, 3).yatzy());
    }

    @Test
    public void ones_scores_sum_of_ones() {
        assertEquals(1, new Yatzy(1, 2, 3, 4, 5).ones());
        assertEquals(2, new Yatzy(1, 2, 1, 4, 5).ones());
        assertEquals(0, new Yatzy(6, 2, 2, 4, 5).ones());
        assertEquals(4, new Yatzy(1, 2, 1, 1, 1).ones());
    }

    @Test
    public void twos_scores_sum_of_twos() {
        assertEquals(4, new Yatzy(1, 2, 3, 2, 6).twos());
        assertEquals(10, new Yatzy(2, 2, 2, 2, 2).twos());
    }

    @Test
    public void threes_scores_sum_of_threes() {
        assertEquals(6, new Yatzy(1, 2, 3, 2, 3).threes());
        assertEquals(12, new Yatzy(2, 3, 3, 3, 3).threes());
    }

    @Test
    public void fours_scores_sum_of_fours() {
        assertEquals(12, new Yatzy(4, 4, 4, 5, 5).fours());
        assertEquals(8, new Yatzy(4, 4, 5, 5, 5).fours());
        assertEquals(4, new Yatzy(4, 5, 5, 5, 5).fours());
    }

    @Test
    public void fives_scores_sum_of_fives() {
        assertEquals(10, new Yatzy(4, 4, 4, 5, 5).fives());
        assertEquals(15, new Yatzy(4, 4, 5, 5, 5).fives());
        assertEquals(20, new Yatzy(4, 5, 5, 5, 5).fives());
    }

    @Test
    public void sixes_scores_sum_of_sixes() {
        assertEquals(0, new Yatzy(4, 4, 4, 5, 5).sixes());
        assertEquals(6, new Yatzy(4, 4, 6, 5, 5).sixes());
        assertEquals(18, new Yatzy(6, 5, 6, 6, 5).sixes());
    }

    @Test
    public void pair_scores_sum_of_two_highest_matching_dice() {
        assertEquals(6, new Yatzy(3, 4, 3, 5, 6).pair());
        assertEquals(10, new Yatzy(5, 3, 3, 3, 5).pair());
        assertEquals(12, new Yatzy(5, 3, 6, 6, 5).pair());
    }

    @Test
    public void twoPairs_scores_sum_of_two_pairs() {
        assertEquals(16, new Yatzy(3, 3, 5, 4, 5).twoPairs());
        assertEquals(16, new Yatzy(3, 3, 5, 5, 5).twoPairs());
    }

    @Test
    public void threeOfAKind_scores_sum_of_three_matching_dice() {
        assertEquals(9, new Yatzy(3, 3, 3, 4, 5).threeOfAKind());
        assertEquals(15, new Yatzy(5, 3, 5, 4, 5).threeOfAKind());
        assertEquals(9, new Yatzy(3, 3, 3, 3, 5).threeOfAKind());
    }

    @Test
    public void fourOfAKind_scores_sum_of_four_matching_dice() {
        assertEquals(12, new Yatzy(3, 3, 3, 3, 5).fourOfAKind());
        assertEquals(20, new Yatzy(5, 5, 5, 4, 5).fourOfAKind());
        assertEquals(12, new Yatzy(3, 3, 3, 3, 3).fourOfAKind());
    }

    @Test
    public void smallStraight_scores_15_if_dice_values_from_1_to_5() {
        assertEquals(15, new Yatzy(1, 2, 3, 4, 5).smallStraight());
        assertEquals(15, new Yatzy(2, 3, 4, 5, 1).smallStraight());
        assertEquals(0, new Yatzy(1, 2, 2, 4, 5).smallStraight());
    }

    @Test
    public void largeStraight_scores_20_if_dice_values_from_2_to_6() {
        assertEquals(20, new Yatzy(6, 2, 3, 4, 5).largeStraight());
        assertEquals(20, new Yatzy(2, 3, 4, 5, 6).largeStraight());
        assertEquals(0, new Yatzy(1, 2, 2, 4, 5).largeStraight());
    }

    @Test
    public void fullHouse_scores_sum_of_pair_and_three_of_a_kind() {
        assertEquals(18, new Yatzy(6, 2, 2, 2, 6).fullHouse());
        assertEquals(22, new Yatzy(6, 6, 2, 2, 6).fullHouse());
        assertEquals(0, new Yatzy(2, 3, 4, 5, 6).fullHouse());
    }
}
