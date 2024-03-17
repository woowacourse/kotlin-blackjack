package blackjack.model

class Dealer(userInfo: UserInfo = DEFAULT_USER_INFO) : CardHolder(userInfo = userInfo) {
    fun shouldDrawCard(): Boolean = calculateHand() <= DEALER_CARD_DRAW_THRESHOLD

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        private val DEFAULT_USER_INFO = UserInfo(nickname = Nickname(DEFAULT_DEALER_NAME))

        const val DEALER_CARD_DRAW_THRESHOLD = 16
    }
}
