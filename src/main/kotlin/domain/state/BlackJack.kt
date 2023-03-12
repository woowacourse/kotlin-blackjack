package domain.state

import domain.card.Hand

abstract class BlackJack(hand: Hand) : Finished(hand)
