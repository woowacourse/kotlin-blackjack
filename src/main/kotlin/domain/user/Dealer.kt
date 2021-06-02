package domain.user

import domain.card.Cards
import domain.card.TrumpCard
import domain.status.Blackjack
import domain.status.Bust
import domain.status.Hit
import domain.status.Status

class Dealer(override val name: String, override val hand: Cards = Cards(mutableListOf())) :
    User {
    var status: Status = Hit()
        private set


    fun receiveCard(trumpCard: TrumpCard) {
        this.hand.receiveCard(trumpCard)
    }

    override fun changeStatus() {
        if (getScore() == 21 && hand.size() == 2) {
            this.status = Blackjack()
        }
        if (getScore() > 21) {
            this.status = Bust()
        }
    }

    fun getScore(): Int {
        return this.hand.getTotalScore()
    }

    override fun draw(cards: Cards) {
        hand.receiveCard(cards)
    }

    override fun score(): Int {
        return this.hand.getTotalScore()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Gamer) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }

}
