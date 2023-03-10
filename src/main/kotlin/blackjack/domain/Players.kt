package blackjack.domain

class Players(val value: List<Player>) {
    constructor(names: List<String>, generatePlayer: (String) -> Player) : this(names.map { generatePlayer(it) })
}
