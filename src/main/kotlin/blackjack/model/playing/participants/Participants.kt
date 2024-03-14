package blackjack.model.playing.participants

import blackjack.model.card.CardDeck
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.playing.participants.player.Players

data class Participants(val dealer: Dealer, val players: Players) {
    fun addInitialCards(cardDeck: CardDeck) {
        dealer.addInitialCards(cardDeck)
        players.players.forEach {
            it.addInitialCards(cardDeck)
        }
    }

    fun getPlayerResult(): Map<PlayerName, Int> = players.players.associate { it.name to it.cardHand.sum() }
}
