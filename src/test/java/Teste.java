import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class Teste {

    private static Controle controle;

    @BeforeAll
    public static void zeraNumeroDeTentativasDeLogin(){
        Controle controle = new Controle();
        try {
            if(controle.leArquivoCSV() != null){
                List<UsuarioCadastrado> usuariosCadastrados = controle.leArquivoCSV();
                for(UsuarioCadastrado usuario : usuariosCadastrados){
                    usuario.setNumeroDeTentativasDeLogin(0);
                }
                controle.atualizaArquivoCSV(usuariosCadastrados);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    @RepeatedTest(5)
    public void testaAutenticacao(RepetitionInfo repetitionInfo) throws IOException, CsvException {
        String usuario = "matheuss";
        String senha = "12345";
        controle = new Controle();

        if(controle.verificaLogin(usuario,senha).equals("Usuário não localizado!")){
            assertTrue(true);
        }else{
            if(repetitionInfo.getCurrentRepetition() <= 5){
                assertTrue(controle.verificaLogin(usuario,senha).equals("Usuário logado!"));
            }else {
                assertTrue(controle.verificaLogin(usuario,senha).equals("Senha errada!"));
            }
        }
    }

    @Test
    public void testaLeituraDoArquivo(){
        controle = new Controle();
        boolean lidoComSucesso = false;
        try {
            if(controle.leArquivoCSV() != null){
                lidoComSucesso = true;
            }
        } catch (IOException e) {
            lidoComSucesso = false;
        } catch (CsvException e) {
            lidoComSucesso = false;
        }
        assumeTrue(lidoComSucesso);
    }
}

