package blackjack.domain.participants

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class User(name: String, money: Int) {
    val userInfo = UserInfo(name, money)
    var cards = Cards()

    val name: String
        get() = userInfo.name.toString()

    fun getScore(): Int = cards.result.score()

    fun isBlackJack(): Boolean = cards.result.score() == BLACKJACK_NUMBER

    fun isBlackJackSize(): Boolean = cards.size == 2

    abstract val isContinue: Boolean

    fun draw(card: Card) {
        cards += card
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
