package blackjack.domain.model

abstract class Participant {
    abstract val name: String
    protected abstract var hands: Hands

    abstract fun showInitCards(): List<Card>

    abstract fun getHandsState(): HandState

    fun getScore() = hands.getScore()

    fun isStartCardCount() = hands.isStartCardCount()

    fun showCards(count: Int = hands.cards.count()): List<Card> = hands.extractCards(count)

    fun acceptCard(card: Card) {
        hands = hands.nextHand(card)
    }

    fun match(otherParticipant: Participant): MatchResult {
        val handState = getHandsState()
        val otherHandState = otherParticipant.getHandsState()
        val score = getScore()
        val otherScore = otherParticipant.getScore()
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
