package blackjack.domain

class Player(name: String): Participant(name) {
    var bettingAmount = 0
        private set

    fun bet(bettingAmount: Int) {
        this.bettingAmount = bettingAmount
    }
}