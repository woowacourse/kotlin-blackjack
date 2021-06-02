package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Gamer
import blackjack.domain.gamer.Player
import blackjack.domain.result.GameResultBoard
import blackjack.view.*

class BlackJackController {
    fun run() {
        val deck = Deck()
        val players = inputNames().map { Player(it) }
        val dealer = Dealer()

        initStage(players.plus(dealer), deck)

        printStatus(players, dealer)

        players.forEach { hitStage(it, deck) }
        while (dealer.isMustHit()) {
            dealer.draw(deck.pop())
            printDealerHit()
        }
        printResult(players.plus(dealer))

        printGameResultBoard(GameResultBoard.of(players, dealer))
    }

    private fun initStage(players: List<Gamer>, deck: Deck) {
        repeat(2) { players.forEach { it.draw(deck.pop()) } }
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
