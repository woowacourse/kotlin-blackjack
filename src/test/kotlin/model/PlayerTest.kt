package model

import model.CardsTest.Companion.cardOf
import model.card.Card
import model.card.CardRank
import model.card.Cards
import model.card.Shape
import model.participant.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @Test
    fun `플레이어의 이름은 공백일 수 없다`() {
        assertThrows<IllegalArgumentException> { Player("", Cards(mutableListOf()), 10000f) }
    }

    @Test
    fun `플레이어는 게임을 시작하면 2장의 카드를 갖는다`() {
        assertThat(cards.totalCount).isEqualTo(2)
    }

    @Test
    fun `플레이어는 카드를 받을지 결정할 수 있다`() {
        val player = Player("joy", cards, 10000f)
        Assertions.assertTrue(player.canHit())
    }

    @Test
    fun `플레이어는 카드를 뽑을 수 있다`() {
        val player = Player("joy", cards, 10000f)
        val drawnCard = Card(CardRank.TWO, Shape.SPADE)
        Assertions.assertFalse(player.turn(drawnCard))
    }

    @ParameterizedTest
    @ValueSource(ints = [0, -1000, -2000])
    fun `플레이어의 베팅 금액은 0원 이하일 수 없다`(amount: Float) {
        assertThrows<IllegalArgumentException> { Player("joy", cards, amount) }
    }

    companion object {
        private val cards =
            cardOf(
                Card(CardRank.SIX, Shape.CLUB),
                Card(CardRank.NINE, Shape.SPADE),
            )
    }
}
