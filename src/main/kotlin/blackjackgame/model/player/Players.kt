package blackjackgame.model.player

import blackjackgame.model.card.Deck

class Players(val players: List<Participant>) : List<Participant> by players {

    fun drawInitCards(deck: Deck) {
        players.forEach {
            it.drawCard(deck.handOutInitialCards())
        }
    }
}
