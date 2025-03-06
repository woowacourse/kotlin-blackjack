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
        val dealer = Dealer()

        val players = Players.from(inputView.getPlayers())
        players.value.forEach { player ->
            player.draw(cardDeck)
        }
        outputView.displayFirstDrawEnd(players.value)

        outputView.displayParticipantInfo(cards = dealer.hand.cards.take(1))
        players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.hand.cards)
        }
        players.value.forEach { player ->
            getIsPlayerDrawMore(player, cardDeck)
        }

        val dealerDrawCount = dealer.drawUntilFinished(cardDeck)
        outputView.displayDealerDrawInfo(dealerDrawCount)
        val playerResults: Map<String, WinningResult> = players.results(dealer.hand.score())
        val dealerResult = dealer.result(players.scores())

        outputView.displayParticipantInfo(cards = dealer.hand.cards, score = dealer.hand.score())

        players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.hand.cards, player.hand.score())
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
        while (!player.hand.isBust()) {
            val isDrawMore = inputView.getIsDrawMore(player.name)
            if (isDrawMore) player.draw(cardDeck)
            outputView.displayParticipantInfo(player.name, player.hand.cards)
            if (!isDrawMore) break
        }
    }
}
