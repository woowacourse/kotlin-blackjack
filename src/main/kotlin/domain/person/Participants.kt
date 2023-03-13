package domain.person

class Participants(val dealer: Dealer, val players: List<Player>) {

    companion object {
        fun from(getPlayerNames: () -> List<String>): Participants {
            val dealer = Dealer()
            val players = getPlayerNames().map { name -> Player(name) }
            return Participants(dealer, players)
        }
    }
}
