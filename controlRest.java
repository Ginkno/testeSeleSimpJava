@RestController
@RequestMapping("/pessoa")
public class PessoaController {
  
  
  @Autowired
  private PessoaDAO pessoaDAO;
  
  @GetMapping
  public List<Pessoa> buscarTodas() {
    return pessoaDAO.buscarTodos();
  }
  
  @PostMapping
  public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody Pessoa pessoa) {
    pessoaDAO.salvar(pessoa);
    return ResponseEntity.ok().body(pessoa);
  }
  
  @GetMapping{"/{id}"}
  public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
    Pessoa pessoa = pessoaDAO.buscarPorId(id);
    if (pessoa == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(pessoa);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
    Pessoa pessoa = pessoaDAO.buscarPorId(id);
    if (pessoa == null) {
      return ResponseEntity.notFound().build();
    }
    
    pessoa.setNome(pessoaAtualizada.getNome());
    pessoa.setIdade(pessoaAtualizada.getIdade());
    pessoa.setPosicaoFila(pessoaAtualizada.getPosicaoFila());
    pessoaDAO.atualizar(pessoa);
    return ResponseEntity.ok().body(pessoa);
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
    Pessoa pessoa = pessoaDAO.buscarPorId(id);
    if (pessoa == null) {
      return ResponseEntity.notFound().build();
    }
    pessoaDAO.deletar(pessoa);
    return ResponseEntity.noContent().build();
  }
}
