package domain

class BlackJackGameData(
    val deck: Deck,
    val players: Players
) {
    val dealer get() = players.dealer
    val users get() = players.users
}
