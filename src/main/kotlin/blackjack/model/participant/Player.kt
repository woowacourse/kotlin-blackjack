package blackjack.model.participant

class Player(val name: PlayerName, val battingAmount: Int = 0) : Role() {
    override fun decideMoreCard() = !isBlackjack() && !isMaxScore() && !isBurst()
}
