package blackjack.controller

import blackjack.model.GameManager
import blackjack.model.Participant
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.user.UserDecision
import blackjack.view.user.UserInputValidator

class BlackJackController {
    fun runBlackJackGame() {
        val gameManager = makeGameManager()
        startGameFlow(gameManager)
        playGame(gameManager)
        showResult(gameManager)
    }

    private fun makeGameManager(): GameManager {
        val participants = UserInputValidator.makeParticipants()
        return GameManager(
            participants = participants,
        )
    }

    private fun startGameFlow(gameManager: GameManager) {
        val dealer = gameManager.getDealer()
        val players = gameManager.getPlayers()

        gameManager.setGame()
        OutputView.outputParticipantsName(
            dealerName = dealer.getName(),
            players = players,
        )

        dealer.openInitCards()
            .also { cards ->
                if (cards.isEmpty()) {
                    println(ERROR_CARD_INDEX)
                }
            }
            .forEach { firstCard ->
                OutputView.outputDealerCurrentHandCard(
                    name = dealer.getName(),
                    firstCard = firstCard,
                )
            }
        OutputView.outputPlayersCurrentHandCard(players)
    }

    private fun playGame(gameManager: GameManager) {
        gameManager.getAlivePlayers().forEach { participant ->
            playPlayer(
                participant,
                gameManager,
            )
        }
        playDealer(gameManager)
    }

    private fun playPlayer(
        participant: Participant,
        gameManager: GameManager,
    ) {
        while (participant.checkShouldDrawCard()) {
            processPlayerDecision(
                participant = participant,
                gameManager = gameManager,
            )
        }
    }

    private fun processPlayerDecision(
        participant: Participant,
        gameManager: GameManager,
    ) {
        when (InputView.inputPlayerDecision(participant.getName())) {
            UserDecision.YES -> {
                processUserDecision(
                    participant = participant,
                    gameManager = gameManager,
                )
                OutputView.outputPlayerCurrentHandCard(participant)
            }

            UserDecision.NO -> participant.transitionToStayState()
        }
    }

    private fun playDealer(gameManager: GameManager) {
        val dealer = gameManager.getDealer()
        while (dealer.checkShouldDrawCard()) {
            OutputView.outputDealerRule()
            processUserDecision(
                participant = dealer,
                gameManager = gameManager,
            )
        }
    }

    private fun showResult(gameManager: GameManager) {
        OutputView.outputParticipantsHandCard(gameManager.getParticipants())
        OutputView.outputBlackResult()
        val gameResult = gameManager.calculateGameResult()
        val bettingResults = gameResult.calculateRevenuePercentages()
        OutputView.outputParticipantResult(
            dealer = gameManager.getDealer(),
            bettingResults = bettingResults,
        )
    }

    private fun processUserDecision(
        participant: Participant,
        gameManager: GameManager,
    ) {
        try {
            participant.draw(gameManager.returnCardForParticipant())
        } catch (e: IllegalArgumentException) {
            println(e.message)
        }
    }

    companion object {
        const val ERROR_CARD_INDEX = "딜러가 가지고 있는 카드가 없습니다."
    }
}
