package blackjack.model.participant

class Player(val name: PlayerName) : Role() {
    override fun decideMoreCard() = !isBlackjack() && !isMaxScore() && !isBurst()
}
