package va.ultis;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUltis {
	public static EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PH14045_HaVietAnh_Assignment");
		EntityManager em = factory.createEntityManager();
		return em;
	}
}
