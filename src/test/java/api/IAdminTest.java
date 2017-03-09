package api;

import api.core.impl.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by masoodmawlavizada on 3/7/17.
 */
public class IAdminTest {
    private IAdmin admin;

    @Before
    public void setup() {
        this.admin = new Admin();
    }

    @Test
    public void createClassMoreThan2Times() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.createClass("Test2", 2017, "Instructor", 15);
        this.admin.createClass("Test3", 2017, "Instructor", 15);
        assertFalse(this.admin.classExists("Test3", 2017));

    }

    @Test
    public void createClassSameClassNameDifferentInstructor() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.createClass("Test", 2017, "Instructor2", 15);
        assertEquals("Instructor2", this.admin.getClassInstructor("Test", 2017));

    }

    @Test
    public void changeCapacityFrom15To30() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 30);

        assertEquals(30, this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void changeCapacityFrom15To10ShouldFail() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 10);

        assertEquals(15, this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void classExists() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);

        assertTrue(this.admin.classExists("Test", 2017));
    }

    @Test
    public void getClassInstructor() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);

        assertEquals("Instructor", this.admin.getClassInstructor("Test", 2017));
    }

    @Test
    public void getClassCapacity() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);

        assertEquals(15, this.admin.getClassCapacity("Test", 2017));
    }

    @Test
    public void getClassCapacityWithNewMax() throws Exception {
        this.admin.createClass("Test", 2017, "Instructor", 15);
        this.admin.changeCapacity("Test", 2017, 35);
        this.admin.changeCapacity("Test", 2017, 10);
        assertEquals(35, this.admin.getClassCapacity("Test", 2017));
    }

}