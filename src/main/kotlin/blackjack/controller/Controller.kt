package blackjack.controller

import blackjack.domain.CardBunch
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Referee
import blackjack.domain.carddeck.CardDeck
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller(private val cardDeck: CardDeck) {
    fun runGame() {
        val dealer = makeDealer()
        val players = makePlayers(InputView.getPlayerNames())
        val referee = Referee()
        showInitialCard(dealer, players)
        players.forEach { player -> askGetCard(player) }
        players.forEach { player -> referee.chooseWinner(dealer, player) }
        printCardAndScore(dealer, players)
        printFinalWinOrLose(referee)
    }

    private fun printCardAndScore(dealer: Dealer, players: List<Player>) {
        showDealerState(dealer)
        OutputView.printCardAndScore(dealer, players)
    }

    private fun makePlayers(names: List<String>): List<Player> =
        names.map { Player(it, makeInitialCardBunch()) }

    private fun makeDealer(): Dealer = Dealer(makeInitialCardBunch())

    private fun makeInitialCardBunch(): CardBunch = CardBunch(cardDeck.drawCard(), cardDeck.drawCard())

    private fun showInitialCard(dealer: Dealer, players: List<Player>) {
        OutputView.printDistributeScript(players)
        OutputView.printDealerInitialCard(dealer.cardBunch)
        players.forEach { OutputView.printPlayerInitialCard(it) }
    }

    private fun askGetCard(player: Player) {
        while (!player.cardBunch.isBurst()) {
            if (!isSuccessAddCardToPlayer(player)) return
        }
    }

    private fun isSuccessAddCardToPlayer(player: Player): Boolean {
        if (InputView.getDecision(player)) {
            player.cardBunch.addCard(cardDeck.drawCard())
            OutputView.printPlayerInitialCard(player)
            return true
        }
        OutputView.printPlayerInitialCard(player)
        return false
    }

    private fun showDealerState(dealer: Dealer) {
        val condition = dealer.canGetCard()
        OutputView.printDealerOverCondition(condition)
        if (condition) dealer.cardBunch.addCard(cardDeck.drawCard())
    }

    private fun printFinalWinOrLose(referee: Referee) {
        OutputView.printWinOrLose(referee)
    }
}
