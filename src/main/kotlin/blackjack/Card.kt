package blackjack

enum class Card(val cardId: Int) {
    SPADE_A(0),
    SPADE_2(1),
    SPADE_3(2),
    SPADE_4(3),
    SPADE_5(4),
    SPADE_6(5),
    SPADE_7(6),
    SPADE_8(7),
    SPADE_9(8),
    SPADE_10(9),
    SPADE_J(10),
    SPADE_Q(11),
    SPADE_K(12),

    HEART_A(13),
    HEART_2(14),
    HEART_3(15),
    HEART_4(16),
    HEART_5(17),
    HEART_6(18),
    HEART_7(19),
    HEART_8(20),
    HEART_9(21),
    HEART_10(22),
    HEART_J(23),
    HEART_Q(24),
    HEART_K(25),

    DIAMOND_A(26),
    DIAMOND_2(27),
    DIAMOND_3(28),
    DIAMOND_4(29),
    DIAMOND_5(30),
    DIAMOND_6(31),
    DIAMOND_7(32),
    DIAMOND_8(33),
    DIAMOND_9(34),
    DIAMOND_10(35),
    DIAMOND_J(36),
    DIAMOND_Q(37),
    DIAMOND_K(38),

    CLUB_A(39),
    CLUB_2(40),
    CLUB_3(41),
    CLUB_4(42),
    CLUB_5(43),
    CLUB_6(44),
    CLUB_7(45),
    CLUB_8(46),
    CLUB_9(47),
    CLUB_10(48),
    CLUB_J(49),
    CLUB_Q(50),
    CLUB_K(51);

    companion object {
        fun matchCard(num:Int): Card {
            return entries.filter {it.cardId == num}[0]
        }
    }
}
