package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름과 카드 리스트를 가진다`() {
        val cards = listOf((Card(CardShape.HEART, Denomination.FIVE)), Card(CardShape.CLOVER, Denomination.TWO))
        val player = Player("모찌", Cards(cards))
        assertAll({
            assertThat(player.name).isEqualTo("모찌")
            assertThat(player.cards.cards).isEqualTo(cards)
        })
    }

    @Test
    fun `플레이어는 카드를 추가로 받을 수 있다`() {
        val initialCards =
            Cards(
                listOf((Card(CardShape.HEART, Denomination.FIVE)), Card(CardShape.CLOVER, Denomination.TWO)),
            )
        val player = Player("모찌", initialCards)
        val card = Card(CardShape.CLOVER, Denomination.SIX)
        player.appendCard(card)

        assertThat(player.cards.cards).isEqualTo(
            listOf(
                Card(CardShape.HEART, Denomination.FIVE),
                Card(CardShape.CLOVER, Denomination.TWO),
                Card(CardShape.CLOVER, Denomination.SIX),
            ),
        )
    }

    @Test
    fun `플레이어가 이름만 가질 경우, 가진 카드 리스트는 비어있다`() {
        val player = Player("모찌")
        assertThat(player.cards.cards.size).isEqualTo(0)
    }
}
