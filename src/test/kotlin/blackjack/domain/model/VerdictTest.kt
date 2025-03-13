package blackjack.domain.model

import org.junit.jupiter.api.BeforeEach

class VerdictTest {
    private lateinit var verdict: Verdict

    @BeforeEach
    fun `setUp`() {
        verdict = Verdict(Dealer(Card(Suit.HEART, Rank.ACE), Card(Suit.HEART, Rank.FIVE))) // 11점
    }
}
