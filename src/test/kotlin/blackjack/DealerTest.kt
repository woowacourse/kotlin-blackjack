package blackjack

import blackjack.model.Card
import blackjack.model.Dealer
import blackjack.model.Number
import blackjack.model.Shape
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
        dealer.addCards(listOf(Card(Shape.SPADE, Number.TWO), Card(Shape.SPADE, Number.THREE)))
        val expect = true

        val actual = dealer.isMoreCard()

        assertThat(actual).isEqualTo(expect)
    }

    @Test
    fun `딜러는 카드 총 합이 17 이상이면 카드를 받지 않는다`() {
        val dealer = Dealer()
        dealer.addCards(listOf(Card(Shape.SPADE, Number.SEVEN), Card(Shape.SPADE, Number.TEN)))
        val expect = false

        val actual = dealer.isMoreCard()

        assertThat(actual).isEqualTo(expect)
    }
}
