package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Funcionario;
import br.com.alura.tdd.service.BonusService;

public class BonusServiceTest {
    BonusService service;

    @BeforeEach
    public void init() {
        service = new BonusService();
    }

    @Test
    void bonusDeveriaLancarExceptionParaSalarioAlto() {
        final Funcionario funcionario = new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal(10001));
        assertThrows(IllegalArgumentException.class, () -> service
                .calcularBonus(funcionario));
    }

    @Test
    void bonusDeveriaExibirMensagemDaExceptionCorretamente() {
        final Funcionario funcionario = new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal(10001));
        try {
            service.calcularBonus(funcionario);
            fail();
        } catch (Exception e) {
            assertEquals("Funcionário com salário maior do que R$10.000,00 não pode receber bonus!", e.getMessage());
        }
    }

    @Test
    void bonusDeveriaSerDezPorCentoParaSalarioComum() {
        BigDecimal bonus = service
                .calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal(9500)));
        assertEquals(new BigDecimal("950.00"), bonus);
    }

    @Test
    void bonusDeveriaSerMilParaSalarioDezMil() {
        BigDecimal bonus = service
                .calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal(10000)));
        assertEquals(new BigDecimal("1000.00"), bonus);
    }
}
