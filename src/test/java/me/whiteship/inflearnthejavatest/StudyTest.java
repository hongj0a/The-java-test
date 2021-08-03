package me.whiteship.inflearnthejavatest;

import me.whiteship.inflearnthejavatest.domain.Study;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StudyTest {

    int value = 1;

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension =
            new FindSlowTestExtension(1000L);

    @Order(2)
    @FastTest
    @DisplayName("스터디 만들기 fast ^^")
    void create_new_study() throws InterruptedException{
        Thread.sleep(1005);
        System.out.println(value++);
        System.out.println(this);
        Study actual = new Study(1);
        /*String test_env = System.getenv("TEST_ENV");
        System.out.println(test_env);
        assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("TEST_ENV")));
*/
        /*assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            Thread.sleep(300);
        });
        // TODO ThreadLocal*/

       /* IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Study(-10));
        assertEquals("limit은 0보다 커야한다.", exception.getMessage());*/

      /*  assertAll(
            () -> assertNotNull(study),
            () -> assertEquals(StudyStatus.ENDED, study.getStatus(), "스터디를 처음 만들면 상태값이 DRAFT 여야 한다."),
            () -> assertTrue(study.getLimit() > 0, "스터디 최대 참석 가능 인원은 0보다 커야 한다.")
        );*/
        System.out.println("fast");
    }

    @Order(1)
    @SlowTest
    @DisplayName("스터디 만들기 slow \uD83D\uDE31")
    void create_new_study_again() throws  InterruptedException {
        Thread.sleep(1006L);
        System.out.println("slow" + value++);
        System.out.println(this);
    }

    @Order(3)
    @DisplayName("스터디 반복 만들기")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest(RepetitionInfo repetitionInfo) {
        System.out.println("test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @Order(4)
    @DisplayName("스터디 또 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 'java study'", "20, 'spring study'"})
    void parameterizedTest(Integer limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study);
    }

    @DisplayName("스터디 또또 만들기")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @CsvSource({"10, 'java study'", "20, 'spring study'"})
    @Disabled
    void parameterizedTest2(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) {
            return new Study(accessor.getInteger(0), accessor.getString(1));
        }
    }

    static class StudyConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEeach() {
        System.out.println("Before Each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }

}