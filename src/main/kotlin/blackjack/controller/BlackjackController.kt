package blackjack.controller

import blackjack.domain.CardGenerator
import blackjack.domain.RandomGenerator
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val cardGenerator: CardGenerator = CardGenerator(RandomGenerator())
) {

    fun run() {
        val dealer: Dealer = Dealer()
        val participants = readParticipants()
        settingPlayersCards(dealer, participants)
        readParticipantsMoreCard(participants)
        giveDealerMoreCard(dealer)
        dealer.decideParticipantsResult(participants)
        dealer.decideDealerResult(participants)
        printSumResult(dealer, participants)
        printFinalResult(dealer, participants)
    }

    private fun readParticipants(): Participants =
        Participants(inputView.readParticipantsName().map { Participant(it) })

    private fun settingPlayersCards(dealer: Dealer, participants: Participants) {
        repeat(CARD_SETTING_COUNT) { dealer.addCard(cardGenerator.generateCard()) }
        participants.values.forEach { participant ->
            repeat(CARD_SETTING_COUNT) { participant.addCard(cardGenerator.generateCard()) }
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
                val answer = inputView.readHitOrNot(participant.name)
                if (answer == InputView.ANSWER_HIT) {
                    participant.addCard(cardGenerator.generateCard())
                }
                outputView.printParticipantCards(participant)
                if (answer == InputView.ANSWER_NOT_HIT) break
            }
            if (!check) break
        }
    }

    private fun giveDealerMoreCard(dealer: Dealer) {
        val check = dealer.checkMustGenerateCard()
        if (check) {
            dealer.addCard(cardGenerator.generateCard())
            outputView.printDealerHitCardMent()
        }
        if (!check) outputView.printDealerNotHitCardMent()
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
