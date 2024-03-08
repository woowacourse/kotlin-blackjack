package blackjack.controller

import Player
import blackjack.model.card.Deck
import blackjack.model.card.Hand
import blackjack.model.game.Referee
import blackjack.model.game.ScoreCalculation.BLACKJACK_SCORE
import blackjack.model.game.State
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry
import blackjack.view.setGame
import blackjack.view.showDealerDrawMessage
import blackjack.view.showFinalWinOrLossResult
import blackjack.view.showHands
import blackjack.view.showHandsScore
import blackjack.view.showPlayerDrawDecision
import blackjack.view.showPlayerHand
import blackjack.view.showPlayersNameReadMessage

private const val INVALID_PLAYER_NAME = "[ERROR] 공백이 아닌 플레이어의 이름을 입력해주세요."
private const val INVALID_DRAW_DECISION = "[ERROR] 카드를 더 받을지 말지는 y 또는 n으로 입력해주세요."
private const val DRAW_DECISION = "y"
private const val STAY_DECISION = "n"

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
        showFinalWinOrLossResult(results, playerEntry)
    }

    private fun drawOrNot(
        drawOrNot: String?,
        player: Player,
    ) {
        if (drawOrNot == DRAW_DECISION) {
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
        return player.hand.totalScore > BLACKJACK_SCORE
    }

    private fun isBlackjack(player: Player): Boolean {
        return player.hand.totalScore == BLACKJACK_SCORE
    }

    private fun readPlayersName(): List<String> {
        showPlayersNameReadMessage()
        return try {
            val playersName = readln().split(',').map { it.trim() }
            require(playersName.all { it.isNotBlank() }) { INVALID_PLAYER_NAME }
            playersName
        } catch (e: IllegalArgumentException) {
            println(e.message)
            readPlayersName()
        }
    }

    private fun askPlayerDraw(player: Player): String? {
        showPlayerDrawDecision(player)
        return try {
            val drawDecision = readlnOrNull()?.trim()?.lowercase()
            require(drawDecision == DRAW_DECISION || drawDecision == STAY_DECISION) { INVALID_DRAW_DECISION }
            drawDecision
        } catch (e: IllegalArgumentException) {
            println(e.message)
            askPlayerDraw(player)
        }
    }
}
