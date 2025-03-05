package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름과 카드 리스트를 가진다`() {
        val cards = listOf((Card(CardShape.HEART, "5")), Card(CardShape.CLOVER, "2"))
        val player = Player("모찌", cards)
        assertAll({
            assertThat(player.name).isEqualTo("모찌")
            assertThat(player.cards).isEqualTo(cards)
        })
    }

    @Test
    fun `플레이어는 카드를 추가로 받을 수 있다`() {
        val initialCards = listOf(Card(CardShape.HEART, "5"), Card(CardShape.CLOVER, "2"))
        val player = Player("모찌", initialCards)
        val card = Card(CardShape.CLOVER, "6")
        player.appendCard(card)
        assertThat(player.cards).isEqualTo(
            listOf(
                Card(CardShape.HEART, "5"),
                Card(CardShape.CLOVER, "2"),
                Card(CardShape.CLOVER, "6"),
            ),
        )
    }
}
