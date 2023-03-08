package blackjack.controller

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    fun run() {
        val dealer: Dealer = Dealer()
        val participants: Participants = readParticipants()
        setInitialPlayersCards(dealer, participants)
        hitPlayerCards(dealer, participants)
        dealer.decidePlayerResult(participants)
        printResult(dealer, participants)
    }

    private fun setInitialPlayersCards(dealer: Dealer, participants: Participants) {
        dealer.setInitialPlayersCards(participants)
        outputView.printInitialSettingCard(dealer, participants)
    }

    private fun hitPlayerCards(dealer: Dealer, participants: Participants) {
        hitParticipantsCards(dealer, participants)
        hitDealerCard(dealer)
    }

    private fun hitParticipantsCards(dealer: Dealer, participants: Participants) {
        participants.values.forEach {
            hitParticipantCards(dealer, it)
        }
    }

    private fun hitParticipantCards(dealer: Dealer, participant: Participant) {
        while (participant.canHit() && readHitOrNot(participant.name)) {
            participant.addCard(dealer.drawCard())
            outputView.printCurrentPlayerCards(participant)
        }
    }

    private fun hitDealerCard(dealer: Dealer) {
        if (dealer.canHit()) {
            dealer.addCard(dealer.drawCard())
            outputView.printDealerHitMessage()
            return
        }
        outputView.printDealerNotHitMessage()
    }

    private fun printResult(dealer: Dealer, participants: Participants) {
        outputView.printSumResult(dealer, participants)
        outputView.printPlayersResults(dealer, participants)
    }

    private fun readParticipants(): Participants = inputView.readParticipants() ?: readParticipants()

    private fun readHitOrNot(name: String): Boolean = inputView.readHitOrNot(name) ?: readHitOrNot(name)
}
