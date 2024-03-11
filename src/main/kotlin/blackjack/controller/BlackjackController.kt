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
    private val deck: Deck = Deck()
    private val dealer: Dealer = Dealer(deck)
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
        players.gamePlayers.forEach { player: Player ->
            playerTurn(player)
        }
    }

    private tailrec fun playerTurn(player: Player) {
        if (player.isBust()) {
            outputView.printBustMessage()
            return
        }
        if (player.addCard(isAddCardInputView.readIsAddCard(player.name))) {
            outputView.printPlayerCard(player)
            playerTurn(player)
        }
    }

    private tailrec fun dealerTurn(dealer: Dealer) {
        if (dealer.addCard()) {
            outputView.printDealerAddCard()
            dealerTurn(dealer)
        }
    }

    private fun showResult() {
        outputView.printCardResult(dealer, players)
        outputView.printGameResult(dealer.gameResult(players.gamePlayers))
    }
}
