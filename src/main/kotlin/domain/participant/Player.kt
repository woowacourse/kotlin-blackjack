package domain.participant

import domain.Money

class Player(name: String = "", hand: Hand = Hand(), money: Money = Money()) : Participant(name, hand, money) {
    override fun isDrawable(): Boolean {
        return !hand.isBust()
    }
}
