package blackjack.model

fun buildCard(cardNumber: CardNumber): Card {
    return Card(cardNumber, Suit.Heart)
}

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

fun buildDealer(vararg card: Card): Dealer {
    val dealer = Dealer()
    val cardList = listOf(*card)
    cardList.forEach {
        dealer.addCard(it)
    }
    return dealer
}

val ace = buildCard(CardNumber.Ace)
val two = buildCard(CardNumber.Two)
val three = buildCard(CardNumber.Three)
val four = buildCard(CardNumber.Four)
val five = buildCard(CardNumber.Five)
val six = buildCard(CardNumber.Six)
val seven = buildCard(CardNumber.Seven)
val eight = buildCard(CardNumber.Eight)
val nine = buildCard(CardNumber.Nine)
val ten = buildCard(CardNumber.Ten)
