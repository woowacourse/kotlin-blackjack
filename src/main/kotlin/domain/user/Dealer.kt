package domain.user

import domain.card.Cards
import domain.card.TrumpCard
import domain.status.*

class Dealer(override val name: String, override val hand: Cards = Cards(mutableListOf())) :
    User {
    override var status: Status = Hit()
        private set


    fun receiveCard(trumpCard: TrumpCard) {
        this.hand.receiveCard(trumpCard)
    }

    override fun changeStatus() {
        if (score() == 21 && hand.size() == 2) {
            this.status = Blackjack()
        }
        if (score() > 21) {
            this.status = Bust()
        }
    }

    override fun draw(cards: Cards) {
        hand.receiveCard(cards)
        changeStatus()
    }

    override fun stayGame() {
        this.status = Stay()
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
