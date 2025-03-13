package blackjack.model.game

enum class UserCommand(
    val command: String,
) {
    HIT("y"),
    STAY("n"),
    UNKNOWN(""),
    ;

    companion object {
        fun from(command: String): UserCommand =
            UserCommand.entries.find {
                it.command == command.lowercase()
            } ?: UNKNOWN
    }
}
