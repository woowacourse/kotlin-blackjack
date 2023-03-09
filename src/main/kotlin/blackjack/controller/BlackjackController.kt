package blackjack.controller

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck
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

    private val deck: Deck = Deck()

    fun run() {
        val dealer: Dealer = Dealer()
        val participants: Participants = readParticipants()
        setInitialPlayersCards(dealer, participants)
        hitPlayerCards(dealer, participants)
        dealer.decidePlayersResult(participants)
        printResult(dealer, participants)
    }

    private fun setInitialPlayersCards(dealer: Dealer, participants: Participants) {
        repeat(Player.CARD_SETTING_COUNT) { dealer.addCard(deck.draw()) }
        participants.values.forEach {
            val initCards: List<Card> = listOf(deck.draw(), deck.draw())
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
        while (participant.canHit() && readHitOrNot(participant.name)) {
            participant.addCard(deck.draw())
            outputView.printCurrentPlayerCards(participant)
        }
    }

    private fun hitDealerCard(dealer: Dealer) {
        if (dealer.canHit()) {
            dealer.addCard(deck.draw())
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
