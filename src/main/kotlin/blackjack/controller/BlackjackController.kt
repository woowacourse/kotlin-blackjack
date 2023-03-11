package blackjack.controller

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.MultiDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.domain.player.Player
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
        setInitialPlayersCards(dealer, participants)
        hitPlayerCards(dealer, participants)
        decidePlayersResult(dealer, participants)
        printResult(dealer, participants)
    }

    private fun decidePlayersResult(dealer: Dealer, participants: Participants) {
        participants.values.forEach { it.decideGameResult(dealer) }
        participants.values.forEach { dealer.decideGameResult(it) }
    }

    private fun setInitialPlayersCards(dealer: Dealer, participants: Participants) {
        repeat(Player.CARD_SETTING_COUNT) { dealer.addCard(multiDeck.draw()) }
        participants.values.forEach {
            val initCards: List<Card> = listOf(multiDeck.draw(), multiDeck.draw())
            it.setInitialCards(Cards(initCards))
        }
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
        while (participant.canHit() && inputView.readHitOrNot(participant.name)) {
            participant.addCard(multiDeck.draw())
            outputView.printCurrentPlayerCards(participant)
        }
    }

    private fun hitDealerCard(dealer: Dealer) {
        if (dealer.canHit()) {
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
