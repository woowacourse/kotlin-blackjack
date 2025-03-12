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
        val results = listOf(VerdictResult.WIN, VerdictResult.WIN, VerdictResult.LOSE, VerdictResult.DRAW, VerdictResult.LOSE)
        dealer.recordVerdict(results)
        val verdicts: Map<VerdictResult, Int> = dealer.getRecord()
        val actual: Map<VerdictResult, Int> =
            mapOf(
                VerdictResult.WIN to 2,
                VerdictResult.LOSE to 2,
                VerdictResult.DRAW to 1,
            )
        assertThat(verdicts).isEqualTo(actual)
    }
}
