package blackjack.controller

import Player
import blackjack.model.card.Deck
import blackjack.model.card.Hand
import blackjack.model.game.Referee
import blackjack.model.game.Result
import blackjack.model.game.State
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry
import blackjack.view.setGame
import blackjack.view.showDealerDrawMessage
import blackjack.view.showFinalWinOrLoss
import blackjack.view.showHands
import blackjack.view.showHandsScore
import blackjack.view.showPlayerDrawMessage
import blackjack.view.showPlayerHand
import blackjack.view.showPlayersNameReadMessage

object BlackJackController {
    fun run() {
        val playersName = readPlayersName()

        setGame(playersName.joinToString(", "))
        val hands = List(playersName.size + 1) { Hand(mutableListOf()) }
        repeat(2) { hands.forEach { hand -> hand.draw(Deck.dealCard()) } }

        val dealer = Dealer(hands[0])

        val players =
            playersName.withIndex().map { (index, playerName) ->
                Player(playerName, hands[index + 1])
            }

        val playerEntry = PlayerEntry(players)
        showHands(dealer, playerEntry)

        playerEntry.players.forEach { player ->
            while (player.state == State.RUNNING) {
                drawOrNot(askPlayerDraw(player), player)
                showPlayerHand(player)
            }
        }

        while (dealer.judgeDraw()) {
            dealer.hand.draw(Deck.dealCard())
            showDealerDrawMessage(dealer)
        }

        showHandsScore(dealer, playerEntry)
        val referee = Referee(dealer, playerEntry)
        val results = referee.makeResults()
        val winCount = results.count { it == Result.DEALER_WIN }
        val defeatCount = results.count { it == Result.PLAYER_WIN }
        val drawCount = results.count { it == Result.DRAW }

        println()
        showFinalWinOrLoss()
        println("딜러: ${winCount}승${" ${drawCount}무 ".takeIf { drawCount != 0 } ?: ""}${defeatCount}패")
        results.withIndex().forEach { (index, result) ->
            val winOrLose =
                when (result) {
                    Result.PLAYER_WIN -> "승"
                    Result.DEALER_WIN -> "패"
                    else -> "무"
                }
            println("${playerEntry.players[index].name}: $winOrLose")
        }
    }

    private fun drawOrNot(
        drawOrNot: String?,
        player: Player,
    ) {
        if (drawOrNot == "y") {
            determineState(player)
        } else {
            player.state = State.STAY
        }
    }

    private fun determineState(player: Player) {
        player.hand.draw(Deck.dealCard())
        if (isBust(player)) player.state = State.BUST
        if (isBlackjack(player)) player.state = State.BLACKJACK
    }

    private fun isBust(player: Player): Boolean {
        return player.hand.totalScore > 21
    }

    private fun isBlackjack(player: Player): Boolean {
        return player.hand.totalScore == 21
    }

    private fun readPlayersName(): List<String> {
        showPlayersNameReadMessage()
        return try {
            val playersName = readln().split(',').map { it.trim() }
            require(playersName.all { it.isNotBlank() }) { "[ERROR] 공백이 아닌 플레이어의 이름을 입력해주세요." }
            playersName
        } catch (e: IllegalArgumentException) {
            println(e.message)
            readPlayersName()
        }
    }

    private fun askPlayerDraw(player: Player): String? {
        showPlayerDrawMessage(player)
        return try {
            val drawMessage = readlnOrNull()?.trim()?.lowercase()
            require(drawMessage == "y" || drawMessage == "n") { "[ERROR] 카드를 더 받을지 말지는 y 또는 n으로 입력해주세요." }
            drawMessage
        } catch (e: IllegalArgumentException) {
            println(e.message)
            askPlayerDraw(player)
        }
    }
}
