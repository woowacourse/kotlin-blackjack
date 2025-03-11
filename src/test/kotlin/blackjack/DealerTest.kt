package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.Rank
import blackjack.domain.card.Suit
import blackjack.domain.participant.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    private fun setCard(vararg card: Card) {
        card.forEach { dealer.addCard(it) }
    }

    @BeforeEach
    fun clear() {
        dealer = Dealer()
    }

    @Test
    fun `카드의 점수가 17 이상이면 카드 추가 여부는 false이다`() {
        setCard(
            Card.of(Rank.ACE, Suit.SPADE),
            Card.of(Rank.NINE, Suit.SPADE),
            Card.of(Rank.NINE, Suit.HEART),
        )

        assertThat(dealer.canHit()).isFalse()
    }
}
