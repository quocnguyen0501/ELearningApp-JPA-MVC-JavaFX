package dao.impl;

import models.StepModel;
import org.junit.jupiter.api.*;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class StepDAOTest extends BaseDAO {

    private static StepDAO stepDAO;

    @BeforeAll
    static void beforeAll() {
        stepDAO = new StepDAO();
    }

    @AfterAll
    static void afterAll() {
        stepDAO.close();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void insert() {
    }

    @Test
    void updateDescription() {
    }

    @Test
    void updateDateComplete() {
    }

    @Test
    void updateUnCompleted() {
    }

    @Test
    void delete() {
    }

    @Test
    void selectById() {
    }

    @Test
    void selectByDescription() {
    }

    @Test
    void selectByTask() {
    }

    @Test
    void getAll() {
    }

    @Test
    void addTask() {
    }

    @Test
    void getNumberStepOfTask() {
    }

    @Test
    void getNumberStepCompleteOfTask() {
    }

    @Test
    void getNumberStepUnCompletedOfTask() {
    }

    @Test
    void selectStepCompleteByTask() {
    }

    @Test
    void selectStepUnCompletedByTask() {
    }
}