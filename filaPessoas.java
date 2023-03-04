@Entity
@Table(name = "pessoas")
public class Pessoa {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private Integer idade;

  @Column(name = "posicao_fila")
  private Integer posicaoFila;
  
  public Pessoa() {}
  
  public Pessoa(String nome, int idade, int posicaoFila) {
    this.nome = nome;
    this.idade = idade;
    this.posicaoFila = posicaoFila;
  }
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public String getNome() {
    return nome;
  }
  
  public void setNome(String nome) {
    this.nome = nome;
  }
  
  public int getIdade() {
    return idade;
  }
  
  public void setIdade(int idade) {
    this.idade = idade;
  }
  
  public int getPosicaoFila() {
    return posicaoFila;
  }
  
  public void setPosicaoFila(int posicaoFila) {
    this.posicaoFila = posicaoFila;
  }
}
