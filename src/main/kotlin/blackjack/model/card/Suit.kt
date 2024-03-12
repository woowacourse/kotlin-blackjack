package blackjack.model.card

const val CLOVER = "클로버"
const val DIAMOND = "다이아몬드"
const val HEART = "하트"
const val SPADE = "스페이드"

enum class Suit {
    CLUBS,
    DIAMONDS,
    HEARTS,
    SPADES, ;

    fun convertToCardSuit(): String {
        return when (this) {
            CLUBS -> CLOVER
            DIAMONDS -> DIAMOND
            HEARTS -> HEART
            SPADES -> SPADE
        }
    }
}
