package domain.card

import domain.EmptyCardException


class Cards(val cards: MutableList<TrumpCard>) {
    companion object {
        fun createAllCards(): Cards {
            val deck = ArrayList<TrumpCard>()
            for (trumpCardPattern: TrumpCardPattern in TrumpCardPattern.values()) {
                for (trumpCardNumber: TrumpCardNumber in TrumpCardNumber.values()) {
                    deck.add(TrumpCard(trumpCardNumber, trumpCardPattern))
                }
            }
            return Cards(deck)
        }
    }

    fun size(): Int {
        return cards.size
    }

    fun getTotalScore(): Int {
        val defaultScore = cards.map { it.getScore() }.sum()
        if (canPlusScore(defaultScore)) {
            return defaultScore + 10
        }
        return defaultScore
    }

    private fun hasAce(): Boolean {
        return cards.any { it.trumpCardNumber == TrumpCardNumber.ACE }
    }

    private fun canPlusScore(defaultScore: Int): Boolean {
        return hasAce() && defaultScore + 10 <= 21
    }

    fun dealCard(): TrumpCard {
        if (this.cards.isEmpty()) {
            throw EmptyCardException()
        }

        val last = this.cards.last()
        this.cards.removeLast()
        return last
    }

    fun receiveCard(trumpCard: TrumpCard) {
        this.cards.add(trumpCard)
    }

    fun receiveCard(cards: Cards) {
        this.cards.add(cards.dealCard())
    }
}
