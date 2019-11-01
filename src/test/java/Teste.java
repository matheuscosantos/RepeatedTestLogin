import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class Teste {
    public static Controle controle = new Controle();

    @Test
    public void testaAberturaDoDocumento() throws IOException, CsvException {
        boolean existe = false;
        if (controle.leArquivoCSV() != null) {
            existe = true;
        }
        assertTrue(existe);
    }
}
