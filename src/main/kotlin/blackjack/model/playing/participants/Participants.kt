package blackjack.model.playing.participants

import blackjack.model.card.generator.CardGenerator
import blackjack.model.playing.participants.player.PlayerName
import blackjack.model.playing.participants.player.Players

data class Participants(val dealer: Dealer, val players: Players) {
    fun addInitialCards(cardGenerator: CardGenerator) {
        dealer.addInitialCards(cardGenerator)
        players.players.forEach {
            it.addInitialCards(cardGenerator)
        }
    }

    fun getPlayerResult(): Map<PlayerName, Int> = players.players.associate { it.name to it.cardHand.sum() }
}
