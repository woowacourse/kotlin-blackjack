package blackjack.domain

import blackjack.dto.HandDTO

open class Player(val name: String) {
    val hand = PlayerHand()

    fun addCard(card: Card) {
        hand.add(card)
    }

    fun isBust(): Boolean = hand.calculateTotalScore() > GameResult.blackjackScore()

    fun getTotalScore(): Int = hand.calculateTotalScore()

    fun getHand(): HandDTO = HandDTO(name, hand.cards.map(Card::toString))
}
