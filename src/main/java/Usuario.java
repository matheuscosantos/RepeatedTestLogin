import com.opencsv.bean.CsvBindByPosition;

public class Usuario {
    @CsvBindByPosition(position = 0)
    String nome;
    @CsvBindByPosition(position = 1)
    String senha;
    public Usuario(String nome,String senha){
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
