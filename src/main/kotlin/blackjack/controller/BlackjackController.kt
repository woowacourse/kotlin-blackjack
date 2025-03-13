package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.game.GameManager
import blackjack.model.game.ResultManager
import blackjack.model.participant.Dealer
import blackjack.model.participant.Dealer.Companion.DEFAULT_DEALER_NAME
import blackjack.model.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val gameManager = GameManager()
        val cardDeck = CardDeck()
        val dealer = gameManager.prepareDealer(DEFAULT_DEALER_NAME, cardDeck)
        val players = preparePlayers(gameManager, cardDeck, dealer)
        val resultManager = ResultManager(dealer, players)

        progressPlayersDraw(gameManager, players, cardDeck)
        progressDealerDraw(gameManager, dealer, cardDeck)

        displayParticipantsInfo(players)
        displayResults(gameManager, resultManager)
    }

    private fun preparePlayers(
        gameManager: GameManager,
        cardDeck: CardDeck,
        dealer: Dealer,
    ): Players {
        val playerNames = inputView.getPlayers()
        val players = gameManager.preparePlayers(playerNames, cardDeck)

        outputView.displayFirstDrawEnd(players.value.map { player -> player.name })
        outputView.displayParticipantCards(dealer.name, dealer.showInitialCards())

        return players
    }

    private fun progressPlayersDraw(
        gameManager: GameManager,
        players: Players,
        cardDeck: CardDeck,
    ) {
        players.value.forEach { player ->
            outputView.displayParticipantCards(player.name, player.cards)
        }
        players.value.forEach { player ->
            gameManager.progressPlayerDrawUntilFinished(
                player = player,
                draw = cardDeck::draw,
                getCommand = { inputView.getIsRecieveMore(player.name.toString()) },
                onCardReceived = { cards -> outputView.displayParticipantCards(player.name, cards) },
            )
        }
    }

    private fun progressDealerDraw(
        gameManager: GameManager,
        dealer: Dealer,
        cardDeck: CardDeck,
    ) {
        gameManager.progressDealerDraw(dealer, cardDeck::draw)

        outputView.displayDealerDrawInfo(dealer.additionalDrawCount())
        outputView.displayParticipantInfo(dealer.name, dealer.cards, dealer.score(), dealer.isBust())
    }

    private fun displayParticipantsInfo(players: Players) {
        players.value.forEach { player ->
            outputView.displayParticipantInfo(player.name, player.cards, player.score(), player.isBust())
        }
    }

    private fun displayResults(
        gameManager: GameManager,
        resultManager: ResultManager,
    ) {
        val result = gameManager.getResult(resultManager)

        outputView.displayResult(result)
    }
}
