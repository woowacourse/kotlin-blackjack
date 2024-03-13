package blackjack.model

class Player(name: String) : Participant(name, PlayerStrengthPolicy()) {
    override fun isHitable(): Boolean = !isBusted()
}
