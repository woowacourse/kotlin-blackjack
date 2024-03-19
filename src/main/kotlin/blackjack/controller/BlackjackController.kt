package blackjack.controller

import blackjack.model.deck.Deck
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.model.participant.state.Finish
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
    }

    private fun setUpGame() {
        players = playersInputView.read(deck::draw)
        outputView.printInitCard(dealer, players)
    }

    private fun gamePlayersTurn() {
        val result =
            players.playTurn(deck::draw, {
                isAddCardInputView.read(it)
            }, {
                outputView.printPlayerCard(it)
            })
        dealerTurn(dealer)
        showResult(result)
    }

    private fun dealerTurn(dealer: Dealer) {
        dealer.playTurn(
            deck::draw,
            outputView::printDealerAddCard,
        )
    }

    private fun showResult(gameResult: Map<Player, Finish>) {
        outputView.printCardResult(dealer, players)
        outputView.printGameResult(dealer.gameResult(gameResult))
    }
}
