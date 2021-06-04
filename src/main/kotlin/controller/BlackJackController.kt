package controller

import domain.*
import domain.card.Deck
import domain.participant.*
import view.*

class BlackJackController {
    fun run() {
        val names = inputNames()
        val game = BlackJackGame(Players(names.map { Player(it) }))
        bettingStage(game.players)
        game.initStage()
        printInitMessage(game.participants)
        game.participants.forEach { hitStage(it, game.deck) }
        printStatusWithScore(game.participants)
        printResult(game.getProfitResult())
    }

    private fun bettingStage(players: Players) {
        players.forEach { it.addMoney(inputMoney(it.name)) }
    }

    private fun hitStage(player: Participant, deck: Deck) {
        while (player.isHitStatus) {
            distributeCard(player, deck)
        }
    }

    private fun distributeCard(player: Participant, deck: Deck) {
        when (player is Dealer) {
            true -> {
                player.draw(deck.pop(), null)
            }
            false -> {
                val input = inputHit(player.name)
                player.draw(deck.pop(), getResponse(input))
                printStatus(listOf(player))
            }
        }
    }
}

fun main() {
    val con = BlackJackController()
    con.run()
}
