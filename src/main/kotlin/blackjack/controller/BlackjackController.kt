package blackjack.controller

import blackjack.model.domain.ActionType
import blackjack.model.domain.Dealer
import blackjack.model.domain.Deck
import blackjack.model.domain.Player
import blackjack.model.domain.Status
import blackjack.model.domain.YesOrNo
import blackjack.model.service.Blackjack
import blackjack.model.strategy.TrueShuffle
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    private val blackjack: Blackjack = Blackjack(Deck(TrueShuffle()))
    private val dealer: Dealer = Dealer()

    fun run() {
        val players: List<Player> = inputView.askForPlayersName().map(::Player)
        initGame(players)
        startGame(players)
        blackjack.endGame(players, dealer)
        printResult(players)
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
        while (player.status == Status.None) {
            val playerAction = getYesOrNo(player)
            if (blackjack.shouldStopDrawing(ActionType.get(playerAction), player)) break
            outputView.printCardStatus(player)
        }
        if (player.cardDeck.size == 2) outputView.printCardStatus(player)
    }

    private fun getYesOrNo(player: Player): YesOrNo {
        return retryInput {
            YesOrNo(inputView.askForHitOrStay(player))
        }
    }

    private fun dealerReceiveCard() {
        val count: Int = blackjack.drawUntilThreshold(dealer)
        dealer.isBust()
        outputView.printDealerReceiveCard(count, dealer)
    }

    private fun printResult(players: List<Player>) {
        outputView.participantsCardResult(listOf(dealer) + players)
        outputView.dealerResult(dealer, getDealerResult(players))
        outputView.playerResult(players)
    }

    private fun getDealerResult(players: List<Player>): Map<Status, Int> {
        return players.groupingBy { it.status }.eachCount()
    }

    private fun <T> retryInput(inputFunction: () -> T): T {
        return runCatching { inputFunction() }
            .getOrElse { e ->
                println(e.message)
                retryInput(inputFunction)
            }
    }
}
