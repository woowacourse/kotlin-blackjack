package blackjack.domain.player

import blackjack.domain.Result

class Participant(name: String) : Player(name) {

    lateinit var result: Result
        private set
}
