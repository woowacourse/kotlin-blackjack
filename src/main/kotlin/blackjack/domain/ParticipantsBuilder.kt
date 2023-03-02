package blackjack.domain

class ParticipantsBuilder {

    private lateinit var dealer: User
    private lateinit var users: List<User>
    fun dealer(name: String) {
        dealer = User(name)
    }

    fun users(names: List<String>) {
        users = names.map { User(it) }
    }

    fun build(): Participants {
        return Participants(dealer, users)
    }

    companion object {
        fun init(block: ParticipantsBuilder.() -> Unit): Participants =
            ParticipantsBuilder().apply(block).build()
    }
}
