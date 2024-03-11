package blackjack.exception

sealed class Exceptions : Throwable() {
    class NoCardErrorException(val reason: String) : Exceptions()

    class InvalidNameLengthErrorException(val reason: String) : Exceptions()

    class InvalidPlayersCountErrorException(val reason: String) : Exceptions()
}
