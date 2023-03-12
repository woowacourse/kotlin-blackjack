package domain

import domain.card.BlackJackCardDeck
import domain.card.Card
import domain.card.CardDeck
import domain.phase.Phases
import domain.result.BetProfitResult

class BlackJackGame(private val phases: Phases) {

    fun runGame(
        playersInfo: List<PlayerInfo>,
        deck: CardDeck = BlackJackCardDeck(Card.DECK.shuffled())
    ): BetProfitResult {
        val participants = Participants(Players.create(playersInfo), Dealer())
        phases.run(participants, deck)
        return BetProfitResult(participants.players, participants.dealer)
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
