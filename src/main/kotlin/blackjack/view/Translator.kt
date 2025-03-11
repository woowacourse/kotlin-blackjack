package blackjack.view

import blackjack.domain.model.card.Suit
import blackjack.domain.model.progress.WinLoss
import java.util.Locale

object Translator {
    fun suitLocalize(
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

    fun winLossLocalize(
        winLoss: WinLoss,
        locale: Locale,
    ): String =
        when (locale) {
            Locale.KOREAN ->
                when (winLoss) {
                    WinLoss.WIN -> "승"
                    WinLoss.DRAW -> "무"
                    WinLoss.LOSE -> "패"
                }

            else -> winLoss.name
        }
}
