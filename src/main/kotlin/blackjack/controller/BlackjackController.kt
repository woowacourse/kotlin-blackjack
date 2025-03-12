package blackjack.controller

import blackjack.domain.model.ActionType
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
        val blackjack = makeGame(playerGroup)
        initGame(blackjack, playerGroup)
        startGame(blackjack, playerGroup)
        endGame(blackjack, playerGroup)
    }

    private fun getPlayerGroup(): PlayerGroup {
        return retryInput {
            val players: List<Player> = inputView.askForPlayersName().map(::Player)
            val dealer: Dealer = Dealer()
            PlayerGroup(listOf(dealer) + players)
        }
    }

    private fun makeGame(playerGroup: PlayerGroup): Blackjack {
        val deck: ArrayDeque<Card> = CardFactory().makeCard()
        return Blackjack(PlayingCard(deck), playerGroup)
    }

    private fun initGame(
        blackjack: Blackjack,
        playerGroup: PlayerGroup,
    ) {
        blackjack.initGame()
        outputView.printInitCardStatus(playerGroup)
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
        while (player.canHit()) {
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

    private fun dealerReceiveCard(
        blackjack: Blackjack,
        dealer: Dealer,
    ) {
        val count: Int = blackjack.drawUntilThreshold()
        outputView.printDealerReceiveCard(count, dealer)
    }

    private fun endGame(
        blackjack: Blackjack,
        playerGroup: PlayerGroup,
    ) {
        outputView.participantsCardResult(playerGroup)
        val gameResult = blackjack.endGame()
        outputView.dealerResult(playerGroup.dealer, gameResult)
        outputView.playerResult(gameResult)
    }

    private fun <T> retryInput(inputFunction: () -> T): T {
        return runCatching { inputFunction() }
            .getOrElse { e ->
                println(e.message)
                retryInput(inputFunction)
            }
    }
}
