package learningtest.jpa;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TransactionRequiredException;
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

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	// tag::testCreate[]
	@Test
	public void testCreate() {
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
	// end::testCreate[]

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

	// tag::testCreateFailedWithoutTransaction[]
	@Test
	public void testCreateFailedWithoutTransaction() {
		thrown.expect(TransactionRequiredException.class);
		thrown.expectMessage("no transaction is in progress");

		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			User user = new User();
			user.setFirstName("Johnny");
			user.setLastName("Lim");
			user.setAge(35);

			assertFalse(em.contains(user));

			// Create
			em.persist(user);
			em.flush();
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
	// end::testCreateFailedWithoutTransaction[]

	// tag::testPersist[]
	@Test
	public void testPersist() {
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
				
				// Persist without change
				em.persist(user);
				assertTrue(em.contains(user));

				// Persist with change
				user.setAge(100);
				
				em.persist(user);
				assertTrue(em.contains(user));

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
	// end::testPersist[]

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

	// tag::testManyToOne[]
	@Test
	public void testManyToOne() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();

				UserGroup userGroup = new UserGroup("dummy");
				em.persist(userGroup);

				User user1 = new User("Johnny", "Lim");
				user1.setGroup(userGroup);
				em.persist(user1);

				User user2 = new User("Bob", "Kim");
				user2.setGroup(userGroup);
				em.persist(user2);

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
	// end::testManyToOne[]

	// tag::testJpql[]
	@Test
	public void testJpql() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();

				UserGroup userGroup = new UserGroup("dummy");
				em.persist(userGroup);

				User user1 = new User("Johnny", "Lim");
				user1.setGroup(userGroup);
				em.persist(user1);

				User user2 = new User("Bob", "Kim");
				user2.setGroup(userGroup);
				em.persist(user2);

				String jpql = "select u from User u join u.group g where g.name=:groupName";
				List<User> users = em.createQuery(jpql, User.class)
						.setParameter("groupName", "dummy").getResultList();
				users.forEach(System.out::println);

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
	// end::testJpql[]

	// tag::testManyToOneUpdate[]
	@Test
	public void testManyToOneUpdate() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();

				UserGroup userGroup1 = new UserGroup("dummy");
				em.persist(userGroup1);

				User user1 = new User("Johnny", "Lim");
				user1.setGroup(userGroup1);
				em.persist(user1);

				User user2 = new User("Bob", "Kim");
				user2.setGroup(userGroup1);
				em.persist(user2);

				UserGroup userGroup2 = new UserGroup("geek");
				em.persist(userGroup2);
				
				user1.setGroup(userGroup2);

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
	// end::testManyToOneUpdate[]

	// tag::testOneToOne[]
	@Test
	public void testOneToOne() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				
				Locker locker = new Locker("locker #1");
				em.persist(locker);

				User user = new User("Johnny", "Lim");
				user.setLocker(locker);
				em.persist(user);

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
	// end::testOneToOne[]

	// tag::testManyToManyWithoutIdClass[]
	@Test
	public void testManyToManyWithoutIdClass() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();

				Product product = new Product();
				product.setName("notebook");
				em.persist(product);

				User user = new User("Johnny", "Lim");
				em.persist(user);

				Order order = new Order();
				order.setUser(user);
				order.setProduct(product);
				order.setOrderAmount(2);
				em.persist(order);
				
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
	// end::testManyToManyWithoutIdClass[]

	// tag::testInheritanceJoined[]
	@Test
	public void testInheritanceJoined() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				
				Album album = new Album();
				album.setName("album name");
				album.setPrice(10);
				album.setArtist("album artist");
				em.persist(album);
				
				Book book = new Book();
				book.setName("book name");
				book.setPrice(100);
				book.setAuthor("book author");
				book.setIsbn("book isbn");
				em.persist(book);
				
				Movie movie = new Movie();
				movie.setName("movie name");
				movie.setPrice(1000);
				movie.setActor("movie actor");
				movie.setDirector("movie director");
				em.persist(movie);

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
	// end::testInheritanceJoined[]

	// tag::testMappedSuperclass[]
	@Test
	public void testMappedSuperclass() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				
				Buyer buyer = new Buyer();
				buyer.setName("buyer name");
				buyer.setEmail("buyer email");
				em.persist(buyer);
				
				Seller seller = new Seller();
				seller.setName("seller name");
				seller.setShopName("seller shop name");
				em.persist(seller);

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
	// end::testMappedSuperclass[]

	// tag::testEmbeddedId[]
	@Test
	public void testEmbeddedId() {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("samples-jpa");
			em = emf.createEntityManager();

			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				
				// NOTE:
				// There's no way to generate primary keys for `@EmbeddedId` automatically.
				ParentId parentId = new ParentId();
				parentId.setId1(1L);
				parentId.setId2(2L);
				
				Parent parent = new Parent();
				parent.setId(parentId);
				parent.setName("parent name");
				em.persist(parent);
				
				Parent foundParent = em.find(Parent.class, parentId);
				assertThat(foundParent, is(sameInstance(parent)));

				Child child = new Child();
				child.setParent(parent);
				em.persist(child);

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
	// end::testEmbeddedId[]

}
