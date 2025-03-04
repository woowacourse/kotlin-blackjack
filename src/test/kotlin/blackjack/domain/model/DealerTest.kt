package blackjack.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun `setUp`() {
        dealer = Dealer("동전", cards = listOf(Card(Suit.HEART, Rank.ACE)))
    }

    @Test
    fun `딜러는 이름을 가진다`() {
        assertThat(dealer.name).isEqualTo("동전")
    }

    @Test
    fun `딜러는 카드를 가진다`() {
        assertThat(dealer.cards).isEqualTo(listOf(Card(Suit.HEART, Rank.ACE)))
    }
}
