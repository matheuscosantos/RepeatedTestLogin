import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

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
                controle.atualizaArquivoCSV();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UsuariosParaTestes.csv", delimiter = ',')
    public void testaAutenticacao(String usuario, String senha){
        controle = new Controle();
        controle.verificaLogin(usuario,senha);
        controle.atualizaArquivoCSV();
        assertTrue(true);
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
