package controller

import domain.*
import view.*

class BlackJackController {
    fun run() {
        val names = inputNames()
        val players = Participants(names.map { Player(it) })
        val participants = Participants(listOf(Dealer()).plus(players))
        val deck = Deck()
        participants.initStage(deck)
        printInitMessage(participants)
        participants.forEach { hitStage(it, deck) }
        printResult(participants)
    }

    private fun hitStage(player: Participant, deck: Deck) {
        var answer = true
        while (player.isHitStatus() && answer) {
            answer = distributeCard(player, deck)
        }
    }

    private fun distributeCard(player: Participant, deck: Deck): Boolean {
        return when (player is Dealer) {
            true -> {
                player.draw(deck.pop())
                true
            }
            false -> {
                val input = inputHit(player.name)
                val hit = drawDecision(input, player as Player, deck)
                printStatus(listOf(player))
                hit
            }
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