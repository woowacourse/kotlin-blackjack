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
        val players = preparePlayers(cardDeck, dealer)

        progressPlayersDraw(players, cardDeck)
        progressDealerDraw(dealer, cardDeck)

        displayParticipantsInfo(players)
        displayResults(dealer, players)
    }

    private fun preparePlayers(
        cardDeck: CardDeck,
        dealer: Dealer,
    ): Players {
        val players = Players.from(inputView.getPlayers())
        players.value.forEach { player ->
            player.draw(cardDeck)
        }
        outputView.displayFirstDrawEnd(players.value)
        outputView.displayParticipantCards(cards = dealer.cards().take(DEALER_FIRST_SHOWN_COUNT))
        return players
    }

    private fun progressPlayersDraw(
        players: Players,
        cardDeck: CardDeck,
    ) {
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.cards())
        }
        players.value.forEach { player ->
            progressPlayerDrawUntilFinished(player, cardDeck)
        }
    }

    private fun progressPlayerDrawUntilFinished(
        player: Player,
        cardDeck: CardDeck,
    ) {
        while (true) {
            if (!inputView.getIsDrawMore(player.name)) break

            player.draw(cardDeck)
            outputView.displayParticipantCards(player.name, player.cards())

            if (player.isBust()) return
        }
    }

    private fun progressDealerDraw(
        dealer: Dealer,
        cardDeck: CardDeck,
    ) {
        val dealerDrawCount = dealer.drawUntilFinished(cardDeck)
        outputView.displayDealerDrawInfo(dealerDrawCount)

        outputView.displayParticipantInfo(
            cards = dealer.cards(),
            score = dealer.score(),
            isBust = dealer.isBust(),
        )
    }

    private fun displayParticipantsInfo(players: Players) {
        players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.cards(), player.score(), player.isBust())
        }
    }

    private fun displayResults(
        dealer: Dealer,
        players: Players,
    ) {
        outputView.displayResultTitle()

        val dealerResult = dealer.result(players.scores())
        outputView.displayDealerResult(dealerResult)

        val playerResults: Map<String, WinningResult> = players.results(dealer.score())
        playerResults.forEach { (name, winningResult) ->
            outputView.displayPlayerResult(name, winningResult)
        }
    }

    companion object {
        private const val DEALER_FIRST_SHOWN_COUNT = 1
    }
}
