package blackjack.domain.player

import blackjack.domain.Result
import blackjack.domain.card.Card
import blackjack.domain.card.Deck

class Dealer(name: String = "딜러") : Player(name) {

    private val deck: Deck = Deck()
    val results: MutableMap<Result, Int> = Result.values().associateWith { 0 }.toMutableMap()

    fun decidePlayerResult(participants: Participants) {
        decideParticipantsResult(participants)
        decideDealerResult(participants)
    }

    fun decideParticipantsResult(participants: Participants) {
        participants.values.forEach {
            it.updateResult(cards.sumCardsNumber())
        }
    }

    fun decideDealerResult(participants: Participants) {
        participants.values.forEach {
            val dealerResult = Result.reverse(it.result)
            results[dealerResult] = results[dealerResult]?.plus(1) ?: throw IllegalArgumentException()
        }
    }

    fun setInitialPlayersCards(participants: Participants) {
        repeat(CARD_SETTING_COUNT) { addCard(deck.draw()) }
        participants.values.forEach { setInitialParticipantCards(it) }
    }

    fun drawCard(): Card = deck.draw()

    fun isDrawable(): Boolean = cards.sumCardsNumber() <= MIN_SUM_NUMBER

    private fun setInitialParticipantCards(participant: Participant) =
        repeat(CARD_SETTING_COUNT) { participant.addCard(deck.draw()) }

    companion object {
        const val MIN_SUM_NUMBER = 16
        private const val CARD_SETTING_COUNT = 2
    }
}
