package controller

import domain.Deck
import domain.Player
import domain.Players
import view.inputHit
import view.inputNames
import view.printResult
import view.printStatus

class BlackJackController {
    fun run() {
        val names = inputNames()
        val players = Players(names.map { Player(it) })
        val deck = Deck()
        players.initStage(deck)
        printStatus(players)
        players.forEach { hitStage(it, deck) }
        printResult(players)
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

fun main() {
    val con = BlackJackController()
    con.run()
}