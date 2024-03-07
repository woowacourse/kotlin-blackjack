package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Deck
import blackjack.model.Player
import blackjack.view.IsAddCardInputView
import blackjack.view.OutputView
import blackjack.view.PlayersInputView

class BlackjackController(
    private val playersInputView: PlayersInputView = PlayersInputView(),
    private val isAddCardInputView: IsAddCardInputView = IsAddCardInputView(),
    private val outputView: OutputView = OutputView(),
) {
    private val deck = Deck()

    fun play() {
        val dealer = Dealer(deck)
        val players = playersInputView.readPlayerNames(deck)
        outputView.printInitCard(dealer, players)

        players.gamePlayers.forEach { player ->
            playerTurn(player)
        }

        dealerTurn(dealer)

        outputView.printCardResult(dealer, players)
        outputView.printGameResult(dealer.gameResult(players.gamePlayers))
    }

    private tailrec fun playerTurn(player: Player) {
        if (player.isBust()) {
            println("Bust! 더이상 카드를 받을 수 없습니다.")
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
}
