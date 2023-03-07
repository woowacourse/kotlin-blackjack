package controller

import domain.BlackJackCardDeck
import domain.BlackJackGameResult
import domain.CardDeck
import domain.Dealer
import domain.Names
import domain.Participants
import domain.Player
import domain.Players
import domain.phase.Phases

class BlackJackGame(private val phase: Phases, private val deck: CardDeck = BlackJackCardDeck()) {

    fun runGame(names: Names): BlackJackGameResult {
        val players = names.values.map { Player(it, deck.drawInitCards()) }
        val dealer = Dealer(deck.drawInitCards())
        val participants = Participants(Players(players), dealer)
        phase.run(participants, deck)
        return BlackJackGameResult(participants)
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
    }
}
