package time;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Scanner;

@Slf4j
public class DateTimeUtilTest {
    private Date date;

    @BeforeEach
    void init() {
        date = new Date();
    }

    @Test
    void t1() {
        log.info("everything is fine -> {}", "nnnnn");
        log.error("everything is fine -> {}", "nnnnn");

        PriorityQueue<LocalDate> pq = new PriorityQueue<>();

        pq.add(LocalDate.of(1990, 2, 5));
        pq.add(LocalDate.of(1990, 2, 6));
        pq.add(LocalDate.of(1990, 2, 7));
        pq.add(LocalDate.of(1990, 2, 8));

        System.out.println("====== start ======");
        for (LocalDate time : pq) {
            System.out.println(time);
        }

        System.out.println("===== remove =====");

        while (!pq.isEmpty()) {
            System.out.println(pq.remove());
        }

        System.out.println(pq.size());
    }


    @Test
    void t2() {
        Scanner s1 = new Scanner(System.in);
        while (s1.hasNext()) {
            String s = s1.next();
            if (Objects.equals(s,"stop")) {
                break;
            }
            System.err.println(s);
        }
        s1.close();
    }

    @Test
    void t3() {
        String s = "中国abc11111";

       /* for (int i = 0; i < s.length(); i++) {
            System.out.println('0' < s.charAt(i) && '9' > s.charAt(i));
        }*/

        int b = 0b111;
        int d = 111;
        int e = 0111;
        int x = 0x111;
        System.out.println(b);
        System.out.println(d);
        System.out.println(e);
        System.out.println(x);
    }

    @Test
    void t4() throws ParseException {

    }
}
