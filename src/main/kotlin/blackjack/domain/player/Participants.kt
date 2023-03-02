package blackjack.domain.player

class Participants(values: List<Participant>) {
    constructor(vararg values: String) : this(values.map { Participant(it) }.toList())
}
