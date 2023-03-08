package domain

data class PlayersResult(val list: List<NameAndProfit>) {
    val sum: Int
        get() = list.sumOf { it.profitMoney }
}
