package blackjackgame.model

import blackjackgame.model.card.Card
import blackjackgame.model.card.Denomination
import blackjackgame.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    internal fun createPlayer() {
        val rocki = Player("rocki")

        assertThat(rocki.name).isEqualTo("rocki")
    }

    @Test
    internal fun drawCard() {
        val better = Player("better")
        val card = Card(Suit.HEART, Denomination.ACE)
        better.drawCard(card)

        assertThat(better.cards).hasSize(1)
        assertThat(better.cards[0]).isEqualTo(card)
    }

    @Test
    internal fun drawCards() {
        val better = Player("better")
        val card1 = Card(Suit.HEART, Denomination.ACE)
        val card2 = Card(Suit.CLOVER, Denomination.TWO)
        better.drawCard(listOf(card1,card2))

        assertThat(better.cards).hasSize(2)
        assertThat(better.cards).containsExactly(card1, card2)
    }
}