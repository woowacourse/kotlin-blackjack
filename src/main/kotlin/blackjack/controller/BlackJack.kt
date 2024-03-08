package blackjack.controller

import blackjack.model.CardHand
import blackjack.model.CardHandState
import blackjack.model.Dealer
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.PlayerName
import blackjack.model.Referee
import blackjack.model.Role
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJack(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun gameStart() {
        val dealer = Dealer(CardHand())
        val players = initPlayers()
        val participants =
            Participants(
                dealer + players,
            )

        dealInitialCards(participants, dealer, players)
        runPlayersPhase(players)
        runDealerPhase(dealer)
        showFinalWinning(participants)
    }

    private fun initPlayers(): List<Player> {
        val players = (
            inputView.readPlayersName()
                .map {
                    Player(PlayerName(it), CardHand())
                }
        )
        return players
    }

    private fun dealInitialCards(
        participants: Participants,
        dealer: Dealer,
        players: List<Player>,
    ) {
        outputView.printInitialSetting(participants)

        participants.addInitialCards()
        outputView.printInitialCardHands(dealer, players)
    }

    private fun runPlayersPhase(players: List<Player>) {
        players.forEach {
            runPlayerPhase(it)
        }
    }

    private fun runPlayerPhase(it: Player) {
        while (it.getState() == CardHandState.STAY && inputView.readIsHit(it)) {
            it.runPhase()
            outputView.printPlayerCardHand(it)
        }
    }

    private fun runDealerPhase(dealer: Dealer) {
        if (dealer.getState() == CardHandState.HIT) {
            outputView.printDealerHit()
            dealer.runPhase()
        }
    }

    private fun showFinalWinning(participants: Participants) {
        outputView.printGameResult(participants)

        val playerWinning = Referee().judgeWinningResult(participants.getDealerSum(), participants.getPlayerResult())
        val dealerWinning = playerWinning.judgeDealerWinningResult()

        outputView.printFinalDealerResult(dealerWinning)
        outputView.printFinalPlayersResult(playerWinning)
    }
}

operator fun Dealer.plus(players: List<Role>): List<Role> {
    val tempPlayers = players.toMutableList()
    tempPlayers.add(0, this)
    return tempPlayers.toList()
}
