package domain.gamer

import domain.gamer.cards.Cards

class Player(val name: String, override val cards: Cards) : Participant(cards)
