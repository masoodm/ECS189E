package api;

import api.core.impl.Admin;
import api.core.impl.Instructor;
import api.core.impl.Student;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by masoodmawlavizada on 3/8/17.
 */
public class IStudentTest {
    private IAdmin admin;
    private IInstructor instructor;
    private IStudent student;


    @Before
    public void setup() {
        this.admin = new Admin();
        this.instructor = new Instructor();
        this.student = new Student();
    }

    @Test
    public void registerForClassNormally() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);

        assertTrue(this.student.isRegisteredFor("Masood", "Test", 2017));


    }

    @Test
    public void registerForClassWhenClassDoesNotExist() throws Exception {
        this.admin.createClass("Test2", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test2", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);

        assertFalse(this.student.isRegisteredFor("Masood", "Test", 2017));


    }

    @Test
    public void registerForClassWhenOverCapacityShouldReturnFalse() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 2);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);
        this.student.registerForClass("Masood2", "Test", 2017);
        this.student.registerForClass("Masood3", "Test", 2017);

//Masood3 Should not be registered because it would be over capacity
        assertFalse(this.student.isRegisteredFor("Masood3", "Test", 2017));


    }


    @Test
    public void dropClassNormally() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);

        this.student.dropClass("Masood", "Test", 2017);

        //Masood Should Not be registered for this class
        assertFalse(this.student.isRegisteredFor("Masood", "Test", 2017));

    }

    @Test
    public void dropClassThatAlreadyEnded() throws Exception {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2016, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2016);

        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);
//This class should have ended already
        this.student.dropClass("Masood", "Test", 2016);

        //Masood Should still be registered for this class
        assertTrue(this.student.isRegisteredFor("Masood", "Test", 2016));

    }

    @Test
    public void dropClassStudentIsNotRegisteredTo() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");

        this.student.dropClass("Masood", "Test", 2017);

        //Masood Should Not be registered for this class
        assertFalse(this.student.isRegisteredFor("Masood", "Test", 2017));

    }

    @Test
    public void submitHomeworkNormal() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);

        this.student.submitHomework("Masood", "HW", "Answers", "Test", 2017);

        assertTrue(this.student.hasSubmitted("Masood", "HW", "Test", 2017));
    }

    @Test
    public void submitHomeworkWhenHWDoesNotExist() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);

        this.student.submitHomework("Masood", "HW2", "Answers", "Test", 2017);

        assertFalse(this.student.hasSubmitted("Masood", "HW2", "Test", 2017));
    }

    @Test
    public void submitHomeworkWhenStudentNotRegistered() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");

        this.student.submitHomework("Masood", "HW", "Answers", "Test", 2017);

        assertFalse(this.student.hasSubmitted("Masood", "HW", "Test", 2017));
    }

    @Test
    public void submitHomeworkForClassInOtherYear() throws Exception {
        this.admin.createClass("Test", 2016, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2016, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2016);

        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);

        this.student.submitHomework("Masood", "HW", "Answers", "Test", 2016);

        assertFalse(this.student.hasSubmitted("Masood", "HW", "Test", 2016));
    }

    @Test
    public void isRegisteredForClassWithCorrectInfo() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);

        assertTrue(this.student.isRegisteredFor("Masood", "Test", 2017));
    }

    @Test
    public void isRegisteredForClassWithIncorrectClassInfo() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);

        assertFalse(this.student.isRegisteredFor("Masood", "Test2", 2017));
    }

    @Test
    public void hasSubmitted() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.instructor.addHomework("Instructor","Test", 2017, "HW", "Do your homework");
        this.student.registerForClass("Masood", "Test", 2017);
        this.student.submitHomework("Masood", "HW", "Answers", "Test", 2016);

        assertTrue(this.student.hasSubmitted("Masood", "HW", "Test", 2017));
    }

}