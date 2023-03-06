package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Participants
import blackjack.domain.Player
import blackjack.domain.carddeck.CardDeck
import blackjack.domain.carddeck.cardnumbergenerator.RandomCardNumberGenerator
import blackjack.domain.carddeck.shapegenerator.RandomShapeGenerator
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller() {
    fun runGame() {
        val participants =
            Participants(InputView.getPlayerNames(), CardDeck(RandomShapeGenerator(), RandomCardNumberGenerator()))
        showInitialState(participants)
        players.forEach { askGetCard(it) }
        players.forEach { dealer.compareScore(it) }
        printResult(participants)
    }

    private fun printResult(participants: Participants) {
        showDealerState(participants.dealer)
        printTotalScore(participants)
        printWinOrLose(participants.players)
    }

    private fun showInitialState(participants: Participants) {
        OutputView.printDistributeScript(participants.players)
        OutputView.printDealerInitialCard(participants.dealer.cardBunch)
        participants.players.forEach { OutputView.printPlayerCards(it) }
    }

    private fun askGetCard(player: Player) {
        while (!player.cardBunch.isBurst()) {
            if (!addCardToPlayer(player)) return
        }
    }

    private fun addCardToPlayer(player: Player): Boolean {
        if (InputView.getDecision(player)) {
            player.cardBunch.addCard(cardDeck.drawCard())
            OutputView.printPlayerCards(player)
            return true
        }
        OutputView.printPlayerCards(player)
        return false
    }

    private fun showDealerState(dealer: Dealer) {
        val condition = dealer.isOverCondition()
        OutputView.printDealerOverCondition(!condition)
        if (!condition) dealer.cardBunch.addCard(cardDeck.drawCard())
    }

    private fun printTotalScore(participants: Participants) {
        OutputView.printTotalScore(participants)
    }

    private fun printWinOrLose(players: List<Player>) {
        OutputView.printWinOrLose(players)
    }
}
