package domain

import domain.card.BlackJackCardDeck
import domain.card.Card
import domain.card.CardDeck
import domain.phase.Phases
import domain.result.BetProfitResult

class BlackJackGame(private val phases: Phases, private val deck: CardDeck = BlackJackCardDeck(Card.DECK.shuffled())) {

    fun runGame(playersNameAndBet: PlayersNameAndBet): BetProfitResult {
        val players = playersNameAndBet.list.map { Player(it) }
        val dealer = Dealer()
        val participants = Participants(Players(players), dealer)
        participants.all.forEach { participant ->
            repeat(2) { participant.addCard(deck.draw()) }
        }
        phases.run(participants, deck)
        return BetProfitResult(participants.players, participants.dealer)
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
