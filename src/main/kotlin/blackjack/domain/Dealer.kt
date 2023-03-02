package blackjack.domain

class Dealer(override val cardBunch: CardBunch) : Participant {
    fun isOverCondition(): Boolean = cardBunch.getTotalScore() > ADD_CARD_CONDITION

    fun compareScore(player: Player) {
        player.chooseWinner(cardBunch.getTotalScore())
    }

    companion object {
        private const val ADD_CARD_CONDITION = 16
    }
}
