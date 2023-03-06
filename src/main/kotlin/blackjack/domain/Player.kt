package blackjack.domain

import blackjack.domain.BlackJack.Companion.blackjackScore
import blackjack.dto.HandDTO
import blackjack.dto.ScoreDTO

open class Player(val name: String) {
    val hand = PlayerHand()

    fun addCard(card: Card) {
        hand.add(card)
    }

    fun isBust(): Boolean = hand.calculateTotalScore() > blackjackScore()

    fun getTotalScore(): Int = hand.calculateTotalScore()

    fun getHand(): HandDTO = HandDTO(name, hand.cards.map(Card::toString))

    fun getScore(): ScoreDTO = ScoreDTO(getHand(), getTotalScore())
}
