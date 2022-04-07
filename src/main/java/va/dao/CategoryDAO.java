package va.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import entities.Category;
import va.ultis.JPAUltis;

public class CategoryDAO {
	private EntityManager em;
	public CategoryDAO() {
		this.em=JPAUltis.getEntityManager();
	}
	public Category create(Category entity) throws Exception{
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
	public Category update(Category entity) throws Exception{
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
	public Category delete(Category entity) throws Exception{
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
	public List<Category> all(){
		String jpql = "SELECT obj FROM Category obj";
		TypedQuery<Category> query = this.em.createQuery(jpql,Category.class);
		List<Category> result = query.getResultList();
		return result;
	}
	public Category findById(int id) {
		return this.em.find(Category.class, id);
	}
	
}
