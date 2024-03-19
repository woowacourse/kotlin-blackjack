package blackjack.controller

import blackjack.model.Result
import blackjack.model.deck.Deck
import blackjack.model.participant.Dealer
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
                playResult = {
                    outputView.printPlayerCard(it)
                },
            )
        dealerTurn()
        showResult(result)
    }

    private fun dealerTurn() {
        dealer.playTurn(
            cards = deck::draw,
            playResult = outputView::printDealerAddCard,
        )
    }

    private fun showResult(gameResult: List<Result>) {
        outputView.printCardResult(dealer, players)
        outputView.printGameResult(dealer.getGameResult(gameResult))
    }
}
