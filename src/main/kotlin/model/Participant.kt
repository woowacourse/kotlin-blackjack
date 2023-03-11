package model

abstract class Participant(val name: Name) {
    val cards = Cards(setOf())
    abstract fun getFirstOpenCards(): Cards
    abstract fun isPossibleDrawCard(): Boolean
    abstract fun isHit(needToDraw: (String) -> Boolean): Boolean
    fun isBust(): Boolean = cards.isBust()
    fun isBlackJack(): Boolean = cards.isBlackJack()
    fun isDealer(): Boolean = this is Dealer
    fun drawFirst(cardDeck: CardDeck) {
        cards.add(cardDeck.drawCard())
        cards.add(cardDeck.drawCard())
    }
    fun drawCard(cardDeck: CardDeck) = cards.add(cardDeck.drawCard())
    fun getGameResult(other: Participant): Result {
        if (isBlackJack() && other.isBlackJack()) return Result.DRAW
        if (isBlackJack()) return Result.BLACKJACK
        if (isDealer() && isBust() && !other.isBust()) return Result.LOSE
        if (!isDealer() && isBust()) return Result.LOSE
        if (other.isBust()) return Result.WIN
        if (cards.sum() > other.cards.sum()) return Result.WIN
        if (cards.sum() == other.cards.sum()) return Result.DRAW
        return Result.LOSE
    }
}
