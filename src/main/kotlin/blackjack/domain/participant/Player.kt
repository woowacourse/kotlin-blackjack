package blackjack.domain.participant

import blackjack.domain.TrumpCard

class Player(
    val name: String,
) : Participant {
    private val _cards = mutableListOf<TrumpCard>()
    override val cards: List<TrumpCard> get() = _cards.toList()

    override fun getInitializedCards(
        initializedCardCount: Int,
        card: TrumpCard,
    ) {
        repeat(initializedCardCount) {
            addCard(card)
        }
    }

    override fun addCard(card: TrumpCard) {
        _cards.add(card)
    }
}
