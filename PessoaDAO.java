import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PessoaDAO {
  public void salvar(Pessoa pessoa) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.save(pessoa);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback(); 
      }
      e.printStackTrace();
    }
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
  
  public void atualizar(Pessoa pessoa) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      session.save(pessoa);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback(); 
      }
      e.printStackTrace();
    }
  }
  
  public void deletar(int id) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      Pessoa pessoa = session.get(Pessoa.class, id);
      if (pessoa != null) {
        session.delete(pessoa);
      }
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTrace();
    }
  }
  
  public Pessoa buscaPorId(int id) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      pessoa = session.get(Pessoa.class, id);
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTree();
    }
    return pessoa;
  }
  
  public List<Pessoa> buscarTodos() {
    Transaction transaction = null;
    List<Pessoa> pessoas = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
      transaction = session.beginTransaction();
      pessoas = session.createQuery("FROM Pessoa", Pessoa.class).list();
      transaction.commit();
    } catch (Exception e) {
      if (transaction != null) {
        transaction.rollback();
      }
      e.printStackTree();
    }
    return pessoas;
  }
}
