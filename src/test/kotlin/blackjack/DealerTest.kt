package blackjack

import blackjack.CardFixture.Companion.HEART_SEVEN
import blackjack.CardFixture.Companion.HEART_TEN
import blackjack.CardFixture.Companion.HEART_THREE
import blackjack.CardFixture.Companion.HEART_TWO
import blackjack.model.user.Dealer
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
        dealer.addCard(HEART_TWO)
        dealer.addCard(HEART_THREE)
        val expected = true

        val actual = dealer.isAvailDrawCard()

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `딜러는 카드 총 합이 17 이상이면 카드를 받지 않는다`() {
        dealer.addCard(HEART_SEVEN)
        dealer.addCard(HEART_TEN)
        val expected = false

        val actual = dealer.isAvailDrawCard()

        assertThat(actual).isEqualTo(expected)
    }
}
