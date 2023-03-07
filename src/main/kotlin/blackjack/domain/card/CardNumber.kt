package blackjack.domain.card

// TODO: value를 쓰지 않고 어떻게 해야할까?
enum class CardNumber(val value: Int) {

    A(1),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(10),
    Q(10),
    K(10),
    BIG_A(11)
}
