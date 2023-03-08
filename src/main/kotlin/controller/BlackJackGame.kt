package controller

import domain.BlackJackCardDeck
import domain.BlackJackGameResult
import domain.CardDeck
import domain.Dealer
import domain.Participants
import domain.Player
import domain.Players
import domain.PlayersNameAndBet
import domain.phase.Phases

class BlackJackGame(private val phases: Phases, private val deck: CardDeck = BlackJackCardDeck()) {

    fun runGame(playersNameAndBet: PlayersNameAndBet): BlackJackGameResult {
        val players = playersNameAndBet.list.map { Player(it, deck.drawInitCards()) }
        val dealer = Dealer(deck.drawInitCards())
        val participants = Participants(Players(players), dealer)
        phases.run(participants, deck)
        return BlackJackGameResult(participants)
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
