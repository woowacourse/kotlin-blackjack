package model

abstract class Participant(val cards: Cards, val name: Name) {
    abstract fun getFirstOpenCards(): Cards
    abstract fun isHit(): Boolean
    abstract fun getProfitMoney(other: Participant): Profit
    abstract fun getResult(participants: Participants): Result
    abstract fun isDealer(): Boolean
    fun isBust(): Boolean = cards.isBust()
    fun isBlackJack(): Boolean = cards.isBlackJack()
    fun drawFirst(cardDeck: CardDeck) {
        cards.add(cardDeck.drawCard())
        cards.add(cardDeck.drawCard())
    }
    fun drawCard(cardDeck: CardDeck) = cards.add(cardDeck.drawCard())
}
