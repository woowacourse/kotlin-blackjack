package blackjack.controller

import blackjack.model.Dealer
import blackjack.model.Participants
import blackjack.model.Player
import blackjack.model.Players
import blackjack.model.Referee
import blackjack.model.card.CardHand
import blackjack.model.card.CardHandState
import blackjack.model.role.PlayerName
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJack(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun gameStart() {
        val dealer = Dealer(CardHand())
        val players = initPlayers()

        val participants = Participants(dealer, players)

        dealInitialCards(participants)
        runPlayersPhase(players)

        runDealerPhase(dealer)
        showFinalWinning(participants)
    }

    private fun initPlayers(): Players =
        Players(
            inputView.readPlayersName()
                .map {
                    Player(PlayerName(it), CardHand())
                },
        )

    private fun dealInitialCards(participants: Participants) {
        outputView.printInitialSetting(participants)

        participants.addInitialCards()
        outputView.printInitialCardHands(participants)
    }

    private fun runPlayersPhase(players: Players) {
        players.players.forEach {
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
