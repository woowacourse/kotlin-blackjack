package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `플레이어는 이름과 카드 리스트를 가진다`() {
        val cards = listOf(Card(CardShape.HEART, "5"), Card(CardShape.CLOVER, "2"))
        val player = Player("모찌", cards)
        assertAll({
            assertThat(player.name).isEqualTo("모찌")
            assertThat(player.cards).isEqualTo(cards)
        })
    }
}