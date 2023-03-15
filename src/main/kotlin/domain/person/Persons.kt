package domain.person

data class Persons(val dealer: Dealer, val players: List<Player>) {
    companion object {
        fun getPersons(names: List<String>): Persons {
            val players = names.map { Player(it) }
            val dealer = Dealer()

            return Persons(dealer, players)
        }
    }
}
