package blackjack.domain

class Dealer(
    private val players: List<Player>,
) {
    private val deck: Deck = Deck()
    private val hand: Hand = Hand(emptyList())
    val results: List<Result>
        get() =
            players.map { player ->
                when (player.result) {
                    Result.WIN -> Result.LOSE
                    Result.DRAW -> Result.DRAW
                    Result.LOSE -> Result.WIN
                    Result.NOT_YET -> Result.NOT_YET
                }
            }

    fun getCard(card: Card = deck.getCard()) {
        hand.add(card)
    }

    fun getCards(cards: List<Card>) {
        cards.forEach { card: Card -> getCard(card) }
    }

    fun giveCard() {
        players.forEach { player -> player.getCard(deck.getCard()) }
    }

    fun giveCard(player: Player) {
        player.getCard(deck.getCard())
    }

    fun getScore(): Int? = hand.getScore()

    fun getCountOfCards(): Int = hand.getSize()

    fun hitOrStay() {
        var dealerScore = getScore()
        while (dealerScore != null && dealerScore < 17) {
            getCard()
            dealerScore = getScore()
        }
    }
}
