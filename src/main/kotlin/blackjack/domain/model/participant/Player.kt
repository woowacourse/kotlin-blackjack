package blackjack.domain.model.participant

import blackjack.domain.model.card.Card

class Player(
    name: String = DEFAULT_NAME,
) : GameParticipant(name = name) {
    override fun isDrawFinish(): Boolean = this.cardStatus == CardStatus.BUST

    constructor(name: String = DEFAULT_NAME, cards: List<Card>) : this(name) {
        cards.forEach { card -> this.handCards.addCard(card) }
    }

    companion object {
        private const val DEFAULT_NAME = "이름 없음"
    }
}
