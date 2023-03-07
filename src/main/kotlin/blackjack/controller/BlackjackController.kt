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
        drawPlayerCards(dealer, participants)

        dealer.decidePlayerResult(participants)

        printSumResult(dealer, participants)
        printFinalResult(dealer, participants)
    }

    private fun drawPlayerCards(dealer: Dealer, participants: Participants) {
        drawDealerCard(dealer)
        drawParticipantsCards(dealer, participants)
    }

    private fun setInitialPlayersCards(dealer: Dealer, participants: Participants) {
        dealer.setInitialPlayersCards(participants)
        outputView.printInitialSettingCard(dealer, participants)
    }

    private fun drawParticipantsCards(dealer: Dealer, participants: Participants) {
        participants.values.forEach {
            drawParticipantCard(dealer, it)
        }
    }

    private fun drawParticipantCard(dealer: Dealer, participant: Participant) {
        while (true) {
            val check = participant.isGenerateCardPossible()
            if (check) {
                val answer: Boolean = readHitOrNot(participant.name)
                if (answer) participant.addCard(dealer.drawCard())
                outputView.printParticipantCards(participant)
                if (!answer) break
            }
            if (!check) break
        }
    }

    private fun drawDealerCard(dealer: Dealer) {
        if (dealer.isDrawable()) {
            dealer.addCard(dealer.drawCard())
            outputView.printDealerHitCardMent()
            return
        }
        outputView.printDealerNotHitCardMent()
    }

    private fun printSumResult(dealer: Dealer, participants: Participants) =
        outputView.printSumResult(dealer, participants)

    private fun printFinalResult(dealer: Dealer, participants: Participants) =
        outputView.printFinalResult(dealer, participants)

    private fun readParticipants(): Participants = inputView.readParticipants() ?: readParticipants()

    private fun readHitOrNot(name: String): Boolean = inputView.readHitOrNot(name) ?: readHitOrNot(name)
}
