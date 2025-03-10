package blackjack

import blackjack.model.Dealer
import blackjack.model.card.Card
import blackjack.model.card.Number
import blackjack.model.card.Shape
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        dealer = Dealer()
    }

    @Test
    fun `딜러는 카드 총 합이 17 미만이면 카드를 받는다`() {
        dealer.addCard(Card(Shape.SPADE, Number.TWO))
        dealer.addCard(Card(Shape.SPADE, Number.THREE))
        val expected = true

        val actual = dealer.isAvailDrawCard()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 카드 총 합이 17 이상이면 카드를 받지 않는다`() {
        dealer = Dealer()
        dealer.addCard(Card(Shape.SPADE, Number.SEVEN))
        dealer.addCard(Card(Shape.SPADE, Number.TEN))
        val expected = false

        val actual = dealer.isAvailDrawCard()

        assertThat(actual).isEqualTo(expected)
    }
}
