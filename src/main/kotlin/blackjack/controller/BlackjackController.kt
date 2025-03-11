package blackjack.controller

import blackjack.model.CardDeck
import blackjack.model.Dealer
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.ResultManager
import blackjack.model.ScoreCalculator
import blackjack.model.WinningResult
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val cardDeck = CardDeck()
        val scoreCalculator = ScoreCalculator()
        val dealer = prepareDealer(cardDeck, scoreCalculator)
        val players = preparePlayers(cardDeck, dealer, scoreCalculator)
        val resultManager = ResultManager(dealer, players)

        progressPlayersDraw(players, cardDeck)
        progressDealerDraw(dealer, cardDeck)

        displayParticipantsInfo(players)
        displayResults(resultManager)
    }

    private fun prepareDealer(
        cardDeck: CardDeck,
        scoreCalculator: ScoreCalculator,
    ): Dealer {
        val dealer = Dealer(scoreCalculator)
        dealer.recieveCards(cardDeck::draw)
        return dealer
    }

    private fun preparePlayers(
        cardDeck: CardDeck,
        dealer: Dealer,
        scoreCalculator: ScoreCalculator,
    ): Players {
        val playerNames = inputView.getPlayers()
        val players = Players.from(playerNames, scoreCalculator)
        players.value.forEach { player -> player.recieveCards(cardDeck::draw) }

        outputView.displayFirstDrawEnd(players.value.map { player -> player.name })
        outputView.displayParticipantCards(cards = dealer.showInitialCards())

        return players
    }

    private fun progressPlayersDraw(
        players: Players,
        cardDeck: CardDeck,
    ) {
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.cards)
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
            if (!inputView.getIsRecieveMore(player.name)) break

            val canRecieveMore = player.recieveCards(cardDeck::draw)
            outputView.displayParticipantCards(player.name, player.cards)

            if (!canRecieveMore) return
        }
    }

    private fun progressDealerDraw(
        dealer: Dealer,
        cardDeck: CardDeck,
    ) {
        while (true) {
            val canRecieveMore = dealer.recieveCards(cardDeck::draw)
            if (!canRecieveMore) break
        }

        outputView.displayDealerDrawInfo(dealer.additionalDrawCount())
        outputView.displayParticipantInfo(
            cards = dealer.cards,
            score = dealer.score(),
            isBust = dealer.isBust(),
        )
    }

    private fun displayParticipantsInfo(players: Players) {
        players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.cards, player.score(), player.isBust())
        }
    }

    private fun displayResults(resultManager: ResultManager) {
        outputView.displayResultTitle()

        val dealerResult = resultManager.dealerResult()
        outputView.displayDealerResult(dealerResult)

        val playerResults: Map<String, WinningResult> = resultManager.playerResults()
        playerResults.forEach { (name, winningResult) ->
            outputView.displayPlayerResult(name, winningResult)
        }
    }
}
