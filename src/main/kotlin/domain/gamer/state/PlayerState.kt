package domain.gamer.state

import domain.card.Card
import domain.judge.Referee

class PlayerState(private val _cards: MutableList<Card> = mutableListOf()) : ParticipantState(_cards) {
    fun checkBurst(): Boolean = calculateCardSum() > Referee.CARD_SUM_MAX_VALUE
}
