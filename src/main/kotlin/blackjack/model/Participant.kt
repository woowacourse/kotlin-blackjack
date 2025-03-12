package blackjack.model

interface Participant {

    val hand: Hand

    val money: Amount

    fun draw(cardDeck: CardDeck) {
        hand.add(cardDeck.draw())
    }

    fun getHandSize(): Int = hand.getHandCount()

    fun getScore(): Int = hand.score()

    fun isBusted(): Boolean = hand.isBust()

    fun settleBlackjack(amount: Amount=Amount(0.0))

    fun settleWin(amount: Amount = Amount(0.0))

    fun settlePush(amount: Amount = Amount(0.0))

    fun settleLose(amount: Amount = Amount(0.0))

}
