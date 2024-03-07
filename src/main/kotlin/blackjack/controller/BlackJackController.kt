package blackjack.controller

import Player
import blackjack.model.card.Deck
import blackjack.model.card.Hand
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry
import blackjack.view.setGame
import blackjack.view.showHands
import blackjack.view.showPlayerDrawMessage
import blackjack.view.showPlayerHand
import blackjack.view.showPlayersNameReadMessage

object BlackJackController {
    fun run() {
        val playersName = readPlayersName()

        setGame(playersName.joinToString(", "))
        val hands = List(playersName.size + 1) { Hand(mutableListOf()) }
        Deck.initialize()
        repeat(2) { hands.forEach { hand -> hand.draw(Deck.dealCard()) } }
        val dealer = Dealer(hands[0])
        val players =
            playersName.withIndex().map { (index, playerName) ->
                Player(playerName, hands[index + 1])
            }
        val playerEntry = PlayerEntry(players)
        showHands(dealer, playerEntry)

        playerEntry.players.forEach { player ->
            while (drawOrNot(askPlayerDraw(player))) {
                player.hand.draw(Deck.dealCard())
                showPlayerHand(player)
            }
        }
    }

    private fun drawOrNot(drawOrNot: String?): Boolean {
        if (drawOrNot == "y") return true
        return false
    }

    private fun readPlayersName(): List<String> {
        showPlayersNameReadMessage()
        return try {
            val playersName = readln().split(',').map { it.trim() }
            playersName
        } catch (e: IllegalArgumentException) {
            println("플레이어는 최소 1명 이상이어야 합니다.")
            readPlayersName()
        }
    }

    private fun askPlayerDraw(player: Player): String? {
        showPlayerDrawMessage(player)
        return try {
            val drawMessage = readlnOrNull()?.trim()?.lowercase()
            require(drawMessage == "y" || drawMessage == "n")
            drawMessage
        } catch (e: IllegalArgumentException) {
            println("플레이어의 이름을 입력해주세요.")
            askPlayerDraw(player)
        }
    }
}
