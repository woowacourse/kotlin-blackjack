package controller

import domain.card.Deck
import domain.gamer.Dealer
import domain.gamer.Player
import domain.gamer.Players
import domain.result.GameResultBoard
import view.*

class BlackJackController {
    fun run() {
        val deck = Deck()
        val players = Players(inputNames().map { Player(it) })
        val dealer = Dealer()

        players.initStage(deck)
        repeat(2) { dealer.draw(deck.pop()) }

        printStatus(players, dealer)


        players.forEach { hitStage(it, deck) }
        while (dealer.isMustHit()) {
            dealer.draw(deck.pop())
            printDealerHit()
        }
        printResult(players.plus(dealer))

        printGameResultBoard(GameResultBoard.of(players, dealer))
    }

    private fun hitStage(player: Player, deck: Deck) {
        var answer = true
        while (!player.isBust() && answer) {
            val input = inputHit(player.name)
            answer = drawDecision(input, player, deck)
            printStatus(listOf(player))
        }
    }

    private fun drawDecision(input: String, player: Player, deck: Deck) = when (input) {
        "y" -> {
            player.draw(deck.pop())
            true
        }
        "n" -> false
        else -> throw IllegalArgumentException()
    }
}
