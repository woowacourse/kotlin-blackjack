package blackjack

import blackjack.model.BlackjackGameStatistics
import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Participant
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller {

    fun run() {
        val deck = Deck()
        val players = makePlayers()
        val dealer = Dealer()
        initParticipantsCard(dealer, players, deck)
        proceedParticipantsTurn(dealer, players, deck)
        printStatistics(dealer, players)
    }

    private fun makePlayers(): List<Player> {
        val names: List<String> = InputView.getNames()
        return names.map { Player(it) }
    }

    private fun initParticipantsCard(
        dealer: Dealer,
        players: List<Player>,
        deck: Deck,
    ) {
        players.forEach { player ->
            player.initCard(deck)
        }
        dealer.initCard(deck)
        OutputView.printInitialResult(dealer, players)
    }

    private fun proceedParticipantsTurn(
        dealer: Dealer,
        players: List<Player>,
        deck: Deck,
    ) {
        players.forEach { player ->
            proceedPlayerTurn(player, deck)
        }
        proceedDealerTurn(dealer, deck)
    }

    private fun proceedPlayerTurn(player: Player, deck: Deck) {
        if (player.isMaxScore()) {
            OutputView.printBlackJackMessage(player)
            return
        }
        while (player.isNotBustedAndHitable() && askPick(player.name)) {
            player.pickCard(deck)
            OutputView.printParticipantStatus(player)
        }
        if (player.isBusted()) {
            OutputView.printBustedMessage(player)
        }
    }

    private fun askPick(name: String): Boolean {
        return InputView.askPickAgain(name)
    }

    private fun proceedDealerTurn(dealer: Dealer, deck: Deck) {
        while (dealer.isNotBustedAndHitable()) {
            dealer.pickCard(deck)
            OutputView.printDealerHitMessage()
        }
    }

    private fun printStatistics(
        dealer: Dealer,
        players: List<Player>,
    ) {
        OutputView.printResult(dealer, players)
        val gameStatistics = BlackjackGameStatistics(dealer, players)
        OutputView.printGameStatistics(gameStatistics)
    }

    private fun Participant.initCard(deck: Deck) {
        this.pickCard(deck)
        this.pickCard(deck)
    }

    private fun Participant.pickCard(deck: Deck) {
        this.addCard(deck.pop())
    }
}
