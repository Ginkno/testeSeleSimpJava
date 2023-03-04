@Repository
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PessoaDAO extends JpaRepository<Pessoa, Long> {
  @Autowired
  private SessionFactory sessionFactory;

  public List<Pessoa> listar() {
      Session session = sessionFactory.getCurrentSession();
      CriteriaBuilder cb = session.getCriteriaBuilder();
      CriteriaQuery<Pessoa> cq = cb.createQuery(Pessoa.class);
      Root<Pessoa> root = cq.from(Pessoa.class);
      cq.select(root);
      Query query = session.createQuery(cq);
      return query.getResultList();
  }

  public Pessoa buscar(Long id) {
      Session session = sessionFactory.getCurrentSession();
      return session.get(Pessoa.class, id);
  }

  public void salvar(Pessoa pessoa) {
      Session session = sessionFactory.getCurrentSession();
      session.saveOrUpdate(pessoa);
      enviarSms(pessoa.getNome()); // chama o método para enviar o SMS
  }

  public void excluir(Long id) {
      Session session = sessionFactory.getCurrentSession();
      Pessoa pessoa = session.byId(Pessoa.class).load(id);
      session.delete(pessoa);
  }
  
  private void enviarSms(Pessoa pessoa) throws Exception {
    String customerId = "B025956F-2186-41E0-BBE9-6C0A6441C63A";
    String apiKey = "vvWpxAdY+tzebf3xPnHC7NyI8tqD4dSCTmqB6qlvdUKqTbbHhGX6aOuo5rzF90AlsPfY0+117grpWBAmUaccTw==";
    String phoneNumber = "+55" + pessoa.getTelefone();
    String message = "Olá " + pessoa.getNome() + ", você foi cadastrado na fila!";
    TelesignClient client = new TelesignClient(customerId, apiKey);
    MessagingClient messagingClient = new MessagingClient(client);
    messagingClient.message(phoneNumber, message, "ARN");
  }
}
