package domain.gamer.cards

import domain.card.Card
import domain.judge.Referee

class PlayerCards(private val _cards: MutableList<Card> = mutableListOf()) : ParticipantCards(_cards) {
    override fun checkOverCondition(): Boolean = calculateCardSum() > Referee.CARD_SUM_MAX_VALUE
}
