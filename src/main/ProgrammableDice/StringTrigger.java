package main.ProgrammableDice;

import main.ProgrammableDice.Dice.History;

import java.util.List;

public class StringTrigger implements Trigger {
    private String sequence;

    public StringTrigger(String sequence) {
        this.sequence = sequence;
    }

    public StringTrigger() {
    }

    private static boolean isDigit(char ch) {
        return ch >= '1' && ch <= '6';
    }

    private static int interpretAsInt(char ch) {
        return Character.getNumericValue(ch) - Character.getNumericValue('0');
    }

    private int expectedLength() {
        int length = 0;
        for (int i = 0; i < sequence.length(); ++i) {
            char ch = sequence.charAt(i);
            if (isDigit(ch) || ch == '*')
                ++length;
        }
        return length;
    }

    @Override
    public boolean isTriggered(History history) {
        int expected = expectedLength();
        List<Integer> recent = history.last(expected);

        if (expected > recent.size())
            return false;

        int h = 0;
        int s = 0;
        while (h < recent.size() && s < sequence.length()) {
            char ch = sequence.charAt(s);
            Integer n = recent.get(h);
            if (isDigit(ch) && interpretAsInt(ch) != n)
                return false;
            if (ch == '!') {
                ++s;
                if (s < sequence.length()) {
                    char nextch = sequence.charAt(s);
                    if (isDigit(nextch) && interpretAsInt(nextch) == n)
                        return false;
                } else
                    return false;
            }
            ++h;
            ++s;
        }
        return h == recent.size() && s == sequence.length();
    }

    @Override
    public String toString() {
        return "StringTrigger{" +
                "sequence='" + sequence + '\'' +
                '}';
    }
}


//    W tym stringu wzorca moga występować
//        1-6 konkretne cyfry, które oznaczają że oczekujemy że była wyrzucona ta cyfra,
//        * oznaczająca że w tym miejscu mógł wystąpić dowolny rzut
//        > przed liczbą określa że następna cyfra określa minimalną wartość oczekiwanego rzutu
//        < przed liczbą określa że następna cyfra określa maksymalną wartość oczekiwanego rzutu
//        ! przed liczbą określa że oczekujemy cyfry innej niż ta za !
//        ? przed liczbą określa że następna cyfra mogła wystąpić albo nie
//
//        Przykłady:
//        "122" - ostatnie 3 rzuty musiały wynosić 122
//        "11**" - ostatnie 4 rzuty mogły wynosić: 1123 albo 1111 albo 1164 itp
//        ">3*" - minimum 3 i dowolny rzut: 45, 35, 56, 51, 32, itp (analogicznie działałby znak <)
//        "5?34" - 5, później możliwe że 3, później 4, czyli zarówno sekwencje 534 jak i 54 inicjują
//        "!6*6" - było coś co nie jest 6, potem dowolny rzut, potem 6