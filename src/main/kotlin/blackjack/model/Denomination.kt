package blackjack.model

enum class Denomination(
    private val score: Int
) {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    fun getScore(): Int = score

    companion object {
        fun aceTransferScore(): Int{
            return ACE.score - BONUS_SCORE
        }

        private const val BONUS_SCORE :Int = 10
    }
}
