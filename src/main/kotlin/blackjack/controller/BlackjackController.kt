package blackjack.controller

import blackjack.model.domain.ActionType
import blackjack.model.domain.BettingMoney
import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Card
import blackjack.model.domain.card.CardFactory
import blackjack.model.domain.card.PlayingCard
import blackjack.model.domain.participant.Dealer
import blackjack.model.domain.participant.Player
import blackjack.model.domain.participant.PlayerBetAmount
import blackjack.model.domain.participant.PlayerGroup
import blackjack.model.service.Blackjack
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val deck: ArrayDeque<Card> = CardFactory().makeCard()
    private val blackjack: Blackjack = Blackjack(PlayingCard(deck))
    private val dealer: Dealer = Dealer()

    fun run() {
        val playerGroup = getPlayerGroup()
        val playersBetAmount = getPlayerBetAmount(playerGroup.players)
        initGame(playerGroup.players)
        startGame(playerGroup.players)
        val playerResult = blackjack.endGame(playerGroup)
        val playersSettleMoney = settleMoney(playerResult, playersBetAmount)

        printResult(playersSettleMoney)
    }

    private fun getPlayerBetAmount(players: List<Player>): List<PlayerBetAmount> {
        return retryInput {
            players.map { PlayerBetAmount(it, BettingMoney(inputView.askForBetAmount(it))) }
        }
    }

    private fun initGame(players: List<Player>) {
        blackjack.initGame(players + dealer)
        outputView.printInitCardStatus(dealer, players)
    }

    private fun startGame(players: List<Player>) {
        players.forEach { player ->
            hitOrStay(player)
        }
        dealerReceiveCard()
    }

    private fun hitOrStay(player: Player) {
        while (!player.canHit()) {
            val playerAction = getActionType(player)
            if (shouldStopDrawing(playerAction)) break
            blackjack.hitAction(player)
            outputView.printCardStatus(player)
            player.hand.isBust()
        }
        if (player.cardDeck.size == 2) outputView.printCardStatus(player)
    }

    private fun shouldStopDrawing(playerAction: ActionType): Boolean {
        return when (playerAction) {
            ActionType.Hit -> false
            ActionType.Stay -> true
        }
    }

    private fun getActionType(player: Player): ActionType {
        return retryInput {
            ActionType.get(inputView.askForHitOrStay(player))
        }
    }

    private fun getPlayerGroup(): PlayerGroup {
        return retryInput {
            val players: List<Player> = inputView.askForPlayersName().map(::Player)
            PlayerGroup(players, dealer)
        }
    }

    private fun dealerReceiveCard() {
        val count: Int = blackjack.drawUntilThreshold(dealer)
        dealer.hand.isBust()
        outputView.printDealerReceiveCard(count, dealer)
    }

    private fun settlePlayerBet(
        playerBetAmount: PlayerBetAmount,
        gameResult: GameResult,
    ): PlayerBetAmount {
        val settledBetAmount =
            when (gameResult) {
                GameResult.BlackjackWin -> BettingMoney(playerBetAmount.betAmount.blackjackMoney)
                GameResult.Win -> BettingMoney(playerBetAmount.betAmount.winMoney)
                GameResult.Lose -> BettingMoney(playerBetAmount.betAmount.loseMoney)
                GameResult.Draw -> BettingMoney(playerBetAmount.betAmount.drawMoney)
            }
        return PlayerBetAmount(playerBetAmount.player, settledBetAmount)
    }

    private fun settleMoney(
        playersResult: Map<Player, GameResult>,
        playersBetAmount: List<PlayerBetAmount>,
    ): List<PlayerBetAmount> {
        return playersBetAmount.map { playerBetAmount ->
            val result = playersResult[playerBetAmount.player] ?: return@map playerBetAmount
            settlePlayerBet(playerBetAmount, result)
        }
    }

    private fun printResult(playerBetAmount: List<PlayerBetAmount>) {
        outputView.participantsCardResult(listOf(dealer) + playerBetAmount.map { it.player })
        outputView.dealerResult(dealer, getLosePlayers(playerBetAmount))
        outputView.playerResult(playerBetAmount)
    }

    private fun getLosePlayers(playerBetAmount: List<PlayerBetAmount>): List<PlayerBetAmount> {
        return playerBetAmount.filter { it.betAmount.amount < 0 }
    }

    private fun <T> retryInput(inputFunction: () -> T): T {
        return runCatching { inputFunction() }
            .getOrElse { e ->
                println(e.message)
                retryInput(inputFunction)
            }
    }
}
