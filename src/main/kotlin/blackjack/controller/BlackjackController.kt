package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.game.BettingManager
import blackjack.model.game.GameManager
import blackjack.model.game.WinningManager
import blackjack.model.participant.Dealer
import blackjack.model.participant.Dealer.Companion.DEFAULT_DEALER_NAME
import blackjack.model.participant.Money
import blackjack.model.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val gameManager = GameManager()
        val bettingManager = BettingManager()
        val cardDeck = CardDeck()
        val dealer = gameManager.prepareDealer(DEFAULT_DEALER_NAME, cardDeck)
        val players = preparePlayers(gameManager, cardDeck)
        val winningManager = WinningManager(dealer, players)

        gameManager.betPlayersMoney(players, bettingManager) { name ->
            Money(inputView.getBettingMoney(name.toString()))
        }

        outputView.displayFirstDrawEnd(players.value.map { player -> player.name })
        outputView.displayParticipantCards(dealer.name, dealer.showInitialCards())

        progressPlayersDraw(gameManager, players, cardDeck)
        progressDealerDraw(gameManager, dealer, cardDeck)

        displayParticipantsInfo(players)
        displayResults(gameManager, winningManager)
    }

    private fun preparePlayers(
        gameManager: GameManager,
        cardDeck: CardDeck,
    ): Players {
        val playerNames = inputView.getPlayers()
        val players = gameManager.preparePlayers(playerNames, cardDeck)

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
        winningManager: WinningManager,
    ) {
        val result = gameManager.getResult(winningManager)

        outputView.displayResult(result)
    }
}
