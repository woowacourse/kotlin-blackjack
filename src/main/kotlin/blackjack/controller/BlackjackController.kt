package blackjack.controller

import blackjack.domain.model.ActionType
import blackjack.domain.model.BetAmount
import blackjack.domain.model.card.Card
import blackjack.domain.model.card.CardFactory
import blackjack.domain.model.card.PlayingCard
import blackjack.domain.model.participant.Dealer
import blackjack.domain.model.participant.Player
import blackjack.domain.model.participant.PlayerGroup
import blackjack.domain.service.Blackjack
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val playerGroup = getPlayerGroup()
        val playerBetAmount = getPlayersBetAmount(playerGroup.players)
        val blackjack = makeGame(playerGroup)
        initGame(blackjack)
        startGame(blackjack)
        endGame(blackjack, playerBetAmount)
    }

    private fun getPlayerGroup(): PlayerGroup {
        return retryInput {
            val players: List<Player> = inputView.askForPlayersName().map(::Player)
            val dealer: Dealer = Dealer()
            PlayerGroup(listOf(dealer) + players)
        }
    }

    private fun getPlayersBetAmount(players: List<Player>): Map<Player, BetAmount> {
        return players.associateWith { player ->
            getBetAmount(player)
        }
    }

    private fun getBetAmount(player: Player): BetAmount {
        return retryInput {
            BetAmount(inputView.askForPlayerBetAmount(player))
        }
    }

    private fun makeGame(playerGroup: PlayerGroup): Blackjack {
        val deck: ArrayDeque<Card> = CardFactory().makeCard()
        return Blackjack(PlayingCard(deck), playerGroup)
    }

    private fun initGame(blackjack: Blackjack) {
        blackjack.initGame()
        outputView.printInitCardStatus(blackjack.playerGroup)
    }

    private fun startGame(blackjack: Blackjack) {
        blackjack.playerGroup.players.forEach { player ->
            hitOrStay(blackjack, player)
        }
        dealerReceiveCard(blackjack)
    }

    private fun hitOrStay(
        blackjack: Blackjack,
        player: Player,
    ) {
        while (blackjack.canHit(player)) {
            val playerAction = getActionType(player)
            if (shouldStopDrawing(playerAction)) break
            blackjack.hitAction(player)
            outputView.printCardStatus(player)
        }
        if (blackjack.getParticipantCardSize(player) == 2) outputView.printCardStatus(player)
    }

    private fun shouldStopDrawing(playerAction: ActionType): Boolean {
        return when (playerAction) {
            ActionType.Hit -> false
            ActionType.Stay -> true
        }
    }

    private fun getActionType(player: Player): ActionType {
        return retryInput {
            get(inputView.askForHitOrStay(player))
        }
    }

    private fun get(yesOrNo: String): ActionType {
        if (yesOrNo == YES) return ActionType.Hit
        return ActionType.Stay
    }

    private fun dealerReceiveCard(blackjack: Blackjack) {
        val count: Int = blackjack.drawUntilThreshold()
        outputView.printDealerReceiveCard(count, blackjack.playerGroup.dealer)
    }

    private fun endGame(
        blackjack: Blackjack,
        playerBetAmount: Map<Player, BetAmount>,
    ) {
        outputView.participantsCardResult(blackjack.playerGroup)
        val gameResult = blackjack.endGame(playerBetAmount)
        val dealerResult = -gameResult.values.sumOf { it.amount }
        outputView.dealerResult(blackjack.playerGroup.dealer, dealerResult)
        outputView.playerResult(gameResult)
    }

    private fun <T> retryInput(inputFunction: () -> T): T {
        return runCatching { inputFunction() }
            .getOrElse { e ->
                println(e.message)
                retryInput(inputFunction)
            }
    }

    companion object {
        private const val YES: String = "y"
        private const val NO: String = "n"
    }
}
