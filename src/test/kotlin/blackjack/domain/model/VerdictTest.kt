package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class VerdictTest {
    private lateinit var verdict: Verdict

    @BeforeEach
    fun `setUp`() {
        verdict = Verdict(Dealer(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.FIVE))) // 11점
    }

    @Test
    fun `플레이어 점수가 더 낮을 경우 패배한다`() {
        val player = Player("A", Card(Suit.HEART, Rank.TWO)) // 2점
        val actual = VerdictResult.LOSE
        assertThat(verdict.determine(player)).isEqualTo(actual)
    }

    @Test
    fun `플레이어 점수가 더 높을 경우 승리한다`() {
        val player = Player("A", Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.SIX)) // 17점
        val actual = VerdictResult.WIN
        assertThat(verdict.determine(player)).isEqualTo(actual)
    }

    @Test
    fun `플레이어 점수가 Bust일 경우 패배한다`() {
        val player =
            Player("A", Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.SIX), Card(Suit.HEART, Rank.NINE)) // 25점
        val actual = VerdictResult.LOSE
        assertThat(verdict.determine(player)).isEqualTo(actual)
    }

    @Test
    fun `딜러의 점수가 Bust일 경우 승리한다`() {
        verdict =
            Verdict(Dealer(Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.FIVE), Card(Suit.HEART, Rank.JACK))) // 25점
        val player = Player("A", Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.SIX)) // 17점
        val actual = VerdictResult.WIN
        assertThat(verdict.determine(player)).isEqualTo(actual)
    }

    @Test
    fun `딜러와 플레이어 점수가 Bust일 경우 패배한다`() {
        verdict =
            Verdict(Dealer(Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.FIVE), Card(Suit.HEART, Rank.JACK))) // 25점
        val player =
            Player("A", Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.SIX), Card(Suit.HEART, Rank.NINE)) // 25점
        val actual = VerdictResult.LOSE
        assertThat(verdict.determine(player)).isEqualTo(actual)
    }

    @Test
    fun `딜러와 플레이어 점수가 Bust가 아니고 같은 경우 비긴다`() {
        verdict =
            Verdict(Dealer(Card(Suit.HEART, Rank.KING), Card(Suit.HEART, Rank.FIVE))) // 15
        val player =
            Player("A", Card(Suit.HEART, Rank.NINE), Card(Suit.HEART, Rank.SIX)) // 15
        val actual = VerdictResult.DRAW
        assertThat(verdict.determine(player)).isEqualTo(actual)
    }
}
