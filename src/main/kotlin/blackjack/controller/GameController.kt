package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameDeck
import blackjack.model.GameState
import blackjack.model.GameState.End
import blackjack.model.GameState.Play
import blackjack.model.Participants
import blackjack.model.Participants.Companion.INITIAL_CARD_COUNTS
import blackjack.model.PlayerGroup
import blackjack.view.InputView
import blackjack.view.InputView.askHitOrStay
import blackjack.view.OutputView
import blackjack.view.OutputView.printDealerDrawCard
import blackjack.view.OutputView.printEveryCards
import blackjack.view.OutputView.printGameSetting
import blackjack.view.OutputView.showPlayerCards

class GameController(private var gameState: GameState = Play) {
    fun run() {
        while (true) {
            when (gameState) {
                Play -> play()
                End -> break
            }
        }
    }

    private fun play() {
        createParticipants().onSuccess { participants ->
            GameDeck.resetCurrentDeck()
            startGame(participants = participants)
        }.onFailure { e ->
            OutputView.printError(e)
        }
    }

    private fun createParticipants(): Result<Participants> {
        return runCatching {
            val playerGroup = createPlayerGroup()
            val dealer = Dealer()
            Participants(dealer = dealer, playerGroup = playerGroup)
        }
    }

    private fun createPlayerGroup(): PlayerGroup {
        val playersNames = InputView.inputPlayersNames()
        val playerGroup = PlayerGroup()
        playerGroup.addPlayer(playerNames = playersNames)
        return playerGroup
    }

    private fun startGame(participants: Participants) {
        playGame(participants = participants).onSuccess {
            OutputView.printMatchResult(participants = participants)
            gameState = End
        }.onFailure { e ->
            participants.resetHand()
            OutputView.printError(e)
        }
    }

    private fun playGame(participants: Participants): Result<Unit> {
        return runCatching {
            start(participants = participants)
            finish(participants = participants)
        }
    }

    private fun start(participants: Participants) {
        initSetting(participants = participants)
        runPlayersTurn(playerGroup = participants.playerGroup)
        runDealerTurn(dealer = participants.dealer)
    }

    private fun initSetting(participants: Participants) {
        repeat(INITIAL_CARD_COUNTS) {
            participants.initParticipantsDeck()
        }
        printGameSetting(participants = participants)
    }

    private fun runPlayersTurn(playerGroup: PlayerGroup) {
        playerGroup.drawPlayerCard(hitOrStay = ::askHitOrStay, showPlayerCards = ::showPlayerCards)
    }

    private fun runDealerTurn(dealer: Dealer) {
        dealer.drawDealerCard(printDealerDrawCard = ::printDealerDrawCard)
    }

    private fun finish(participants: Participants) {
        participants.matchResult()
        printEveryCards(participants = participants)
    }
}
