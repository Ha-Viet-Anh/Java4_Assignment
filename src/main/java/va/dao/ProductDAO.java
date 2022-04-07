package va.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Product;
import va.ultis.JPAUltis;

public class ProductDAO {
	private EntityManager em;
	public ProductDAO() {
		this.em = JPAUltis.getEntityManager();
	}
	public Product create(Product entity) throws Exception{
		try {
			this.em.getTransaction().begin();
			this.em.persist(entity);
			this.em.getTransaction().commit();
			return entity;
		}catch(Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	public Product update(Product entity) throws Exception{
		try {
			this.em.getTransaction().begin();
			this.em.merge(entity);
			this.em.getTransaction().commit();
			return entity;
		}catch(Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	public Product delete(Product entity) throws Exception{
		try {
			this.em.getTransaction().begin();
			this.em.remove(entity);
			this.em.getTransaction().commit();
			return entity;
		}catch(Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
			throw e;
		}
	}
	public List<Product> all(){
		String jpql = "SELECT obj FROM Product obj";
		TypedQuery<Product> query = this.em.createQuery(jpql,Product.class);
		List<Product> result = query.getResultList();
		return result;
	}
	public Product findById(int id) {
		return this.em.find(Product.class, id);
	}
	
}
