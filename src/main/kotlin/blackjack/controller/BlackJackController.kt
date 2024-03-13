package blackjack.controller

import blackjack.model.ScoreBoard
import blackjack.model.role.Dealer
import blackjack.model.role.Player
import blackjack.model.role.PlayerName
import blackjack.model.role.Players
import blackjack.view.InputView2
import blackjack.view.OutputView2

class BlackJackController(
    private val inputView: InputView2,
    private val outputView: OutputView2,
) {
    fun gameStart() {
        val dealer = Dealer()
        val players = Players((inputView.readPlayersName()).map { Player(PlayerName(it)) })
        outputView.printInitialDealing(dealer, players)

        runGame(players, dealer)

        showGameResult(dealer, players)
    }

    private fun showGameResult(
        dealer: Dealer,
        players: Players,
    ) {
        outputView.printFinalCardHands(dealer, players)
        showWinningResult(dealer, players)
    }

    private fun runGame(
        players: Players,
        dealer: Dealer,
    ) {
        runPlayersPhase(players)
        runDealerPhase(dealer)
    }

    private fun runPlayersPhase(players: Players) {
        players.players.forEach { player ->
            player.runPhase({ inputView.readIsHit(player) }) { outputView.printPlayerCardHand(player) }
        }
    }

    private fun runDealerPhase(dealer: Dealer) {
        dealer.runPhase({ dealer.dealerDecisionCondition.invoke() }) { outputView.printDealerHit() }
    }

    private fun showWinningResult(
        dealer: Dealer,
        players: Players,
    ) {
        val dealerScore = dealer.state.getCardHands().calculateScore()
        val record = ScoreBoard(players.players.associate { it.name to it.state.getCardHands().calculateScore() })
        val playerWinning = record.calculatePlayerWinning(dealerScore)
        val dealerWinning = playerWinning.judgeDealerWinningResult()

        outputView.printWinningResult(dealerWinning, playerWinning)
    }
}
