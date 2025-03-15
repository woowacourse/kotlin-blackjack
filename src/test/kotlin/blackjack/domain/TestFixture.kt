package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit

object TestFixture {
    val ClubAce = Card(Suit.CLUB, Denomination.ACE)
    val ClubTwo = Card(Suit.CLUB, Denomination.TWO)
    val ClubThree = Card(Suit.CLUB, Denomination.THREE)
    val ClubFour = Card(Suit.CLUB, Denomination.FOUR)
    val ClubFive = Card(Suit.CLUB, Denomination.FIVE)
    val ClubSix = Card(Suit.CLUB, Denomination.SIX)
    val ClubSeven = Card(Suit.CLUB, Denomination.SEVEN)
    val ClubEight = Card(Suit.CLUB, Denomination.EIGHT)
    val ClubNine = Card(Suit.CLUB, Denomination.NINE)
    val ClubTen = Card(Suit.CLUB, Denomination.TEN)
    val ClubJack = Card(Suit.CLUB, Denomination.JACK)
    val ClubQueen = Card(Suit.CLUB, Denomination.QUEEN)
    val ClubKing = Card(Suit.CLUB, Denomination.KING)
}