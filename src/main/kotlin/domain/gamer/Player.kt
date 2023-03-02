package domain.gamer

import domain.card.Card

class Player(private val _card: MutableList<Card> = mutableListOf()) : Participant(_card) {}
