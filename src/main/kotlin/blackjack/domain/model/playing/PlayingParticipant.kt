package blackjack.domain.model.playing

import blackjack.domain.model.Card
import blackjack.domain.model.HandState
import blackjack.domain.model.Hands
import blackjack.domain.model.MatchResult

abstract class PlayingParticipant {
    abstract val name: String
    protected abstract var hands: Hands

    abstract fun getHandsState(): HandState

    fun getScore() = hands.getScore()

    fun isStartCardCount() = hands.isStartCardCount()

    fun showCards(count: Int = hands.cards.count()): List<Card> = hands.extractCards(count)

    fun acceptCard(card: Card) {
        hands = hands.nextHand(card)
    }

    fun match(otherPlayingParticipant: PlayingParticipant): MatchResult {
        val handState = getHandsState()
        val otherHandState = otherPlayingParticipant.getHandsState()
        val score = getScore()
        val otherScore = otherPlayingParticipant.getScore()
        return when {
            handState == HandState.BLACKJACK && otherHandState == HandState.HIT -> MatchResult.DRAW
            handState == HandState.BLACKJACK -> MatchResult.BLACKJACK
            handState == HandState.BUST -> MatchResult.LOSE
            score > otherScore -> MatchResult.WIN
            score < otherScore -> MatchResult.LOSE
            else -> MatchResult.DRAW
        }
    }
}
