package blackjack.domain

class Dealer(
    private val players: List<Player>,
    shuffler: Shuffler,
) {
    private val deck: Deck = Deck(shuffler)
    private val hand: Hand = Hand()
    val cards: List<Card>
        get() = hand.value
    val playerStates: List<PlayerState>
        get() =
            players.map { player ->
                when (player.playerState) {
                    PlayerState.WIN -> PlayerState.LOSE
                    PlayerState.DRAW -> PlayerState.DRAW
                    PlayerState.LOSE -> PlayerState.WIN
                    PlayerState.PLAYING -> PlayerState.PLAYING
                }
            }

    fun getCard(card: Card = deck.draw()) {
        hand.draw(card)
    }

    fun getCards(cards: List<Card>) {
        cards.forEach { card: Card -> getCard(card) }
    }

    fun giveCard() {
        players.forEach { player -> player.draw(deck.draw()) }
    }

    fun giveCard(player: Player) {
        player.draw(deck.draw())
    }

    fun getScore(): Int? = hand.score

    fun hitOrStay() {
        var dealerScore = getScore()
        while (dealerScore != null && dealerScore < 17) {
            getCard()
            dealerScore = getScore()
        }
    }
}
