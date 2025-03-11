package blackjack.model

interface EventListener {
    fun displayParticipantCards(name: String, cards: List<Card>)
    fun displayParticipantInfo(name: String, cards: List<Card>, score: Int, isBusted: Boolean)
    fun displayDealerDrawInfo(name: String, count: Int)
}
