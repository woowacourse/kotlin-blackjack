package domain

class Participants(private val participants: List<Participant>) : List<Participant> by participants{
    fun initStage(deck: Deck) {
        repeat(2) {participants.forEach{ it.draw(deck.pop()) }}
    }
}
