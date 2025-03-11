package blackjack.view

import blackjack.domain.model.card.Suit
import java.util.Locale

object SuitTranslator {
    fun localize(
        suit: Suit,
        locale: Locale,
    ): String =
        when (locale) {
            Locale.KOREAN ->
                when (suit) {
                    Suit.SPADE -> "스페이드"
                    Suit.HART -> "하트"
                    Suit.DIAMOND -> "다이아몬드"
                    Suit.CLOVER -> "클로버"
                }

            else -> suit.name
        }
}
