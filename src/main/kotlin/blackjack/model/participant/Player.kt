package blackjack.model.participant

class Player(val name: PlayerName, val battingAmount: BattingAmount) : Role() {
    override fun decideMoreCard() = !isBlackjack() && !isMaxScore() && !isBurst()
}
