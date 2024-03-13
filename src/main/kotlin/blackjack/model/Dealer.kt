package blackjack.model

class Dealer(name: String = DEFAULT_DEALER_NAME) : Participant(name) {
    fun openFirstCard(): Card {
        return getCards().firstOrNull() ?: throw IllegalArgumentException(ERROR_CARD_INDEX)
    }

    fun drawAdditionalDraw(
        deck: CardDeck,
        outputAction: (() -> Unit),
    ) {
        while (checkDealerScoreCondition()) {
            draw(deck.draw())
            outputAction()
        }
    }

    fun checkDealerScoreCondition(): Boolean {
        return getBlackJackScore() <= MIN_HAND_CARD_SCORE
    }

    fun calculateDealerResult(result: Result): Result {
        return when (result) {
            Result.WIN -> Result.LOSE
            Result.LOSE -> Result.WIN
            else -> Result.DRAW
        }
    }

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        private const val ERROR_CARD_INDEX = "가지고 있는 카드가 없습니다."
        const val MIN_HAND_CARD_SCORE: Int = 16
    }
}
