package blackjackgame.model.player

import blackjackgame.model.card.Card
import blackjackgame.model.card.Cards
import blackjackgame.model.result.LOSE

import blackjackgame.model.result.Result
import blackjackgame.model.result.WIN

const val BLACKJACK_SCORE = 21

open class Player(val name: String = "any", val initialMoney: Int = 0) {
    var finalMoney: Int = 0
    val cards = Cards(mutableListOf())
    var isPlaying = true
        private set

    fun drawCard(card: Card) {
        cards.add(card)
    }

    fun drawCard(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    open fun getInitCards(): List<Card> {
        return this.cards.subList(0, 2)
    }

    fun isBurst(): Boolean {
        return cards.calculateScore() > BLACKJACK_SCORE
    }

    fun isBlackjack(): Boolean {
        return cards.isInitSize() && cards.calculateScore() == BLACKJACK_SCORE
    }

    fun isHit(): Boolean {
        return cards.calculateScore() < BLACKJACK_SCORE
    }

    fun canDraw(): Boolean {
        return cards.calculateScore() < BLACKJACK_SCORE
    }

    fun endTurn() {
        isPlaying = false
    }

    open fun isPlayer(): Boolean {
        return true
    }

    fun calculateFinalScore(): Int {
        return cards.calculateFinalScore()
    }

    fun earnMoney(result: Result) {
        if (isBlackjack() && result == WIN) {
            finalMoney = (initialMoney * 1.5).toInt()
            return
        }
        if (result == LOSE) {
            finalMoney = 0
            return
        }
        finalMoney = (initialMoney * 1.0).toInt()
    }

}
