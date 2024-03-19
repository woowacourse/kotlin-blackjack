package blackjack.model

fun Card(cardNumber: CardNumber): Card = Card(cardNumber, Suit.Heart)

fun buildCards(vararg card: Card): Cards {
    val cards = Cards()
    card.forEach {
        cards.addCard(it)
    }
    return cards
}

fun buildPlayer(
    name: String,
    vararg card: Card,
): Player {
    val player = Player(name)
    val cardList = listOf(*card)
    cardList.forEach {
        player.addCard(it)
    }
    return player
}

fun buildPlayer(
    name: String,
    stake: Double,
    vararg card: Card,
): Player {
    val player = Player(name, Money(stake))
    val cardList = listOf(*card)
    cardList.forEach {
        player.addCard(it)
    }
    return player
}

fun buildDeck(vararg cardNumber: CardNumber) = Deck(cardNumber.map { Card(it) })

fun buildDealer(vararg card: Card): Dealer {
    val dealer = Dealer()
    val cardList = listOf(*card)
    cardList.forEach {
        dealer.addCard(it)
    }
    return dealer
}

val ace = Card(CardNumber.Ace)
val two = Card(CardNumber.Two)
val three = Card(CardNumber.Three)
val four = Card(CardNumber.Four)
val five = Card(CardNumber.Five)
val six = Card(CardNumber.Six)
val seven = Card(CardNumber.Seven)
val eight = Card(CardNumber.Eight)
val nine = Card(CardNumber.Nine)
val ten = Card(CardNumber.Ten)
