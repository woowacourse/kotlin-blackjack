package blackjack.controller

import blackjack.domain.card.MultiDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    private val multiDeck: MultiDeck = MultiDeck()

    fun run() {
        val dealer: Dealer = Dealer()
        val participants: Participants = inputView.readParticipants()
        setFirstTurnPlayersCards(dealer, participants)
        hitPlayerCards(dealer, participants)
        decidePlayersResult(dealer, participants)
        printResult(dealer, participants)
    }

    private fun decidePlayersResult(dealer: Dealer, participants: Participants) {
        participants.values.forEach { it.decideGameResult(dealer) }
        participants.values.forEach { dealer.decideGameResult(it) }
    }

    private fun setFirstTurnPlayersCards(dealer: Dealer, participants: Participants) {
        dealer.setFirstTurnCards(multiDeck)
        participants.values.forEach { it.setFirstTurnCards(multiDeck) }
        outputView.printFirstTurnSettingCard(dealer, participants)
    }

    private fun hitPlayerCards(dealer: Dealer, participants: Participants) {
        hitParticipantsCards(dealer, participants)
        hitDealerCard(dealer)
    }

    private fun hitParticipantsCards(dealer: Dealer, participants: Participants) {
        participants.values.forEach {
            hitParticipantCards(it)
        }
    }

    private fun hitParticipantCards(participant: Participant) {
        while (participant.canHit() && inputView.readHitOrNot(participant.name)) {
            participant.addCard(multiDeck.draw())
            outputView.printCurrentPlayerCards(participant)
        }
    }

    private fun hitDealerCard(dealer: Dealer) {
        while (dealer.canHit()) {
            dealer.addCard(multiDeck.draw())
            outputView.printDealerHitMessage()
            return
        }
        outputView.printDealerNotHitMessage()
    }

    private fun printResult(dealer: Dealer, participants: Participants) {
        outputView.printSumResult(dealer, participants)
        outputView.printPlayersResults(dealer, participants)
    }
}
