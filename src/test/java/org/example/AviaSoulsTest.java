package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AviaSoulsTest {

    @Test
    void shouldCompareTicketsByPrice() {
        Ticket cheap = new Ticket("MOW", "LED", 5000, 1000, 1200);
        Ticket expensive = new Ticket("MOW", "LED", 12000, 1100, 1400);
        Ticket samePrice = new Ticket("MOW", "LED", 5000, 900, 1100);

        assertEquals(-1, cheap.compareTo(expensive));
        assertEquals(1, expensive.compareTo(cheap));
        assertEquals(0, cheap.compareTo(samePrice));
    }

    @Test
    void shouldReturnSortedTicketsByPriceInSearch() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("MOW", "LED", 12000, 1000, 1300));
        manager.add(new Ticket("MOW", "LED", 5000, 800, 1030));
        manager.add(new Ticket("MOW", "LED", 7000, 900, 1130));

        Ticket[] result = manager.search("MOW", "LED");

        assertEquals(3, result.length);
        assertEquals(5000, result[0].getPrice());
        assertEquals(7000, result[1].getPrice());
        assertEquals(12000, result[2].getPrice());
    }

    @Test
    void shouldReturnEmptyArrayWhenNoMatchesInSearch() {
        AviaSouls manager = new AviaSouls();
        manager.add(new Ticket("MOW", "LED", 6000, 1000, 1200));

        Ticket[] result = manager.search("KZN", "LED");
        assertEquals(0, result.length);
    }

    @Test
    void shouldCompareByFlightDuration() {
        Ticket shortFlight = new Ticket("MOW", "LED", 6000, 1000, 1200);
        Ticket longFlight = new Ticket("MOW", "LED", 8000, 1000, 1400);
        Ticket mediumFlight = new Ticket("MOW", "LED", 7000, 1000, 1300);

        TicketTimeComparator comparator = new TicketTimeComparator();

        assertEquals(-1, comparator.compare(shortFlight, longFlight));
        assertEquals(1, comparator.compare(longFlight, shortFlight));
        assertEquals(0, comparator.compare(mediumFlight, mediumFlight));
    }
    
}
