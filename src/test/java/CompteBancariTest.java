import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompteBancariTest {

    private CompteBancari compte;

    @BeforeEach
    void setUp() {
        compte = new CompteBancari("Maria", "ES123456789", 100.0);
    }

    @Test
    void constructorAmbDadesCorrectes_creaCompteCorrectament() {
        assertEquals("Maria", compte.getTitular());
        assertEquals("ES123456789", compte.getIban());
        assertEquals(100.0, compte.getSaldo(), 0.001);
    }

    @Test
    void constructorAmbTitularNull_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CompteBancari(null, "ES123456789", 100.0);
        });
    }

    @Test
    void constructorAmbTitularBuit_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CompteBancari("", "ES123456789", 100.0);
        });
    }

    @Test
    void constructorAmbIbanNull_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CompteBancari("Maria", null, 100.0);
        });
    }

    @Test
    void constructorAmbIbanBuit_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CompteBancari("Maria", "", 100.0);
        });
    }

    @Test
    void constructorAmbSaldoNegatiu_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CompteBancari("Maria", "ES123456789", -50.0);
        });
    }

    @Test
    void ingressarQuantitatCorrecta_incrementaSaldo() {
        compte.ingressar(50.0);

        assertEquals(150.0, compte.getSaldo(), 0.001);
    }

    @Test
    void ingressarZero_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.ingressar(0);
        });
    }

    @Test
    void ingressarQuantitatNegativa_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.ingressar(-20.0);
        });
    }

    @Test
    void retirarQuantitatCorrecta_redueixSaldo() {
        compte.retirar(40.0);

        assertEquals(60.0, compte.getSaldo(), 0.001);
    }

    @Test
    void retirarZero_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.retirar(0);
        });
    }

    @Test
    void retirarQuantitatNegativa_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.retirar(-10.0);
        });
    }

    @Test
    void retirarMesDelSaldoDisponible_llencaExcepcio() {
        assertThrows(IllegalArgumentException.class, () -> {
            compte.retirar(200.0);
        });
    }
}