package blackjack.model

fun createPlayerWithCards(
    money: Int,
    vararg cards: Card,
): Player {
    val player = Player(Wallet(Identification("누누"), money))
    cards.forEach { card ->
        player.draw(card)
    }
    return player
}

fun createDealerWithCards(vararg cards: Card): Dealer {
    val dealer = Dealer()
    cards.forEach { card ->
        dealer.draw(card)
    }
    return dealer
}
