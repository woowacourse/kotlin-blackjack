package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DealerTest {
    private lateinit var drawableDealer: Dealer
    private lateinit var unDrawableDealer: Dealer

    @BeforeEach
    fun setUp() {
        drawableDealer = Dealer()
        drawableDealer.apply {
            draw(Card(Denomination.FOUR, Suit.SPADE))
            draw(Card(Denomination.FOUR, Suit.DIAMOND))
            draw(Card(Denomination.FOUR, Suit.CLOVER))
            draw(Card(Denomination.FOUR, Suit.HEART))
        }

        unDrawableDealer = Dealer()
        unDrawableDealer.apply {
            draw(Card(Denomination.QUEEN, Suit.SPADE))
            draw(Card(Denomination.SEVEN, Suit.SPADE))
        }
    }

    @Test
    fun `딜러는 첫번째 카드를 공개할 수 있다`() {
        val dealer = Dealer()
        val card = Card(Denomination.NINE, Suit.DIAMOND)

        dealer.draw(card)
        val firstDealerCard = dealer.openFirstCard()
        assertThat(firstDealerCard.getCardSuit()).isEqualTo(card.getCardSuit())
        assertThat(firstDealerCard.getCardDenomination()).isEqualTo(card.getCardDenomination())
    }

    @Test
    fun `딜러는 첫번째 카드를 공개할 수 없으면, 에러를 던진다`() {
        val dealer = Dealer()
        assertThrows<IllegalArgumentException> { dealer.openFirstCard() }
    }

    @Test
    fun `딜러는 손패의 합이 16을 초과하지 않으면 카드를 드로우 해야 한다`() {
        assertThat(drawableDealer.checkDealerScoreCondition()).isTrue()
    }

    @Test
    fun `딜러는 손패의 합이 16을 초과할 때 카드를 더 이상 드로우할 수 없다`() {
        assertThat(unDrawableDealer.checkDealerScoreCondition()).isFalse()
    }
}
