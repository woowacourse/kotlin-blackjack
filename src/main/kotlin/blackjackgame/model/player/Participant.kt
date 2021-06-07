package blackjackgame.model.player

import blackjackgame.model.card.Card
import blackjackgame.model.card.Cards

abstract class Participant(val name: String, val initialMoney: Int) {
    var finalMoney = 0
    val cards = Cards(mutableListOf())
    var isPlaying = true
        private set

    fun drawCard(card: Card) {
        cards.add(card)
    }

    fun drawCard(cards: List<Card>) {
        this.cards.addAll(cards)
    }

    fun isBurst(): Boolean {
        return cards.calculateScore() > BLACKJACK_SCORE
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

    fun calculateFinalScore(): Int {
        return cards.calculateFinalScore()
    }

    abstract fun getInitCards(): List<Card>

    abstract fun isPlayer(): Boolean

}