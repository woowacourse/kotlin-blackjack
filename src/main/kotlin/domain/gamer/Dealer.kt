package domain.gamer

import domain.card.Card

class Dealer(private val mutableList: MutableList<Card> = mutableListOf()) : Participant(mutableList) {
}
