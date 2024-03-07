package blackjack.model

class Player(val name: PlayerName) : Role() {
    override fun decideMoreCard() = !isBlackjack() && !isBurst()
}
