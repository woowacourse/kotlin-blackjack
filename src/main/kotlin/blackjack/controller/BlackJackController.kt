package blackjack.controller

import blackjack.model.result.Money
import blackjack.model.role.Dealer
import blackjack.model.role.Participants
import blackjack.model.role.Player
import blackjack.model.role.PlayerName
import blackjack.model.role.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun gameStart() {
        val (dealer, players) = initGameParticipants()

        runGame(players, dealer)

        showGameResult(dealer, players)
    }

    private fun initGameParticipants(): Participants {
        val dealer = Dealer()
        val players =
            Players(
                (inputView.readPlayersName())
                    .map {
                        val name = PlayerName(it)
                        Player(name, Money.bet(inputView.readMoney(name)))
                    },
            )
        outputView.printInitialDealing(dealer, players)
        return Participants(dealer, players)
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
        val playerWinning = players.calculatePlayersWinning(dealer)
        val dealerWinning = playerWinning.judgeDealerWinningResult()

        outputView.printWinningResult(dealerWinning, playerWinning)

        val playersProfit = players.calculatePlayersProfit(dealer)
        val dealerProfit = playersProfit.calculateDealerProfit()
        outputView.printParticipantsProfit(dealerProfit, playersProfit)
    }
}
