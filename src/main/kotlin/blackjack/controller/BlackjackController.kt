package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.WinningResult
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        outputView.displayPlayerEnrollGuide()

        val cardDeck = CardDeck()
        val dealer = Dealer(cardDeck)

        val players = Players(inputView.getPlayers(), cardDeck)
        outputView.displayFirstDrawEnd(players.players)

        outputView.displayParticipantInfo(cards = dealer.hand.cards.take(1))
        players.players.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.hand.cards)
        }
        players.players.forEach { player ->
            getIsPlayerDrawMore(player)
        }

        val dealerDrawCount = dealer.drawUntilFinished()
        outputView.displayDealerDrawInfo(dealerDrawCount)
        val playerResults: Map<String, WinningResult> = players.results(dealer.hand.score())
        val dealerResult = dealer.result(players.scores())

        outputView.displayParticipantInfo(cards = dealer.hand.cards, score = dealer.hand.score())

        players.players.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.hand.cards, player.hand.score())
        }
        outputView.displayResultTitle()
        outputView.displayDealerResult(dealerResult)
        playerResults.forEach { (name, winningResult) ->
            outputView.displayPlayerResult(name, winningResult)
        }
    }

    private fun getIsPlayerDrawMore(player: Player) {
        while (!player.hand.isBusted()) {
            outputView.askDrawMoreCard(player.name)
            if (inputView.getIsDrawMore()) player.draw() else break
            outputView.displayParticipantInfo(player.name, player.hand.cards)
        }
        outputView.displayParticipantInfo(player.name, player.hand.cards)
    }
}
