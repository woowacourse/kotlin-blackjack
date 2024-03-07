package blackjack.model

class Dealer(name: String = DEFAULT_DEALER_NAME) : Participant(name) {

    fun openFirstCard(): Card {
        return getCards().firstOrNull() ?: throw IllegalArgumentException(ERROR_CARD_INDEX)
    }

    fun checkDealerScoreCondition(): Boolean {
        return getBlackJackScore() <= MIN_HAND_CARD_SCORE
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        private const val ERROR_CARD_INDEX = "가지고 있는 카드가 없습니다."
        private const val MIN_HAND_CARD_SCORE: Int = 16
    }
}
