package blackjack.controller

import blackjack.model.card.CardProvider
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.util.retryWhileNotException
import blackjack.view.CardDecision
import blackjack.view.OutputView
import blackjack.view.PlayersBattingAmountInputView
import blackjack.view.PlayersNameInputView

class BlackjackController(
    private val playersNameInputView: PlayersNameInputView,
    private val playersBattingAmountInputView: PlayersBattingAmountInputView,
    private val cardDecision: CardDecision,
    private val outputView: OutputView,
    private val cardProvider: CardProvider,
) {
    fun run() {
        val dealer = Dealer()
        val players =
            retryWhileNotException {
                val playersName = playersNameInputView.read()
                val playersBattingAmount = playersBattingAmountInputView.read(playersName)
                Players.from(playersName, playersBattingAmount)
            }

        initHandCards(dealer, players)
        players.takePlayersTurn()
        dealer.takeDealerTurn()
    }

    private fun initHandCards(
        dealer: Dealer,
        players: Players,
    ) {
        dealer.initCard(cardProvider)
        players.playerGroup.forEach { it.initCard(cardProvider) }
        outputView.printInitCard(dealer, players)
    }

    private fun Players.takePlayersTurn() {
        playerGroup.forEach { it.takePlayerTurn() }
    }

    private fun Player.takePlayerTurn() {
        takeTurn(cardProvider, cardDecision) {
            outputView.printPlayerCardsMessage(it)
        }
    }

    private fun Dealer.takeDealerTurn() {
        takeTurn(cardProvider) { outputView.printDealerAdditionalCardMessage() }
    }
}
