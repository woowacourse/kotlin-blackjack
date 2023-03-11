package domain

import domain.card.Card

class BlackJackGameData(
    private val deck: Deck,
    val players: Players
) {
    val dealer get() = players.dealer
    val users get() = players.users

    fun getOneCard(): Card = deck.getOneCard()
}
