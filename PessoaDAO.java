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
