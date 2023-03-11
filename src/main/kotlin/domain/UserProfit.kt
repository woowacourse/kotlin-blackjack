package domain

data class UserProfit(
    val user: User,
    val profit: Double
) {
    val name = user.name
}
