public class CompteBancari {

    private static final double LIMIT_SALDO_BAIX = 1000;
    private static final double LIMIT_SALDO_NORMAL = 5000;

    private String titular;
    private String iban;
    private double saldo;

    public CompteBancari(String titular, String iban, double saldoInicial) {
        validarTitular(titular);
        validarIban(iban);
        validarSaldoInicial(saldoInicial);

        this.titular = titular;
        this.iban = iban;
        this.saldo = saldoInicial;
    }

    public void ingressar(double quantitat) {
        validarQuantitatPositiva(quantitat);
        saldo += quantitat;
    }

    public void retirar(double quantitat) {
        validarQuantitatPositiva(quantitat);
        validarSaldoSuficient(quantitat);
        saldo -= quantitat;
    }

    public String obtenirEstatSaldo() {
        if (saldo < LIMIT_SALDO_BAIX) {
            return "Saldo baix";
        }

        if (saldo < LIMIT_SALDO_NORMAL) {
            return "Saldo normal";
        }

        return "Saldo alt";
    }

    public String mostrarDades() {
        return "Titular: " + titular
                + "\nIBAN: " + iban
                + "\nSaldo: " + saldo
                + "\n" + obtenirEstatSaldo();
    }

    public String getTitular() {
        return titular;
    }

    public String getIban() {
        return iban;
    }

    public double getSaldo() {
        return saldo;
    }

    private void validarTitular(String titular) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("El titular és obligatori");
        }
    }

    private void validarIban(String iban) {
        if (iban == null || iban.isBlank()) {
            throw new IllegalArgumentException("L'IBAN és obligatori");
        }
    }

    private void validarSaldoInicial(double saldoInicial) {
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("El saldo inicial no pot ser negatiu");
        }
    }

    private void validarQuantitatPositiva(double quantitat) {
        if (quantitat <= 0) {
            throw new IllegalArgumentException("La quantitat ha de ser positiva");
        }
    }

    private void validarSaldoSuficient(double quantitat) {
        if (quantitat > saldo) {
            throw new IllegalArgumentException("No hi ha prou saldo");
        }
    }
}


