import com.opencsv.CSVReader;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Controle {
    public static List<UsuarioCadastrado> usuariosCadastrados;
    private static final String caminhoCSV = "./src/main/resources/usuarios.csv";

    public List<UsuarioCadastrado> leArquivoCSV() throws IOException, CsvException {

        usuariosCadastrados = new ArrayList<UsuarioCadastrado>();

        try (CSVReader reader = new CSVReader(new FileReader(caminhoCSV))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                UsuarioCadastrado usuarioCadastrado = new UsuarioCadastrado(nextLine[0], nextLine[1], Integer.parseInt(nextLine[2]));
                usuariosCadastrados.add(usuarioCadastrado);
            }
            reader.close();
            return usuariosCadastrados;
        }catch (FileNotFoundException e){
            return null;
        }
    }

    public boolean verificaLogin(String nome, String senha){
        for(UsuarioCadastrado usuario : usuariosCadastrados){
            if(nome.equals(usuario.getNome()) && senha.equals(usuario.getSenha())){
                usuario.setNumeroDeTentativasDeLogin(0);
                return true;
            }
            else if(nome.equals(usuario.getNome())){
                usuario.setNumeroDeTentativasDeLogin(usuario.getNumeroDeTentativasDeLogin()+1);
                return false;
            }
        }
        return false;
    }

    public void atualizaArquivoCSV()
    {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(caminhoCSV));
            StatefulBeanToCsv<UsuarioCadastrado> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();

            beanToCsv.write(usuariosCadastrados);

            writer.flush();
            writer.close();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }
}
