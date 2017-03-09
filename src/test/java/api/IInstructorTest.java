package api;

import api.core.impl.Admin;
import api.core.impl.Instructor;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by masoodmawlavizada on 3/8/17.
 */
public class IInstructorTest {
    private IAdmin admin;
    private IInstructor instructor;


    @Before
    public void setup() {
        this.admin = new Admin();
        this.instructor = new Instructor();
    }

    @Test
    public void addHomeworkWithWrongInstructorName() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);

        this.instructor.addHomework("WrongInstructorName","Test", 2017, "HW", "Do your homework");
        //Should return false because the instructor name was incorrect
        assertFalse(this.instructor.homeworkExists("Test", 2017, "HW"));
    }

    @Test
    public void addHomeworkWithRightInstructorName() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);

        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        assertTrue(this.instructor.homeworkExists("Test", 2017, "HW"));
    }




    @Test
    public void assignGradeWhenInstructorNameIsWrong() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.instructor.assignGrade("WrongInstructorName", "Test", 2017, "HW", "Masood", 99);

        assertNull(this.instructor.getGrade("Test", 2017, "HW", "Masood"));

    }

    @Test
    public void assignGradeWhenInstructorNameIsRight() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.instructor.assignGrade("Instructor", "Test", 2017, "HW", "Masood", 99);

        assertNotNull(this.instructor.getGrade("Test", 2017, "HW", "Masood"));

    }

    @Test
    public void assignGradeWhenAssignmentWasNeverAssigned() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.instructor.assignGrade("WrongInstructorName", "Test", 2017, "HW", "Masood", 99);

        assertNull(this.instructor.getGrade("Test", 2017, "HW", "Masood"));

    }

    @Test
    public void assignGradeWhenHomeworkWasNeverAssigned() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.instructor.assignGrade("Instructor", "Test", 2017, "HW", "Masood", 99);

        assertNull(this.instructor.getGrade("Test", 2017, "HWwww", "Masood"));

    }

    @Test
    public void assignGradeWhenStudentDidNotSubmit() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.instructor.assignGrade("Instructor", "Test", 2017, "HW", "Masood", 99);

        assertNull(this.instructor.getGrade("Test", 2017, "HWwww", "MasoodFalse"));

    }



    @Test
    public void homeworkExistsWhenItShouldExist() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");

        assertTrue(this.instructor.homeworkExists("Test", 2017, "HW"));
    }

    @Test
    public void homeworkDoesNotExistBecauseOfWrongInstructorName() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("WrongInstructorName","Test", 2017, "HW", "Do your homework");

        assertFalse(this.instructor.homeworkExists("Test", 2017, "HW2"));
    }


    @Test
    public void getGradeWithCorrentInfoShouldReturnNotNull() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.instructor.assignGrade("Instructor", "Test", 2017, "HW", "Masood", 99);

        assertNotNull(this.instructor.getGrade("Test", 2017, "HW", "Masood"));
    }

    @Test
    public void getGradeWithIncorrentInfoShouldReturnNULL() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.instructor.assignGrade("Instructor", "Test", 2017, "HW", "Masood", 99);

        assertNull(this.instructor.getGrade("Test", 2017, "HW2", "Masood"));
    }

}