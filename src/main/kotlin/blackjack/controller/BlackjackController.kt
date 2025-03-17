package blackjack.controller

import blackjack.model.domain.ActionType
import blackjack.model.domain.BettingMoney
import blackjack.model.domain.GameResult
import blackjack.model.domain.card.CardFactory
import blackjack.model.domain.card.PlayingCard
import blackjack.model.domain.participant.Dealer
import blackjack.model.domain.participant.Player
import blackjack.model.domain.participant.PlayerBetAmount
import blackjack.model.domain.participant.PlayerBetResult
import blackjack.model.domain.participant.PlayerGroup
import blackjack.model.service.Blackjack
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val playerGroup: PlayerGroup = getPlayerGroup(Dealer())
        val playersBetAmount = getPlayerBetAmount(playerGroup)
        val blackjack = Blackjack(PlayingCard(CardFactory().makeCard()), playerGroup)
        initGame(blackjack, playerGroup)
        startGame(blackjack, playerGroup)
        val playerResult = blackjack.endGame()
        val playersSettleMoney = settleMoney(playerResult, playersBetAmount)

        printResult(playersSettleMoney, playerGroup.dealer)
    }

    private fun getPlayerBetAmount(playerGroup: PlayerGroup): List<PlayerBetAmount> {
        return retryInput {
            playerGroup.players.map { PlayerBetAmount(it, BettingMoney(inputView.askForBetAmount(it))) }
        }
    }

    private fun initGame(
        blackjack: Blackjack,
        playerGroup: PlayerGroup,
    ) {
        blackjack.initGame()
        outputView.printInitCardStatus(playerGroup.dealer, playerGroup.players)
    }

    private fun startGame(
        blackjack: Blackjack,
        playerGroup: PlayerGroup,
    ) {
        playerGroup.players.forEach { player ->
            hitOrStay(blackjack, player)
        }
        dealerReceiveCard(blackjack, playerGroup.dealer)
    }

    private fun hitOrStay(
        blackjack: Blackjack,
        player: Player,
    ) {
        while (!player.canHit()) {
            val playerAction = inputView.askForHitOrStay(player)
            if (shouldStopDrawing(playerAction)) break
            blackjack.hitAction(player)
            outputView.printCardStatus(player)
        }
        if (player.cardDeck.size == 2) outputView.printCardStatus(player)
    }

    private fun shouldStopDrawing(playerAction: ActionType): Boolean {
        return when (playerAction) {
            ActionType.Hit -> false
            ActionType.Stay -> true
        }
    }

    private fun getPlayerGroup(dealer: Dealer): PlayerGroup {
        return retryInput {
            val players: List<Player> = inputView.askForPlayersName().map(::Player)

            PlayerGroup(players, dealer)
        }
    }

    private fun dealerReceiveCard(
        blackjack: Blackjack,
        dealer: Dealer,
    ) {
        val count: Int = blackjack.drawUntilThresholdWithCount(dealer)
        outputView.printDealerReceiveCard(count, dealer)
    }

    private fun settleMoney(
        playersResult: Map<Player, GameResult>,
        playersBetAmount: List<PlayerBetAmount>,
    ): List<PlayerBetResult> {
        return playersBetAmount.map { playerBetAmount ->
            val result = playersResult[playerBetAmount.player] ?: return emptyList()
            playerBetAmount.profitResult(result.rate)
        }
    }

    private fun printResult(
        playerBetResult: List<PlayerBetResult>,
        dealer: Dealer,
    ) {
        outputView.participantsCardResult(listOf(dealer) + playerBetResult.map { it.player })
        outputView.dealerResult(dealer, getDealerResult(playerBetResult))
        outputView.playerResult(playerBetResult)
    }

    private fun getDealerResult(playerBetResult: List<PlayerBetResult>): Float {
        val dealerProfit = -playerBetResult.filter { it.bettingResult < 0 }.map { it.bettingResult }.sum()
        val dealerLoss = playerBetResult.filter { it.bettingResult > 0 }.map { it.bettingResult }.sum()
        return dealerProfit - dealerLoss
    }

    private fun <T> retryInput(inputFunction: () -> T): T {
        return runCatching { inputFunction() }
            .getOrElse { e ->
                println(e.message)
                retryInput(inputFunction)
            }
    }
}
