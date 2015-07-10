package br.com.itw.qopsearch.api.security;

/**
 * @author Wellington de lima alves
 */

public final class ValidarCpf {

    public static boolean validarCpf(String cpf) {
        if (cpf == null) {
            return false;
        } else {
            String cpfGerado = "";
            cpf = removerCaracteres(cpf);
            if (verificarSeTamanhoInvalido(cpf))
                return false;
            if (verificarSeDigIguais(cpf))
                return false;
            cpfGerado = cpf.substring(0, 9);
            cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));
            cpfGerado = cpfGerado.concat(calculoComCpf(cpfGerado));

            if (!cpfGerado.equals(cpf)) return false;
        }
        return true;
    }

    private static String removerCaracteres(String cpf) {
        cpf = cpf.replace("-", "");
        return cpf.replace(".", "");
    }

    private static boolean verificarSeTamanhoInvalido(String cpf) {
        if (cpf.length() != 11)
            return true;
        return false;
    }

    private static boolean verificarSeDigIguais(String cpf) {
        //char primDig = cpf.charAt(0);
        char primDig = '0';
        char[] charCpf = cpf.toCharArray();
        for (char c : charCpf)
            if (c != primDig)
                return false;
        return true;
    }

    private static String calculoComCpf(String cpf) {
        int digGerado = 0;
        int mult = cpf.length() + 1;
        char[] charCpf = cpf.toCharArray();
        for (int i = 0; i < cpf.length(); i++)
            digGerado += (charCpf[i] - 48) * mult--;
        if (digGerado % 11 < 2)
            digGerado = 0;
        else
            digGerado = 11 - digGerado % 11;
        return String.valueOf(digGerado);
    }
}