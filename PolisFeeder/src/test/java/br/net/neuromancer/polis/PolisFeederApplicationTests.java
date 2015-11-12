package br.net.neuromancer.polis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.net.neuromancer.polis.domains.simple.DomainsFeederApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DomainsFeederApp.class)
public class PolisFeederApplicationTests {

	@Test
	public void contextLoads() {
	}

}
