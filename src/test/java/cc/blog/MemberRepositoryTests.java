package cc.blog;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cc.blog.config.DataSourceConfig;
import cc.blog.model.Member;
import cc.blog.model.MemberRoleType;
import cc.blog.repository.MemberRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataSourceConfig.class)
@Transactional
public class MemberRepositoryTests {

	@Autowired
	private MemberRepository repository;

	@Test
	public void testSaveAndFind() {
		Date currentDate = Calendar.getInstance().getTime();
		final int numberMembers = 10;

		IntStream.range(0, numberMembers).mapToObj(idx -> {
			String name = "test-name-" + idx;
			String email = name + "@email.com";
			String password = name + "!pass";

			return new Member(null, name, email, password, currentDate, MemberRoleType.ADMIN);
		}).forEach(member -> repository.save(member));

		Assert.assertEquals(numberMembers, repository.findAll().size());
	}
}
