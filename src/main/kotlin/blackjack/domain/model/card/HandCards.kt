package blackjack.domain.model.card

import blackjack.domain.model.card.Number.ACE
import blackjack.domain.model.participant.WinLoss

class HandCards(
    initCards: List<Card> = emptyList(),
) {
    private val _cards: MutableList<Card> = initCards.toMutableList()

    val cards: List<Card>
        get() = this._cards.toList()

    val status: CardStatus
        get() = CardStatus.calculateCardsStatus(this._cards)

    val bestCardValue: Int
        get() {
            val cardNumbers = this._cards.map { it.number }
            val minimumSum = minimumCardValuesSum

            if (ACE in cardNumbers && (minimumSum + ACE_VALUE_GAP) <= CardStatus.BLACKJACK_NUMBER) {
                return minimumSum + ACE_VALUE_GAP
            }
            return minimumSum
        }

    private val minimumCardValuesSum: Int
        get() {
            val cardValues = _cards.map { it.minimumValue }
            return cardValues.sum()
        }

    fun retrieveCard(index: Int): Card = this._cards[index]

    fun cardAdd(card: Card) {
        this._cards += card
    }

    fun versusRivalWinLoss(
        rivalStatus: CardStatus,
        rivalBestValue: Int,
    ): WinLoss =
        when {
            (status == CardStatus.BLACKJACK) && (rivalStatus != CardStatus.BLACKJACK) -> WinLoss.WIN
            status == CardStatus.BUST -> WinLoss.LOSE
            (rivalStatus != CardStatus.BUST) && (rivalBestValue > bestCardValue) -> WinLoss.LOSE
            (rivalStatus == CardStatus.BLACKJACK) && (status != CardStatus.BLACKJACK) -> WinLoss.LOSE
            (rivalBestValue == bestCardValue) -> WinLoss.DRAW
            else -> WinLoss.WIN
        }

    companion object {
        private const val ACE_VALUE_GAP = 10
        const val INIT_CARD_SIZE = 2
    }
}
