package blackjack.controller

import Player
import blackjack.model.card.Deck
import blackjack.model.card.Hand
import blackjack.model.card.Hand.Companion.BLACKJACK_SCORE
import blackjack.model.game.Referee
import blackjack.model.game.Result
import blackjack.model.game.State
import blackjack.model.player.Dealer
import blackjack.model.player.PlayerEntry
import blackjack.view.showDealerDrawMessage
import blackjack.view.showFinalWinOrLossResult
import blackjack.view.showHands
import blackjack.view.showHandsScore
import blackjack.view.showPlayerDrawDecision
import blackjack.view.showPlayerEntry
import blackjack.view.showPlayerHand
import blackjack.view.showPlayersNameReadMessage

private const val INVALID_PLAYER_NAME = "[ERROR] 공백이 아닌 플레이어의 이름을 입력해주세요."
private const val INVALID_DRAW_DECISION = "[ERROR] 카드를 더 받을지 말지는 y 또는 n으로 입력해주세요."
private const val DRAW_DECISION = "y"
private const val STAY_DECISION = "n"

object BlackJackController {
    fun run() {
        val playersName = readPlayersName()
        val (dealer, playerEntry) = showInitialSettingCard(playersName)
        playGame(playerEntry, dealer)
        showGameResult(dealer, playerEntry)
    }

    private fun showGameResult(
        dealer: Dealer,
        playerEntry: PlayerEntry,
    ) {
        showHandsScore(dealer, playerEntry)
        val results = judgeOfWinOrLose(dealer, playerEntry)
        showFinalWinOrLossResult(results, playerEntry)
    }

    private fun judgeOfWinOrLose(
        dealer: Dealer,
        playerEntry: PlayerEntry,
    ): List<Result> {
        val referee = Referee(dealer, playerEntry)
        val results = referee.makeResults()
        return results
    }

    private fun playGame(
        playerEntry: PlayerEntry,
        dealer: Dealer,
    ) {
        askPlayersDraw(playerEntry)
        showDealerDraw(dealer)
    }

    private fun showDealerDraw(dealer: Dealer) {
        while (dealer.judgeDraw()) {
            dealer.hand.draw(Deck.dealCard())
            determineDealerstate(dealer)
            showDealerDrawMessage(dealer)
        }
    }

    private fun askPlayersDraw(playerEntry: PlayerEntry) {
        playerEntry.players.forEach { player -> askPlayerDraw(player) }
    }

    private fun askPlayerDraw(player: Player) {
        while (player.state is State.Running.Hit) {
            val drawOrNot = askPlayersDraw(player)
            if (drawOrNot == DRAW_DECISION) {
                player.hand.draw(Deck.dealCard())
                drawOrNot(drawOrNot, player)
                showPlayerHand(player)
                determinestate(player)
            } else {
                player.state = State.Finished.Stay
            }
        }
    }

    private fun showInitialSettingCard(playersName: List<String>): Pair<Dealer, PlayerEntry> {
        showPlayerEntry(playersName.joinToString(", "))
        val hands = dealingCards(playersName)
        val dealer = setDealer(hands)
        val playerEntry = setPlayerEntry(playersName, hands)
        showHands(dealer, playerEntry)
        return Pair(dealer, playerEntry)
    }

    private fun setPlayerEntry(
        playersName: List<String>,
        hands: List<Hand>,
    ): PlayerEntry {
        val players =
            setPlayers(playersName, hands)
        val playerEntry = PlayerEntry(players)
        return playerEntry
    }

    private fun setPlayers(
        playersName: List<String>,
        hands: List<Hand>,
    ) = playersName.withIndex().map { (index, playerName) ->
        Player(playerName, hands[index + 1])
    }

    private fun setDealer(hands: List<Hand>): Dealer {
        val dealer = Dealer(hands[0])
        return dealer
    }

    private fun dealingCards(playersName: List<String>): List<Hand> {
        val hands = List(playersName.size + 1) { Hand(mutableListOf()) }
        repeat(2) { hands.forEach { hand -> hand.draw(Deck.dealCard()) } }
        return hands
    }

    private fun drawOrNot(
        drawOrNot: String?,
        player: Player,
    ) {
        if (drawOrNot == DRAW_DECISION) {
            determinestate(player)
        } else {
            player.state = State.Finished.Stay
        }
    }

    private fun determinestate(player: Player) {
        if (isBust(player)) {
            player.state = State.Finished.Bust
        } else if (isBlackjack(player)) {
            player.state = State.Finished.BlackJack
        }
    }

    private fun determineDealerstate(dealer: Dealer) {
        if (isDealerBust(dealer)) {
            dealer.state = State.Finished.Bust
        } else if (isDealerBlackjack(dealer)) {
            dealer.state = State.Finished.BlackJack
        }
    }

    private fun isBust(player: Player): Boolean {
        return player.hand.totalScore > BLACKJACK_SCORE
    }

    private fun isDealerBust(dealer: Dealer): Boolean {
        return dealer.hand.totalScore > BLACKJACK_SCORE
    }

    private fun isBlackjack(player: Player): Boolean {
        return player.hand.totalScore == BLACKJACK_SCORE
    }

    private fun isDealerBlackjack(dealer: Dealer): Boolean {
        return dealer.hand.totalScore == BLACKJACK_SCORE
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

    private fun askPlayersDraw(player: Player): String? {
        showPlayerDrawDecision(player)
        return try {
            val drawDecision = readlnOrNull()?.trim()?.lowercase()
            require(drawDecision == DRAW_DECISION || drawDecision == STAY_DECISION) { INVALID_DRAW_DECISION }
            drawDecision
        } catch (e: IllegalArgumentException) {
            println(e.message)
            askPlayersDraw(player)
        }
    }
}
