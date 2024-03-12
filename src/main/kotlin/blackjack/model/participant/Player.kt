package blackjack.model.participant

import blackjack.model.deck.Deck
import blackjack.model.deck.HandCards

class Player(val name: String, deck: Deck) : GameParticipant(HandCards(deck))
