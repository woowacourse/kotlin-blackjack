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
        val cardDeck = CardDeck()
        val dealer = Dealer().apply { this.draw(cardDeck) }

        val players = Players.from(inputView.getPlayers())
        players.value.forEach { player ->
            player.draw(cardDeck)
        }
        outputView.displayFirstDrawEnd(players.value)

        outputView.displayParticipantCards(cards = dealer.hand.cards.take(1))
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.hand.cards)
        }
        players.value.forEach { player ->
            getIsPlayerDrawMore(player, cardDeck)
        }

        val dealerDrawCount = dealer.drawUntilFinished(cardDeck)
        outputView.displayDealerDrawInfo(dealerDrawCount)
        val playerResults: Map<String, WinningResult> = players.results(dealer.hand.score())
        val dealerResult = dealer.result(players.scores())

        outputView.displayParticipantInfo(cards = dealer.hand.cards, score = dealer.hand.score(), isBust = dealer.hand.isBust())

        players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.hand.cards, player.hand.score(), player.hand.isBust())
        }
        outputView.displayResultTitle()
        outputView.displayDealerResult(dealerResult)
        playerResults.forEach { (name, winningResult) ->
            outputView.displayPlayerResult(name, winningResult)
        }
    }

    private fun getIsPlayerDrawMore(
        player: Player,
        cardDeck: CardDeck,
    ) {
        while (true) {
            if (!inputView.getIsDrawMore(player.name)) break

            player.draw(cardDeck)
            outputView.displayParticipantCards(player.name, player.hand.cards)

            if (player.hand.isBust()) return
        }
    }
}
