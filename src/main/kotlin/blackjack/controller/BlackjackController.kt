package blackjack.controller

import blackjack.domain.card.Deck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val deck: Deck = Deck()
) {

    fun run() {
        val dealer: Dealer = Dealer()
        val participants: Participants = readParticipants()
        settingPlayersCards(dealer, participants)
        readParticipantsMoreCard(participants)
        drawCard(dealer)
        dealer.decideParticipantsResult(participants)
        dealer.decideDealerResult(participants)
        printSumResult(dealer, participants)
        printFinalResult(dealer, participants)
    }

    private fun readParticipants(): Participants = inputView.readParticipants() ?: readParticipants()

    private fun readHitOrNot(name: String): Boolean = inputView.readHitOrNot(name) ?: readHitOrNot(name)

    private fun settingPlayersCards(dealer: Dealer, participants: Participants) {
        repeat(CARD_SETTING_COUNT) { dealer.addCard(deck.draw()) }
        participants.values.forEach { participant ->
            repeat(CARD_SETTING_COUNT) { participant.addCard(deck.draw()) }
        }

        outputView.printSettingCard(dealer, participants)
    }

    private fun readParticipantsMoreCard(participants: Participants) {
        participants.values.forEach {
            readParticipantMoreCard(it)
        }
    }

    private fun readParticipantMoreCard(participant: Participant) {
        while (true) {
            val check = participant.isGenerateCardPossible()
            if (check) {
                val answer: Boolean = readHitOrNot(participant.name)
                if (answer) participant.addCard(deck.draw())
                outputView.printParticipantCards(participant)
                if (!answer) break
            }
            if (!check) break
        }
    }

    private fun drawCard(dealer: Dealer) {
        if (dealer.isDrawable()) {
            dealer.addCard(deck.draw())
            outputView.printDealerHitCardMent()
            return
        }
        outputView.printDealerNotHitCardMent()
    }

    private fun printSumResult(dealer: Dealer, participants: Participants) {
        outputView.printSumResult(dealer, participants)
    }

    private fun printFinalResult(dealer: Dealer, participants: Participants) {
        outputView.printFinalResult(dealer, participants)
    }

    companion object {
        private const val CARD_SETTING_COUNT = 2
    }
}
