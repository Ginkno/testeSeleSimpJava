@RestController
@RequestMapping("/pessoa")
public class PessoaController {
  
  @Autowired
  private PessoaDAO pessoaDAO;

  @GetMapping("/pessoas")
  public List<Pessoa> listar() {
      return pessoaDAO.listar();
  }

  @GetMapping("/pessoas/{id}")
  public Pessoa buscar(@PathVariable Long id) {
      return pessoaDAO.buscar(id);
  }

  @PostMapping("/pessoas")
  public void salvar(@RequestBody Pessoa pessoa) {
      pessoaDAO.salvar(pessoa);
  }

  @PutMapping("/pessoas/{id}")
  public void atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
      pessoa.setId(id);
      pessoaDAO.salvar(pessoa);
  }

  @DeleteMapping("/pessoas/{id}")
  public void excluir(@PathVariable Long id) {
      pessoaDAO.excluir(id);
  }
}
