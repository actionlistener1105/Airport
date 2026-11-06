package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {

    // Сравнение по цене (compareTo возвращает числа, поэтому assertArrayEquals тут не нужен)
    @Test
    void shouldCompareTicketsByPrice() {
        Ticket cheap = new Ticket("MOW", "LED", 5000, 1000, 1200);
        Ticket expensive = new Ticket("MOW", "LED", 12000, 1100, 1400);
        Ticket samePrice = new Ticket("MOW", "LED", 5000, 900, 1100);

        Assertions.assertEquals(-1, cheap.compareTo(expensive));
        Assertions.assertEquals(1, expensive.compareTo(cheap));
        Assertions.assertEquals(0, cheap.compareTo(samePrice));
    }

    // Должен вернуть билеты, отсортированные по возрастанию цены
    @Test
    void shouldReturnSortedTicketsByPriceInSearch() {
        AviaSouls manager = new AviaSouls();

        Ticket expensive = new Ticket("MOW", "LED", 12000, 1000, 1300);
        Ticket cheap = new Ticket("MOW", "LED", 5000, 800, 1030);
        Ticket medium = new Ticket("MOW", "LED", 7000, 900, 1130);

        manager.add(expensive);
        manager.add(cheap);
        manager.add(medium);

        Ticket[] result = manager.search("MOW", "LED");

        // Ожидаем билеты в порядке возрастания цены: 5000 -> 7000 -> 12000
        Ticket[] expected = new Ticket[]{cheap, medium, expensive};

        Assertions.assertArrayEquals(expected, result,
                "Билеты должны вернуться в порядке возрастания цены");
    }

    // Должен вернуть пустой массив, если совпадений нет
    @Test
    void shouldReturnEmptyArrayWhenNoMatchesInSearch() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("MOW", "LED", 6000, 1000, 1200));

        Ticket[] result = manager.search("KZN", "LED");

        Ticket[] expected = new Ticket[]{};

        Assertions.assertArrayEquals(expected, result,
                "При отсутствии совпадений должен возвращаться пустой массив, а не null");
    }

    // Сравнение по длительности полёта (compare возвращает числа — assertArrayEquals не применим)
    @Test
    void shouldCompareByFlightDuration() {
        Ticket shortFlight = new Ticket("MOW", "LED", 6000, 1000, 1200);
        Ticket longFlight = new Ticket("MOW", "LED", 8000, 1000, 1400);
        Ticket mediumFlight = new Ticket("MOW", "LED", 7000, 1000, 1300);

        TicketTimeComparator comparator = new TicketTimeComparator();

        Assertions.assertEquals(-1, comparator.compare(shortFlight, longFlight));
        Assertions.assertEquals(1, comparator.compare(longFlight, shortFlight));
        Assertions.assertEquals(0, comparator.compare(mediumFlight, mediumFlight));
    }

    // Должен вернуть билеты, отсортированные по возрастанию длительности
    @Test
    void shouldSearchAndSortByFlightDuration() {
        AviaSouls manager = new AviaSouls();

        Ticket mediumFlight = new Ticket("MOW", "LED", 6000, 1000, 1300);
        Ticket shortFlight = new Ticket("MOW", "LED", 5000, 1000, 1200);
        Ticket longFlight = new Ticket("MOW", "LED", 7000, 1000, 1400);

        manager.add(mediumFlight);
        manager.add(shortFlight);
        manager.add(longFlight);

        TicketTimeComparator byDuration = new TicketTimeComparator();
        Ticket[] result = manager.searchAndSortBy("MOW", "LED", byDuration);
        Ticket[] expected = new Ticket[]{shortFlight, mediumFlight, longFlight};

        Assertions.assertArrayEquals(expected, result,
                "Билеты должны вернуться в порядке возрастания длительности полёта");
    }
}

