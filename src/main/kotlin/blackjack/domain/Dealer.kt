package blackjack.domain

class Dealer(name: String = "딜러") : Participant(name) {
    fun shouldHit(): Boolean = cards.size == 2 && getScore() <= 16
}
