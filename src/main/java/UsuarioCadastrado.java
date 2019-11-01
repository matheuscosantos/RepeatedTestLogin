import com.opencsv.bean.CsvBindByPosition;

public class UsuarioCadastrado extends Usuario{
    @CsvBindByPosition(position = 2)
    int numeroDeTentativasDeLogin;

    public UsuarioCadastrado(String nome, String senha, int numeroDeTentativasDeLogin) {
        super(nome, senha);
        this.numeroDeTentativasDeLogin = numeroDeTentativasDeLogin;
    }

    public int getNumeroDeTentativasDeLogin() {
        return numeroDeTentativasDeLogin;
    }

    public void setNumeroDeTentativasDeLogin(int numeroDeTentativasDeLogin) {
        this.numeroDeTentativasDeLogin = numeroDeTentativasDeLogin;
    }
}
