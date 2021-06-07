package ru.sazonov.future1;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import ru.sazonov.future1.mappers.LordMapper;
import ru.sazonov.future1.repositories.LordRepository;
import ru.sazonov.future1.repositories.PlanetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractTest {

    @MockBean
    protected LordRepository lordRepository;
    @MockBean
    protected PlanetRepository planetRepository;

}
