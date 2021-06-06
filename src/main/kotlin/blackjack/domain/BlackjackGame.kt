package blackjack.domain

import blackjack.domain.card.Deck
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Gamer
import blackjack.domain.gamer.Player
import blackjack.domain.gamer.Players

class BlackjackGame(val deck: Deck, val dealer: Dealer, val players: Players) {
    fun initStage() {
        repeat(REPEAT_NUMBER) {
            players.players.plus(dealer).forEach {
                it.initializeCards(
                    mutableListOf(deck.pop(), deck.pop())
                )
            }
        }
    }

    fun gamersMap(): Pair<Dealer, List<Player>> {
        return dealer to players.players
    }

    fun gamers(): List<Gamer> {
        return players.players.plus(dealer)
    }

    fun runnable(): Boolean {
        return players.isRemainToHit()
    }

    fun currentPlayer(): Player {
        return players.currentPlayer()
    }

    fun proceedHit() {
        currentPlayer().draw(deck.pop())
    }

    fun next() {
        currentPlayer().stay()
    }

    fun proceedDealerStageReturnCount(): Int {
        var count = 0
        while (dealer.isMustHit()) {
            dealer.draw(deck.pop())
            count++
        }
        return count
    }

    companion object {
        const val REPEAT_NUMBER = 2
    }
}