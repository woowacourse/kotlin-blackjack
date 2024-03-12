package blackjack.model.card

const val JACK_MARK = "J"
const val QUEEN_MARK = "Q"
const val KING_MARK = "K"
const val ACE_MARK = "A"

enum class Denomination(val score: Int) {
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
    ACE(11), ;

    fun convertCardDenomination(): String {
        return when (this) {
            JACK -> JACK_MARK
            QUEEN -> QUEEN_MARK
            KING -> KING_MARK
            ACE -> ACE_MARK
            else -> this.score.toString()
        }
    }
}
