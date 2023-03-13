package domain

import domain.card.BlackJackCardDeck
import domain.card.Card
import domain.card.CardDeck
import domain.phase.BlackJackPhases
import domain.result.BetProfitResult

class BlackJackGame(private val phases: BlackJackPhases) {

    fun runGame(
        playersInfo: List<PlayerInfo>,
        deck: CardDeck = BlackJackCardDeck(Card.DECK.shuffled())
    ): BetProfitResult {
        val participants = Participants(Players.create(playersInfo), Dealer())
        return phases.run(participants, deck)
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
