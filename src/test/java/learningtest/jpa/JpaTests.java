package learningtest.jpa;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by izeye on 15. 8. 12..
 */
public class JpaTests {

	// tag::testCrud[]
	@Test
	public void testCrud() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			User user = new User();
			user.setFirstName("Johnny");
			user.setLastName("Lim");
			user.setAge(35);

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();

				assertFalse(em.contains(user));
				
				// Create
				em.persist(user);
				assertTrue(em.contains(user));

				// Read
				User found = em.find(User.class, user.getId());
				assertThat(found, is(user));
				assertThat(found, is(sameInstance(user)));
				
				// Update
				user.setAge(20);

				found = em.find(User.class, user.getId());
				assertThat(found.getAge(), is(20));

				TypedQuery<User> query = em.createQuery("select u from User u", User.class);
				List<User> users = query.getResultList();
				users.forEach(System.out::println);

				assertTrue(em.contains(user));
				
				// Delete
				em.remove(user);
				assertFalse(em.contains(user));

				tx.commit();
			} catch (Throwable ex) {
				ex.printStackTrace();

				tx.rollback();
			}
		} finally {
			if (em != null) {
				try {
					em.close();
				} catch (Throwable ex) {
				}
			}
			
			if (emf != null) {
				try {
					emf.close();
				} catch (Throwable ex) {
				}
			}
		}
	}
	// end::testCrud[]

	// tag::testReadWithoutTransaction[]
	@Test
	public void testReadWithoutTransaction() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			User user = new User();
			user.setFirstName("Johnny");
			user.setLastName("Lim");
			user.setAge(35);

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();

				assertFalse(em.contains(user));

				// Create
				em.persist(user);
				assertTrue(em.contains(user));

				tx.commit();
			} catch (Throwable ex) {
				ex.printStackTrace();

				tx.rollback();
			}

			// Read
			User found = em.find(User.class, user.getId());
			assertThat(found, is(user));
			assertThat(found, is(sameInstance(user)));
		} finally {
			if (em != null) {
				try {
					em.close();
				} catch (Throwable ex) {
				}
			}

			if (emf != null) {
				try {
					emf.close();
				} catch (Throwable ex) {
				}
			}
		}
	}
	// end::testReadWithoutTransaction[]

	// tag::testDetach[]
	@Test
	public void testDetach() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			User user = new User();
			user.setFirstName("Johnny");
			user.setLastName("Lim");
			user.setAge(35);

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();

				assertFalse(em.contains(user));
				
				// Create
				em.persist(user);
				assertTrue(em.contains(user));
				
				// NOTE:
				// You should flush before merging when using `GenerationType.SEQUENCE`.
				// If not, you will get an error.
				// Why? No idea.
				em.flush();
				
				// Detach
				em.detach(user);
				assertFalse(em.contains(user));
				
				// Update the detached
				user.setAge(100);

				// Merge
				User merged = em.merge(user);
				assertThat(merged, is(not(sameInstance(user))));
				assertFalse(em.contains(user));
				assertTrue(em.contains(merged));
				assertThat(merged.getAge(), is(100));

				tx.commit();
			} catch (Throwable ex) {
				tx.rollback();

				ex.printStackTrace();
				throw ex;
			}
		} finally {
			if (em != null) {
				try {
					em.close();
				} catch (Throwable ex) {
				}
			}

			if (emf != null) {
				try {
					emf.close();
				} catch (Throwable ex) {
				}
			}
		}
	}
	// end::testDetach[]

	// tag::testClear[]
	@Test
	public void testClear() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			User user = new User();
			user.setFirstName("Johnny");
			user.setLastName("Lim");
			user.setAge(35);

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();

				assertFalse(em.contains(user));
				
				// Create
				em.persist(user);
				assertTrue(em.contains(user));

				// Clear
				em.clear();
				assertFalse(em.contains(user));

				// Update the detached
				user.setAge(100);

				// Merge
				User merged = em.merge(user);
				assertThat(merged, is(not(sameInstance(user))));
				assertFalse(em.contains(user));
				assertTrue(em.contains(merged));
				assertThat(merged.getAge(), is(100));

				tx.commit();
			} catch (Throwable ex) {
				tx.rollback();
				
				ex.printStackTrace();
				throw ex;
			}
		} finally {
			if (em != null) {
				try {
					em.close();
				} catch (Throwable ex) {
				}
			}

			if (emf != null) {
				try {
					emf.close();
				} catch (Throwable ex) {
				}
			}
		}
	}
	// end::testClear[]

	// tag::testClose[]
	@Test
	public void testClose() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			User user = new User();
			user.setFirstName("Johnny");
			user.setLastName("Lim");
			user.setAge(35);

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();

				assertFalse(em.contains(user));
				
				// Create
				em.persist(user);
				assertTrue(em.contains(user));

				tx.commit();
			} catch (Throwable ex) {
				tx.rollback();

				ex.printStackTrace();
				throw ex;
			}
			
			// Close
			em.close();

			// Update the detached
			user.setAge(100);

			em = emf.createEntityManager();
			tx = em.getTransaction();
			try {
				tx.begin();

				assertFalse(em.contains(user));
				
				// Merge
				User merged = em.merge(user);
				assertThat(merged, is(not(sameInstance(user))));
				assertFalse(em.contains(user));
				assertTrue(em.contains(merged));
				assertThat(merged.getAge(), is(100));

				tx.commit();
			} catch (Throwable ex) {
				tx.rollback();

				ex.printStackTrace();
				throw ex;
			}
		} finally {
			if (em != null) {
				try {
					em.close();
				} catch (Throwable ex) {
				}
			}

			if (emf != null) {
				try {
					emf.close();
				} catch (Throwable ex) {
				}
			}
		}
	}
	// end::testClose[]

}
