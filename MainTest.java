package BD1;

import static org.junit.jupiter.api.Assertions.*;

import Lab2.MyChain;
import org.junit.jupiter.api.*;

public class MainTest {

    Registration reg;

    @BeforeEach
    void setUp() {
        reg = new Registration(); // өгөгдлийг уншина
    }

    @Test
    @DisplayName("1️. Hicheeluudiin jagsaalt hooson bish eseh")
    void testSubjectListNotEmpty() {
        assertTrue(reg.subjectList.size() > 0, "Subject list must not be empty");
    }

    @Test
    @DisplayName("2️. Mergejliin jagsaalt hooson bish eseh")
    void testMajorListNotEmpty() {
        assertTrue(reg.majorList.size() > 0, "Major list must not be empty");
    }

    @Test
    @DisplayName("3️. Oyutnii jagsaalt hooson bish eseh")
    void testStudentListNotEmpty() {
        assertTrue(reg.studentList.size() > 0, "Student list must not be empty");
    }

    @Test
    @DisplayName("4️. GPA tootsoolol 0-4-iin hoorond baigaa eseh")
    void testGPARange() {
        for (int i = 0; i < reg.studentList.size(); i++) {
            float gpa = ((Student) reg.studentList.get(i)).GPA;
            assertTrue(gpa >= 0 && gpa <= 4.0, "GPA must be between 0.0 and 4.0");
        }
    }

    @Test
    @DisplayName("5️. F unelgeetei 3-aas deesh hicheeltei oyutan baigaa eseh")
    void testHas3FStudents() {
        boolean found = false;
        for (int i = 0; i < reg.studentList.size(); i++) {
            int f = 0;
            Student s = (Student) reg.studentList.get(i);
            for (int j = 0; j < s.lessons.size(); j++) {
                Lessons l = (Lessons) s.lessons.get(j);
                if (l.score < 60) f++;
            }
            if (f >= 3) {
                found = true;
                break;
            }
        }
        assertTrue(found, "At least one student should have 3 or more F grades");
    }

    @Test
    @DisplayName("6️. Hicheeltei holbootoi dungiin ugugdul butsaah eseh")
    void testGetLessons() {
        if (reg.studentList.size() > 0) {
            String studentCode = ((Student) reg.studentList.get(0)).code;
            MyChain lessons = reg.GetLessons(studentCode);
            assertNotNull(lessons, "Lessons must not be null");
            assertTrue(lessons.size() > 0, "Student must have at least one lesson");
        }
    }
}
