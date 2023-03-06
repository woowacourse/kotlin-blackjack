package blackjack.domain

import blackjack.dto.HandDTO
import blackjack.dto.ScoreDTO

abstract class Participant(val name: String) {
    val hand = ParticipantHand()

    abstract fun canDraw(): Boolean

    fun addCard(card: Card) {
        hand.add(card)
    }

    fun getTotalScore(): Int = hand.calculateTotalScore()

    fun getHand(): HandDTO = HandDTO(name, hand.cards.map(Card::toString))

    fun getScore(): ScoreDTO = ScoreDTO(getHand(), getTotalScore())
}
