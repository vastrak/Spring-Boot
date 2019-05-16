package com.vastrak.springboot001.repositories;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.vastrak.springboot001.domain.Article;
import com.vastrak.springboot001.domain.User;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * 
 * @author Christian
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryImpTest {
	private static final Log logger = LogFactory.getLog(UserRepositoryImpTest.class);
	private static User userLuis = null;
	private static Article articleLuis1 = null;
	private static Article articleLuis2 = null;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ArticleRepository articleRepository;

	public UserRepositoryImpTest() {
	}

	@Test
	public void test000_removeAllData() {
		userRepository.deleteAll();
		articleRepository.deleteAll();
		assertTrue(userRepository.count() == 0L);
		assertTrue(articleRepository.count() == 0L);
	}

	@Test
	public void test001_addUserAndArticle() {
		userLuis = new User();
		userLuis.setName("Luis");
		userLuis.setEmail("luis@aol.com");

		articleLuis1 = new Article();
		articleLuis1.setTitle("El primer artículo de Luis");
		articleLuis1.setBody("Lo primero que dijo Luis");
		articleLuis1.setUser(userLuis);
		userLuis.addArticle(articleLuis1);

		articleLuis2 = new Article();
		articleLuis2.setTitle("El segundo artículo de Luis");
		articleLuis2.setBody("Lo segundo y último que dijo Luis");
		articleLuis2.setUser(userLuis);
		userLuis.addArticle(articleLuis2);

		userLuis = (User) userRepository.save(userLuis);

		User foundUser = (User) userRepository.findOne(userLuis.getUser_id());

		assertNotNull(foundUser);
		assertTrue(foundUser.getUser_id().equals(userLuis.getUser_id()));

		List<Article> listArticlesLuis = foundUser.getArticles();

		assertNotNull(listArticlesLuis);
		assertTrue(listArticlesLuis.size() == 2);
		assertTrue(listArticlesLuis.contains(articleLuis1));

		logger.info("--> Usuario recuperado: " + foundUser);
		logger.info("--> Artículo recuperado : " + listArticlesLuis.get(0));
	}

	@Test
	public void test002_UpdateUser() {
		User foundUser = (User) userRepository.findOne(userLuis.getUser_id());

		String newEmail = "luisnewville@newville.com";
		foundUser.setEmail(newEmail);

		userRepository.save(foundUser);

		foundUser = (User) userRepository.findOne(userLuis.getUser_id());
		assertTrue(foundUser.getEmail().equals(newEmail));
	}

	@Test
	public void test003_UpdateArticle() {
		Article foundArticle = (Article) articleRepository.findOne(articleLuis1.getArticle_id());
		assertNotNull(foundArticle);

		String newTitle = "Modificado artículo de Luis";
		foundArticle.setTitle(newTitle);

		articleRepository.save(foundArticle);

		foundArticle = (Article) articleRepository.findOne(articleLuis1.getArticle_id());

		assertNotNull(foundArticle);
		assertTrue(foundArticle.getTitle().equals(newTitle));

		articleLuis1 = foundArticle;
	}

	@Test
	public void test004_removeUserArticle() {
		User foundUser = (User) userRepository.findOne(userLuis.getUser_id());
		List<Article> articles = foundUser.getArticles();

		boolean removed = articles.remove(articleLuis1);
		assertTrue(removed);

		userRepository.save(foundUser);

		Article foundArticle = (Article) articleRepository.findOne(articleLuis1.getArticle_id());
		assertNull(foundArticle);
		articleLuis1 = null;
	}

	@Test
	public void test005_removeUserAndAllHisArticles() {
		userRepository.delete(userLuis.getUser_id());

		User foundUser = (User) userRepository.findOne(userLuis.getUser_id());

		Article foundArticle = (Article) articleRepository.findOne(articleLuis2.getArticle_id());
		assertNull(foundUser);
		assertNull(foundArticle);
	}

	@Test
	public void test006_findUserbyName() {
		String name = "Rodolfo";
		User user1 = new User();
		user1.setName(name);
		User user2 = new User();
		user2.setName(name + " Natalio");

		userRepository.save(user1);
		userRepository.save(user2);

		User found = userRepository.findByName(name);

		assertNotNull(found);
		assertTrue(found.getName().equals(name));

		userRepository.deleteAll();
	}
}
