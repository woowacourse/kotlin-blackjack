package blackjack.domain.player

import blackjack.domain.card.Cards

class Dealer(name: String = "딜러", cards: Cards = Cards()) : Player(name, cards) {

    override fun checkProvideCardPossible(): Boolean = (cards.calculateScore() <= PARTICIPANT_MORE_CARD_CRITERIA)

    companion object {
        const val PARTICIPANT_MORE_CARD_CRITERIA = 16
    }
}
