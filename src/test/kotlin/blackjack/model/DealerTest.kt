package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class DealerTest {
    @Test
    fun `딜러는 이름과 카드들을 가진다`() {
        val cards = Cards(listOf((Card(CardShape.HEART, Denomination.FIVE)), Card(CardShape.CLOVER, Denomination.TWO)))
        val dealer = Dealer(cards = cards)

        assertAll({ assertThat(dealer.name).isEqualTo("딜러") }, { assertThat(dealer.cards).isEqualTo(cards) })
    }

    @Test
    fun `딜러는 이름이 없을 경우, 딜러라는 이름을 가진다`() {
        val dealer = Dealer()

        val expected = "딜러"

        assertThat(dealer.name).isEqualTo(expected)
    }

    @Test
    fun `딜러의 카드 리스트의 초기값은 비어있다`() {
        val dealer = Dealer()

        val expected = 0

        assertThat(dealer.cards.size).isEqualTo(expected)
    }

    @Test
    fun `딜러는 카드를 추가로 받을 수 있다`() {
        val dealer = Dealer()
        val card = Card(CardShape.CLOVER, Denomination.SIX)
        dealer.appendCard(card)

        val expected = 1

        assertThat(dealer.cards.value.size).isEqualTo(expected)
    }
}
