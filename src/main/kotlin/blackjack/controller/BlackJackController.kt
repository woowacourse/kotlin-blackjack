package blackjack.controller

import blackjack.domain.GameResult
import blackjack.domain.card.Deck
import blackjack.domain.person.Dealer
import blackjack.domain.person.Player
import blackjack.uiModel.PersonUiModel
import blackjack.uiModel.ResultUiModel
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
) {
    private lateinit var deck: Deck

    fun play() {
        val players = generatePlayers()
        val dealer = Dealer()
        deck = Deck()

        settingInitialCards(dealer, players)
        processPlayerTurns(players)
        processDealerTurns(dealer)

        showGameResult(dealer, players)
    }

    private fun generatePlayers(): List<Player> {
        outputView.printNameMessage()
        return inputView.getNames().map { Player(it) }
    }

    private fun settingInitialCards(
        dealer: Dealer,
        players: List<Player>,
    ) {
        dealer.draw(deck)
        players.forEach { person -> person.draw(deck) }
        outputView.printDrawMessage(combinePerson(dealer, players))
    }

    private fun processPlayerTurns(players: List<Player>) {
        players.forEach { player -> handlePlayerTurn(player) }
    }

    private fun handlePlayerTurn(player: Player) {
        while (player.canDraw()) {
            outputView.printFlagMessage(player.name)
            letPlayerDrawCard(player)
        }
    }

    private fun letPlayerDrawCard(player: Player) {
        if (inputView.getFlag()) {
            player.draw(deck)
            outputView.printDrawStatus(PersonUiModel.create(player))
            return
        }
        player.changeToStay()
    }

    private fun processDealerTurns(dealer: Dealer) {
        while (dealer.canDraw()) {
            outputView.printDealerDrawMessage()
            dealer.draw(deck)
        }
    }

    private fun showGameResult(
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.printGameResult(combinePerson(dealer, players))
        val gameResult = GameResult(dealer).calculateWin(players)
        outputView.printResult(ResultUiModel.create(gameResult))
    }

    private fun combinePerson(
        dealer: Dealer,
        players: List<Player>,
    ): List<PersonUiModel> {
        return listOf(PersonUiModel.create(dealer)) + players.map(PersonUiModel::create)
    }
}
