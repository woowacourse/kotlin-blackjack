package domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `게임 시작 시 딜러의 처음 카드를 하나 보여준다`() {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card.of(CardCategory.SPADE, CardNumber.NINE),
                ),
            ),
        )
        val actual = dealer.showInitCards()
        val expected = listOf(Card.of(CardCategory.CLOVER, CardNumber.EIGHT))
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `카드의 총합이 16이하면 카드를 더 받아야 한다`() {
        val dealer = Dealer(
            Cards(
                listOf(
                    Card.of(CardCategory.CLOVER, CardNumber.EIGHT),
                    Card.of(CardCategory.SPADE, CardNumber.SIX),
                ),
            ),
        )
        val actual = dealer.isPossibleDrawCard()
        val expected = true
        assertThat(actual).isEqualTo(expected)
    }
}
