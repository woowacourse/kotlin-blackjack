package domain.player

import domain.gamer.cards.ParticipantCards

data class Player(val name: String, val ownCards: ParticipantCards)
