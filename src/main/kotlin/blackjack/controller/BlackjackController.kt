package blackjack.controller

import blackjack.domain.card.MultiDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView()
) {

    fun run() {
        val deck: MultiDeck = MultiDeck()
        val dealer: Dealer = Dealer()
        val participants = inputView.readParticipants()

        participants.bettingParticipants(inputView::readParticipantBattingAmount)
        setFirstTurnPlayersCards(dealer, participants, deck)
        hitPlayersCards(dealer, participants, deck)
        decidePlayersResult(dealer, participants)
        printGameResult(dealer, participants)
    }

    private fun setFirstTurnPlayersCards(dealer: Dealer, participants: Participants, deck: MultiDeck) {
        dealer.setFirstTurnCards(deck)
        participants.values.forEach { it.setFirstTurnCards(deck) }
        outputView.printFirstTurnSettingCards(dealer, participants)
    }

    private fun hitPlayersCards(dealer: Dealer, participants: Participants, deck: MultiDeck) {
        participants.values.forEach { participant ->
            while (participant.canHit() && inputView.readHitOrNot(participant.name)) {
                participant.addCard(deck.draw())
                outputView.printPlayerCards(participant, "")
            }
        }

        while (dealer.canHit()) {
            outputView.printDealerHitOrNotMessage(dealer.canHit())
            dealer.addCard(deck.draw())
        }
    }

    private fun decidePlayersResult(dealer: Dealer, participants: Participants) {
        participants.values.forEach { it.decideGameResult(dealer) }
        participants.values.forEach { dealer.decideGameResult(it) }
        dealer.matchResult.reversGameResult()
    }

    private fun printGameResult(dealer: Dealer, participants: Participants) {
        outputView.printSumResult(dealer, participants)
        outputView.printPlayersResults(dealer, participants)
    }
}
