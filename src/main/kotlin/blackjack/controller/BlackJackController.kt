package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.GameManager
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView
import blackjack.view.user.UserDecision
import kotlin.system.exitProcess

class BlackJackController {
    private lateinit var gameManager: GameManager

    fun startGameFlow() {
        val dealer = Dealer()
        val players = InputView.inputPlayers()
        val participants =
            makeParticipants(dealer, players) ?: exitProcess(1)
        gameManager =
            GameManager(
                participants = participants,
            )
        gameManager.setGame()
        OutputView.outputParticipantsName(
            dealerName = dealer.getName(),
            players = participants.getPlayers(),
        )
        dealer.openFirstCard()?.let { firstCard ->
            OutputView.outputDealerCurrentHandCard(
                name = dealer.getName(),
                firstCard = firstCard,
            )
        }
        OutputView.outputPlayersCurrentHandCard(participants.getPlayers())
    }

    fun playGame() {
        gameManager.getAlivePlayers().forEach { player ->
            playPlayer(player as Player)
        }
        playDealer()
    }

    private fun playPlayer(player: Player) {
        while (player.checkHitState() && InputView.inputPlayerDecision(player.getName()) == UserDecision.YES) {
            gameManager.applyUserDrawDecision(player)
            OutputView.outputPlayerCurrentHandCard(player)
        }
    }

    private fun playDealer() {
        val dealer = gameManager.getDealer()
        while (dealer.checkShouldDealerDrawCard()) {
            OutputView.outputDealerRule()
            gameManager.applyUserDrawDecision(dealer)
        }
        gameManager.judgeBlackJackScores()
    }

    fun showResult() {
        OutputView.outputParticipantsHandCard(gameManager.getParticipants())
        OutputView.outputBlackResult()
        OutputView.outputDealerResult(
            dealerName = gameManager.getDealer().getName(),
            dealerResults = gameManager.getDealerResults(),
        )
        OutputView.outputPlayersResult(
            playersResult = gameManager.getPlayerResults(),
        )
    }

    private fun makeParticipants(
        dealer: Dealer,
        players: List<Player>,
    ): Participants? {
        return try {
            Participants(
                participants = listOf(dealer) + players,
            )
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }
}
