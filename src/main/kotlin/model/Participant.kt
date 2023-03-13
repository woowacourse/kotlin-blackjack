package model

abstract class Participant(val name: Name) {
    val cards = Cards(setOf())
    abstract fun getFirstOpenCards(): Cards
    abstract fun isPossibleDrawCard(): Boolean
    abstract fun isHit(needToDraw: (String) -> Boolean): Boolean
    abstract fun getProfitMoney(other: Participant): Profit
    abstract fun isDealer(): Boolean
    fun isBust(): Boolean = cards.isBust()
    fun isBlackJack(): Boolean = cards.isBlackJack()
    fun drawFirst(cardDeck: CardDeck) {
        cards.add(cardDeck.drawCard())
        cards.add(cardDeck.drawCard())
    }
    fun drawCard(cardDeck: CardDeck) = cards.add(cardDeck.drawCard())
}
