package domain

import domain.card.Card
import domain.card.Symbol
import domain.card.Value
import domain.participant.Dealer
import domain.participant.Hand
import domain.participant.Player

val blackJackHand = Hand(mutableListOf(Card(Symbol.CLOVER, Value.ACE), Card(Symbol.CLOVER, Value.KING)))
val bustHand = Hand(mutableListOf(Card(Symbol.CLOVER, Value.JACK), Card(Symbol.CLOVER, Value.KING), Card(Symbol.CLOVER, Value.QUEEN)))
val valueTwentyHand = Hand(mutableListOf(Card(Symbol.CLOVER, Value.KING), Card(Symbol.CLOVER, Value.QUEEN)))
val valueTenHand = Hand(mutableListOf(Card(Symbol.CLOVER, Value.KING)))
val blackJackPlayer = Player(hand = blackJackHand, money = Money(1000))
val bustPlayer = Player(hand = bustHand, money = Money(1000))
val scoreTwentyPlayer = Player(hand =valueTwentyHand, money = Money(1000))
val scoreTenPlayer = Player(hand =valueTenHand, money = Money(1000))
val blackJackDealer = Dealer(hand = blackJackHand)
val bustDealer = Dealer(hand = bustHand)
val scoreTwentyDealer = Dealer(hand =valueTwentyHand)
val scoreTenDealer = Dealer(hand =valueTenHand)