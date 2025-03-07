package model

import model.CardsTest.Companion.cardOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    fun `플레이어의 이름은 공백일 수 없다`() {
        assertThrows<IllegalArgumentException> { Player("", Cards(mutableListOf())) }
    }

    @Test
    fun `플레이어는 게임을 시작하면 2장의 카드를 갖는다`() {
        val cards = cardOf(
            Card(CardRank.KING, Shape.CLUB),
            Card(CardRank.QUEEN, Shape.SPADE),
        )

        assertThat(cards.getCardsCount()).isEqualTo(2)
    }

    @Test
    fun `플레이어는 카드를 받을지 결정할 수 있다`() {
        val cards = cardOf(
            Card(CardRank.SIX, Shape.CLUB),
            Card(CardRank.NINE, Shape.SPADE),
        )

        val player = Player("joy", cards)
        Assertions.assertTrue(player.isHit())
    }

    @Test
    fun `플레이어는 카드를 뽑을 수 있다`() {
        val cards = cardOf(
            Card(CardRank.FIVE, Shape.CLUB),
            Card(CardRank.SIX, Shape.SPADE),
        )

        val player = Player("joy", cards)
        Assertions.assertFalse(player.turn(cards))
    }
}
