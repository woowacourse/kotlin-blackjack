package blackjack.controller

import blackjack.model.domain.ActionType
import blackjack.model.domain.GameResult
import blackjack.model.domain.card.Card
import blackjack.model.domain.card.CardFactory
import blackjack.model.domain.card.PlayingCard
import blackjack.model.domain.participant.Dealer
import blackjack.model.domain.participant.Player
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
        initGame(playerGroup.players)
        startGame(playerGroup.players)
        val playerResult = blackjack.endGame(playerGroup)
        printResult(playerResult)
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

    private fun printResult(playersResult: Map<Player, GameResult>) {
        outputView.participantsCardResult(listOf(dealer) + playersResult.keys)
        outputView.dealerResult(dealer, getDealerResult(playersResult))
        outputView.playerResult(playersResult)
    }

    private fun getDealerResult(playersResult: Map<Player, GameResult>): Map<GameResult, Int> {
        return playersResult.values.groupingBy { it }.eachCount()
    }

    private fun <T> retryInput(inputFunction: () -> T): T {
        return runCatching { inputFunction() }
            .getOrElse { e ->
                println(e.message)
                retryInput(inputFunction)
            }
    }
}
