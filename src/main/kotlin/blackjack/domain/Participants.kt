package blackjack.domain

class Participants(names: List<String>, drawCard: () -> Card) {
    val dealer: Dealer = Dealer(makeCardBunch(drawCard))
    val players: List<Player> = names.map { Player(it, makeCardBunch(drawCard)) }

    private fun makeCardBunch(drawCard: () -> Card): CardBunch = CardBunch(drawCard(), drawCard())

    fun getConsequence(player: Player): Consequence = player.chooseWinner(dealer)
}
