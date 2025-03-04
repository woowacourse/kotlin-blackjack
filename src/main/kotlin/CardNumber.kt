enum class CardNumber(
    private val number: Int,
    val display: String = "$number",
) {
    Ace(1, "A"),
    Two(2),
    Three(3),
    Four(4),
    Five(5),
    Six(6),
    Seven(7),
    Eight(8),
    Nine(9),
    Ten(10),
    Jack(10, "J"),
    Queen(10, "Q"),
    King(10, "K"),
}
