package controller

import domain.*
import view.*

class BlackJackController {
    fun run() {
        val deck = Deck()
        val names = inputNames()
        val players = Players(names.map { Player(it) })
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
