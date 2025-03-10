package blackjack.domain

class Dealer(
    private val players: List<Player>,
    shuffler: Shuffler,
) {
    private val deck: Deck = Deck(shuffler)
    private val hand: Hand = Hand(emptyList())
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
        hand.add(card)
    }

    fun getCards(cards: List<Card>) {
        cards.forEach { card: Card -> getCard(card) }
    }

    fun giveCard() {
        players.forEach { player -> player.getCard(deck.draw()) }
    }

    fun giveCard(player: Player) {
        player.getCard(deck.draw())
    }

    fun getScore(): Int? = hand.getScore()

    fun hitOrStay() {
        var dealerScore = getScore()
        while (dealerScore != null && dealerScore < 17) {
            getCard()
            dealerScore = getScore()
        }
    }
}
