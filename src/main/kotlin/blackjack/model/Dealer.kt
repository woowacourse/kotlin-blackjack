package blackjack.model

class Dealer(val nickname: Nickname = Nickname(DEFAULT_DEALER_NAME)) : CardHolder() {
    fun shouldDrawCard(): Boolean = hand.calculate() <= DEALER_CARD_DRAW_THRESHOLD

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        const val DEALER_CARD_DRAW_THRESHOLD = 16
    }
}
