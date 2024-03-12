package blackjack.controller

import blackjack.model.deck.Deck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.view.IsAddCardInputView
import blackjack.view.OutputView
import blackjack.view.PlayersInputView

class BlackjackController(
    private val playersInputView: PlayersInputView = PlayersInputView(),
    private val isAddCardInputView: IsAddCardInputView = IsAddCardInputView(),
    private val outputView: OutputView = OutputView(),
) {
    private val deck = Deck()
    private val dealer = Dealer.withInitCards(deck)
    private lateinit var players: Players

    fun play() {
        setUpGame()
        gamePlayersTurn()
        dealerTurn(dealer)
        showResult()
    }

    private fun setUpGame() {
        players = playersInputView.readPlayerNames(deck)
        outputView.printInitCard(dealer, players)
    }

    private fun gamePlayersTurn() {
        players.gamePlayers.forEach { player ->
            playerTurn(player)
        }
    }

    private tailrec fun playerTurn(player: Player) {
        if (player.isBust()) {
            outputView.printBustMessage()
            return
        }
        if (isAddCardInputView.readIsAddCard(player.name)) {
            player.add(deck.draw(1))
            outputView.printPlayerCard(player)
            playerTurn(player)
        }
    }

    private tailrec fun dealerTurn(dealer: Dealer) {
        if (dealer.add(deck.draw(1))) {
            outputView.printDealerAddCard()
            dealerTurn(dealer)
        }
    }

    private fun showResult() {
        outputView.printCardResult(dealer, players)
        outputView.printGameResult(dealer.gameResult(players.gamePlayers))
    }
}
