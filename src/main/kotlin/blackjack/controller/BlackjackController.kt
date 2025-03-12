package blackjack.controller

import blackjack.model.card.CardDeck
import blackjack.model.game.GameManager
import blackjack.model.game.ResultManager
import blackjack.model.game.UserCommand.HIT
import blackjack.model.game.UserCommand.STAY
import blackjack.model.game.UserCommand.UNKNOWN
import blackjack.model.participant.Dealer
import blackjack.model.participant.Player
import blackjack.model.participant.Players
import blackjack.model.rule.ScoreCalculator
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val gameManager = GameManager()
        val cardDeck = CardDeck()
        val scoreCalculator = ScoreCalculator()
        val dealer = gameManager.prepareDealer(DEFAULT_DEALER_NAME, cardDeck, scoreCalculator)
        val players = preparePlayers(gameManager, cardDeck, dealer, scoreCalculator)
        val resultManager = ResultManager(dealer, players)

        progressPlayersDraw(players, cardDeck)
        progressDealerDraw(gameManager, dealer, cardDeck)

        displayParticipantsInfo(players)
        displayResults(gameManager, resultManager)
    }

    private fun preparePlayers(
        gameManager: GameManager,
        cardDeck: CardDeck,
        dealer: Dealer,
        scoreCalculator: ScoreCalculator,
    ): Players {
        val playerNames = inputView.getPlayers()
        val players = gameManager.preparePlayers(playerNames, cardDeck, scoreCalculator)

        outputView.displayFirstDrawEnd(players.value.map { player -> player.name })
        outputView.displayParticipantCards(dealer.name, dealer.showInitialCards())

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
            when (inputView.getIsRecieveMore(player.name)) {
                HIT -> {
                    player.recieveCards(cardDeck::draw)
                    outputView.displayParticipantCards(player.name, player.cards)
                    if (!player.isDrawable()) return
                }
                STAY -> break
                UNKNOWN -> throw IllegalArgumentException("[ERROR] 올바르지 않은 입력입니다.")
            }
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

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
    }
}
