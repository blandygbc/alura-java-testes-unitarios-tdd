import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Funcionario;
import br.com.alura.tdd.service.BonusService;

public class BonusServiceTest {

    @Test
    void bonusDeveriaSerZeroParaSalarioAlto() {
        BonusService service = new BonusService();
        BigDecimal bonus = service
                .calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal(1000000000)));
        assertEquals(new BigDecimal("0.00"), bonus);
    }

    @Test
    void bonusDeveriaSerDezPorCentoParaSalarioComum() {
        BonusService service = new BonusService();
        BigDecimal bonus = service
                .calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal(9500)));
        assertEquals(new BigDecimal("950.00"), bonus);
    }

    @Test
    void bonusDeveriaSerMilParaSalarioDezMil() {
        BonusService service = new BonusService();
        BigDecimal bonus = service
                .calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal(10000)));
        assertEquals(new BigDecimal("1000.00"), bonus);
    }
}
