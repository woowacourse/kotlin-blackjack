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
    private val dealer = Dealer.withInitCards(deck::draw)
    private lateinit var players: Players

    fun play() {
        setUpGame()
        gamePlay()
    }

    private fun setUpGame() {
        players = playersInputView.read(deck::draw)
        outputView.printInitCard(dealer, players)
    }

    private fun gamePlay() {
        val result =
            players.playTurn(
                cards = deck::draw,
                isHit = {
                    isAddCardInputView.read(it)
                },
                showResult = {
                    outputView.printPlayerCard(it)
                },
            )
        dealerTurn()
        showResult(result)
    }

    private fun dealerTurn() {
        dealer.playTurn(
            cards = deck::draw,
            showResult = outputView::printDealerAddCard,
        )
    }

    private fun showResult(gameResult: Map<Player, Finish>) {
        outputView.printCardResult(dealer, players)
        outputView.printGameResult(dealer.gameResult(gameResult))
    }
}
