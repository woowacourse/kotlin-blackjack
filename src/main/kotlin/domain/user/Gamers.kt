package domain.user

import domain.card.Cards

class Gamers(inputNames: String) {
    val gamers: List<User> = inputNames.split(",").map { Gamer(it) }

    fun dealCard(cards: Cards) {
        this.gamers.map { it -> it.draw(cards) }
    }


}
