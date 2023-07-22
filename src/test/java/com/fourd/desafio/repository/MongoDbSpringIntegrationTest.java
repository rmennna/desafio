package com.fourd.desafio.repository;

import com.fourd.desafio.domain.Aula;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.assertj.core.api.Assertions;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

//@DataMongoTest
//@ExtendWith(SpringExtension.class)
//class MongoDbSpringIntegrationTest {
//
//    @Autowired
//    MongoTemplate mongoTemplate;
//
//    /**
//     * public static void afterAll() {} -> Este método é executado quando todos os testes tiverem terminado
//     */
//    @AfterAll
//    public static void afterAll() {
//        System.out.println("ALL TESTS TERMINATED!");
//    }
//
//    /**
//     * public void after(){} -> Este método é executado quando cada teste for terminado
//     */
//    @AfterEach
//    public void after() {
//        System.out.println("A TEST TERMINATED!");
//    }
//
//    @Test
//    @DisplayName("Save persists aula when successful")
//    void save_PersistAula_WhenSuccessful() {
//        Aula aulaToBeSaved = createAula();
//        Aula aulaSaved = mongoTemplate.save(aulaToBeSaved);
//
//        Assertions.assertThat(aulaSaved).isNotNull();
//        Assertions.assertThat(aulaSaved.getTitulo()).isNotNull();
//        Assertions.assertThat(aulaSaved.getTitulo()).isEqualTo(aulaToBeSaved.getTitulo());
//        Assertions.assertThat(aulaSaved.getDescricao()).isNotNull();
//        Assertions.assertThat(aulaSaved.getDescricao()).isEqualTo(aulaToBeSaved.getDescricao());
//        Assertions.assertThat(aulaSaved.getIdProfessor()).isNotNull();
//    }
//
//    @Test
//    @DisplayName("Save updates aula when successful")
//    void save_UpdateAula_WhenSuccessful() {
//        Aula aulaToBeSaved = createAula();
//        Aula aulaSaved = mongoTemplate.save(aulaToBeSaved);
//
//        aulaSaved.setTitulo("Aula 01 update");
//        aulaSaved.setDescricao("Descrição Aula 01 update");
//        Aula aulaUpdate = mongoTemplate.save(aulaSaved);
//
//        Assertions.assertThat(aulaUpdate).isNotNull();
//        Assertions.assertThat(aulaUpdate.getTitulo()).isNotNull();
//        Assertions.assertThat(aulaUpdate.getTitulo()).isEqualTo(aulaSaved.getTitulo());
//        Assertions.assertThat(aulaUpdate.getDescricao()).isNotNull();
//        Assertions.assertThat(aulaUpdate.getDescricao()).isEqualTo(aulaSaved.getDescricao());
//        Assertions.assertThat(aulaUpdate.getIdProfessor()).isNotNull();
//    }
//
//    private Aula createAula() {
//        return Aula.builder()
//                .titulo("Aula 01")
//                .descricao("Descrição Aula 01")
//                .dataPrevista(new Date())
//                .idProfessor("123456789")
//                .build();
//    }
//
//}

class MongoDbSpringIntegrationTest {
    private static final String CONNECTION_STRING = "mongodb://%s:%d";

    private MongodExecutable mongodExecutable;
    private MongoTemplate mongoTemplate;


    @AfterEach
    void clean() {
        mongodExecutable.stop();
    }

    @BeforeEach
    void setup() throws Exception {
        String ip = "localhost";
        int port = 27017;

        ImmutableMongodConfig mongoConfig = MongodConfig
                .builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongodExecutable = starter.prepare(mongoConfig);
        mongodExecutable.start();
        mongoTemplate = new MongoTemplate(MongoClients.create(String.format(CONNECTION_STRING, ip, port)), "test");
    }

    @Test
    @DisplayName("Save persists aula when successful")
    void save_PersistAula_WhenSuccessful() {
        Aula aulaToBeSaved = createAula();
        Aula aulaSaved = mongoTemplate.save(aulaToBeSaved);

        Assertions.assertThat(aulaSaved).isNotNull();
        Assertions.assertThat(aulaSaved.getTitulo()).isNotNull();
        Assertions.assertThat(aulaSaved.getTitulo()).isEqualTo(aulaToBeSaved.getTitulo());
        Assertions.assertThat(aulaSaved.getDescricao()).isNotNull();
        Assertions.assertThat(aulaSaved.getDescricao()).isEqualTo(aulaToBeSaved.getDescricao());
        Assertions.assertThat(aulaSaved.getIdProfessor()).isNotNull();
    }

    @Test
    @DisplayName("Save updates aula when successful")
    void save_UpdateAula_WhenSuccessful() {
        Aula aulaToBeSaved = createAula();
        Aula aulaSaved = mongoTemplate.save(aulaToBeSaved);

        aulaSaved.setTitulo("Aula 01 update");
        aulaSaved.setDescricao("Descrição Aula 01 update");
        Aula aulaUpdate = mongoTemplate.save(aulaSaved);

        Query query = new Query(Criteria.where("titulo").is("Aula 01 update"));
        Aula aula = mongoTemplate.findOne(query, Aula.class);

        Assertions.assertThat(aula).isNotNull();
        Assertions.assertThat(aula.getTitulo()).isNotNull();
        Assertions.assertThat(aula.getTitulo()).isEqualTo(aulaSaved.getTitulo());
        Assertions.assertThat(aula.getDescricao()).isNotNull();
        Assertions.assertThat(aula.getDescricao()).isEqualTo(aulaSaved.getDescricao());
        Assertions.assertThat(aula.getIdProfessor()).isNotNull();
    }

    private Aula createAula() {
        return Aula.builder()
                .titulo("Aula 01")
                .descricao("Descrição Aula 01")
                .dataPrevista(new Date())
                .idProfessor("123456789")
                .build();
    }
}