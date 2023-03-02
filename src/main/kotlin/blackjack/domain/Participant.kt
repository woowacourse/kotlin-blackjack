package blackjack.domain

class Participant(name: String) : Player(name) {

    lateinit var result: Result
        private set
}
