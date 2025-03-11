package blackjack.domain.model

abstract class Participant {
    abstract val name: String
    protected abstract var hands: Hands
    protected abstract var record: Record

    abstract fun showInitCards(): List<Card>

    fun recordVerdict(verdictResult: VerdictResult) {
        record = record.progress(verdictResult)
    }

    fun recordVerdict(verdictResults: List<VerdictResult>) {
        record = record.progress(*verdictResults.toTypedArray())
    }

    fun getCurrentVerdict(): VerdictResult = record.lastVerdictResult()

    fun getRecord(): Map<VerdictResult, Int> =
        VerdictResult.entries.associateWith {
                verdictResult ->
            record.verdictResults.count { recordResult -> recordResult == verdictResult }
        }

    fun getScore() = hands.getScore()

    fun isStartCardCount() = hands.isStartCardCount()

    fun showCards(count: Int = hands.cards.count()): List<Card> = hands.extractCards(count)

    fun acceptCard(card: Card) {
        hands = hands.nextHand(card)
    }

    fun isBust(): Boolean = hands.isBust()
}
