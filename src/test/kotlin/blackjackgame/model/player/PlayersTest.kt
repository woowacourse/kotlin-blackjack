package blackjackgame.model.player

import blackjackgame.model.card.Card
import blackjackgame.model.card.Deck
import blackjackgame.model.card.Denomination
import blackjackgame.model.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayersTest {

    @Test
    internal fun drawInitCards() {
        val initCards = mutableListOf(
            Card(Suit.CLOVER, Denomination.ACE), Card(Suit.DIAMOND, Denomination.FOUR),
            Card(Suit.CLOVER, Denomination.TWO), Card(Suit.DIAMOND, Denomination.FIVE),
        )
        val deck = Deck(initCards)
        var players = Players(listOf(Player("better"), Player("roki")))
        players.drawInitCards(deck)

        val playersGroup = players.players
        assertThat(playersGroup[0].cards).hasSize(2)
        assertThat(playersGroup[1].cards).hasSize(2)
        assertThat(deck.cards).hasSize(0)
    }
}