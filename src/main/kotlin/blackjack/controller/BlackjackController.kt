package blackjack.controller

import blackjack.domain.model.ActionType
import blackjack.domain.model.BetAmount
import blackjack.domain.model.BetStatus
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

    private fun getPlayersBetAmount(players: List<Player>): List<BetStatus> {
        return players.map { BetStatus(it, getBetAmount(it)) }
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
            playPlayerTurn(blackjack, player)
        }
        playDealerTurn(blackjack)
    }

    private fun playPlayerTurn(
        blackjack: Blackjack,
        player: Player,
    ) {
        while (blackjack.canHit(player) && wantsToHit(player)) {
            blackjack.hitAction(player)
            outputView.printCardStatus(player)
        }
        if (blackjack.getParticipantCardSize(player) == 2) outputView.printCardStatus(player)
    }

    private fun wantsToHit(player: Player): Boolean {
        val playerAction =
            retryInput {
                inputView.askForHitOrStay(player)
            }
        return when (playerAction) {
            ActionType.Hit -> true
            ActionType.Stay -> false
        }
    }

    private fun playDealerTurn(blackjack: Blackjack) {
        val count: Int = blackjack.drawUntilDealerStands()
        outputView.printDealerReceiveCard(count, blackjack.playerGroup.dealer)
    }

    private fun endGame(
        blackjack: Blackjack,
        playerBetAmount: List<BetStatus>,
    ) {
        outputView.participantsCardResult(blackjack.playerGroup)
        val gameResult = blackjack.endGame(playerBetAmount)
        outputView.printGameResult(gameResult)
    }

    private fun <T> retryInput(inputFunction: () -> T): T {
        return runCatching { inputFunction() }
            .getOrElse { e ->
                println(e.message)
                retryInput(inputFunction)
            }
    }
}
