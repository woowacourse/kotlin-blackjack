package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer(Card(Suit.HEART, Rank.ACE))
    }

    @Test
    fun `딜러의 승패 횟수를 반환한다`() {
        val results = listOf(MatchResult.WIN, MatchResult.WIN, MatchResult.LOSE, MatchResult.DRAW, MatchResult.LOSE)
        dealer.recordVerdict(results)
        val verdicts: Map<MatchResult, Int> = dealer.getRecord()
        val actual: Map<MatchResult, Int> =
            mapOf(
                MatchResult.WIN to 2,
                MatchResult.LOSE to 2,
                MatchResult.DRAW to 1,
            )
        assertThat(verdicts).isEqualTo(actual)
    }
}
