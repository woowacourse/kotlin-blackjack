package model

import model.Cards.Companion.PARTICIPANT_STANDARD_BUST_POINT
import model.Dealer.Companion.DEALER

abstract class Participant(val name: Name) {
    val cards = Cards(setOf())
    abstract fun getFirstOpenCards(): Cards
    abstract fun isPossibleDrawCard(): Boolean
    abstract fun isHit(needToDraw: (String) -> Boolean): Boolean
    fun isDealer(): Boolean = name.value == DEALER
    fun drawFirst(cardDeck: CardDeck) {
        cards.add(cardDeck.drawCard())
        cards.add(cardDeck.drawCard())
    }
    fun isBust(): Boolean {
        if (cards.sum() > PARTICIPANT_STANDARD_BUST_POINT) return true
        return false
    }
    fun getGameResult(other: Participant): Result {
        if (isDealer() && isBust() && !other.isBust()) return Result.LOSE
        if (!isDealer() && isBust()) return Result.LOSE
        if (other.isBust()) return Result.WIN
        if (cards.sum() > other.cards.sum()) return Result.WIN
        return Result.LOSE
    }
}
