package blackjack.model

class Dealer : Role() {
    val result = DealerResult()

    fun decideMoreCard() = getCardSum() < MIN_CARD_SUM

    companion object {
        private const val MIN_CARD_SUM = 17
    }
}
