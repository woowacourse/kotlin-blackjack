package controller

import domain.BlackJackCardDeck
import domain.Card
import domain.CardDeck
import domain.Dealer
import domain.Participants
import domain.Player
import domain.Players
import domain.PlayersNameAndBet
import domain.phase.Phases
import domain.result.BetProfitResult

class BlackJackGame(private val phases: Phases, private val deck: CardDeck = BlackJackCardDeck(Card.DECK.shuffled())) {

    fun runGame(playersNameAndBet: PlayersNameAndBet): BetProfitResult {
        val players = playersNameAndBet.list.map { Player(it, deck.drawInitCards()) }
        val dealer = Dealer(deck.drawInitCards())
        val participants = Participants(Players(players), dealer)
        phases.run(participants, deck)
        return BetProfitResult(participants.players, participants.dealer)
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
