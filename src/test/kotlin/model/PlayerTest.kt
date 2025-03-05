package model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    fun `플레이어의 이름은 공백일 수 없다`() {
        assertThrows<IllegalArgumentException> { Player("", Cards(mutableListOf())) }
    }

    @Test
    fun `플레이어는 게임을 시작하면 2장의 카드를 갖는다`() {
        val king = Card(CardRank.KING, Shape.CLUB)
        val queen = Card(CardRank.QUEEN, Shape.CLUB)

        assertThat(Cards(mutableListOf(king,queen)).allCards.size).isEqualTo(2)
    }
}
