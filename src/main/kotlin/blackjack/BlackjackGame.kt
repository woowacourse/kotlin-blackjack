package blackjack

import blackjack.domain.card.MultiDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Participants
import blackjack.domain.player.Player

class BlackjackGame(
    private val deck: MultiDeck = MultiDeck(),
    val dealer: Dealer = Dealer(),
    val participants: Participants
) {

    fun setFirstTurnPlayersCards(
        printFirstTurnSettingCards: (Dealer, Participants) -> Unit,
    ) {
        dealer.setFirstTurnCards(deck)
        participants.values.forEach { it.setFirstTurnCards(deck) }
        printFirstTurnSettingCards(dealer, participants)
    }

    fun hitPlayersCards(
        readHitOrNot: (String) -> Boolean,
        printPlayerCards: (Player, String) -> Unit,
        printDealerHitOrNotMessage: (Boolean) -> Unit
    ) {
        hitParticipantsCards(readHitOrNot, printPlayerCards)
        hitDealerCard(printDealerHitOrNotMessage)
    }

    fun decidePlayersResult() {
        participants.values.forEach { it.decideGameResult(dealer) }
        participants.values.forEach { dealer.decideGameResult(it) }
        dealer.matchResult.reversGameResult()
    }

    private fun hitParticipantsCards(
        readHitOrNot: (String) -> Boolean,
        printPlayerCards: (Player, String) -> Unit
    ) {
        participants.values.forEach {
            hitParticipantCards(it, readHitOrNot, printPlayerCards)
        }
    }

    private fun hitParticipantCards(
        participant: Participant,
        readHitOrNot: (String) -> Boolean,
        printPlayerCards: (Player, String) -> Unit
    ) {
        while (participant.canHit() && readHitOrNot(participant.name)) {
            participant.addCard(deck.draw())
            printPlayerCards(participant, "")
        }
    }

    private fun hitDealerCard(
        printDealerHitOrNotMessage: (Boolean) -> Unit
    ) {
        while (dealer.canHit()) {
            printDealerHitOrNotMessage(dealer.canHit())
            dealer.addCard(deck.draw())
        }
        printDealerHitOrNotMessage(false)
    }
}
