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
        val players = preparePlayers(cardDeck)

        progressPlayersDraw(dealer, players, cardDeck)
        progressDealerDraw(dealer, cardDeck)

        displayParticipantsInfo(players)
        displayResults(dealer, players)
    }

    private fun preparePlayers(cardDeck: CardDeck): Players {
        val players = Players.from(inputView.getPlayers())
        players.value.forEach { player ->
            player.draw(cardDeck)
        }
        outputView.displayFirstDrawEnd(players.value)
        return players
    }

    private fun progressPlayersDraw(
        dealer: Dealer,
        players: Players,
        cardDeck: CardDeck,
    ) {
        outputView.displayParticipantCards(cards = dealer.hand.cards.take(DEALER_FIRST_SHOWN_COUNT))
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.hand.cards)
        }
        players.value.forEach { player ->
            getIsPlayerDrawMore(player, cardDeck)
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

    private fun progressDealerDraw(
        dealer: Dealer,
        cardDeck: CardDeck,
    ) {
        val dealerDrawCount = dealer.drawUntilFinished(cardDeck)
        outputView.displayDealerDrawInfo(dealerDrawCount)

        outputView.displayParticipantInfo(
            cards = dealer.hand.cards,
            score = dealer.hand.score(),
            isBust = dealer.hand.isBust(),
        )
    }

    private fun displayParticipantsInfo(players: Players) {
        players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.hand.cards, player.hand.score(), player.hand.isBust())
        }
    }

    private fun displayResults(
        dealer: Dealer,
        players: Players,
    ) {
        val dealerResult = dealer.result(players.scores())
        outputView.displayDealerResult(dealerResult)

        outputView.displayResultTitle()
        val playerResults: Map<String, WinningResult> = players.results(dealer.hand.score())
        playerResults.forEach { (name, winningResult) ->
            outputView.displayPlayerResult(name, winningResult)
        }
    }

    companion object {
        private const val DEALER_FIRST_SHOWN_COUNT = 1
    }
}
