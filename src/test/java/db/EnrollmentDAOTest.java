package db;


import org.example.business.EnrollmentDTO;
import org.example.db.EnrollmentDAO;
import org.example.db.filter.FilterEnrollment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EnrollmentDAOTest {
  public static final EnrollmentDAO ENROLLMENT_DAO = new EnrollmentDAO();
  public static final EnrollmentDTO ENROLLMENT_DTO = new EnrollmentDTO.EnrollmentBuilder()
    .setIDCourse(CourseDAOTest.COURSE_DTO.getNRC())
    .setIDStudent(StudentDAOTest.STUDENT_DTO.getID())
    .build();

  public static void createOneTestEnrollment() throws SQLException {
    StudentDAOTest.createOneTestStudent();
    CourseDAOTest.createOneTestCourse();
    ENROLLMENT_DAO.createOne(ENROLLMENT_DTO);
  }

  public static void deleteOneTestEnrollment() throws SQLException {
    StudentDAOTest.deleteOneTestStudent();
    CourseDAOTest.deleteOneTestCourse();
    ENROLLMENT_DAO.deleteOne(
      new FilterEnrollment(
        ENROLLMENT_DTO.getIDStudent(),
        ENROLLMENT_DTO.getIDCourse()
      )
    );
  }

  @AfterEach
  public void tearDown() throws SQLException {
    deleteOneTestEnrollment();
  }

  @Test
  public void testCreateOneEnrollment() {
    assertDoesNotThrow(() -> {
      createOneTestEnrollment();

      EnrollmentDTO createdEnrollment = ENROLLMENT_DAO.getOne(
        new FilterEnrollment(
          ENROLLMENT_DTO.getIDStudent(),
          ENROLLMENT_DTO.getIDCourse()
        )
      );

      Assertions.assertNotNull(createdEnrollment);
      Assertions.assertEquals(ENROLLMENT_DTO.getIDCourse(), createdEnrollment.getIDCourse());
      Assertions.assertEquals(ENROLLMENT_DTO.getIDStudent(), createdEnrollment.getIDStudent());
      Assertions.assertInstanceOf(LocalDateTime.class, createdEnrollment.getCreatedAt());
    });
  }

  @Test
  public void testGetAllEnrollments() {
    assertDoesNotThrow(() -> {
      createOneTestEnrollment();

      List<EnrollmentDTO> createdEnrollment = ENROLLMENT_DAO.getAll();

      Assertions.assertNotNull(createdEnrollment);
      Assertions.assertFalse(createdEnrollment.isEmpty());
    });
  }

  @Test
  public void testGetOneEnrollment() {
    assertDoesNotThrow(() -> {
      createOneTestEnrollment();

      EnrollmentDTO createdEnrollment = ENROLLMENT_DAO.getOne(
        new FilterEnrollment(
          ENROLLMENT_DTO.getIDStudent(),
          ENROLLMENT_DTO.getIDCourse()
        )
      );

      Assertions.assertNotNull(createdEnrollment);
    });
  }

  @Test
  public void testUpdateOneEnrollment() {
    assertThrows(UnsupportedOperationException.class, () -> {
      createOneTestEnrollment();

      EnrollmentDTO updatedEnrollment = new EnrollmentDTO.EnrollmentBuilder()
        .setIDCourse(ENROLLMENT_DTO.getIDCourse())
        .setIDStudent(ENROLLMENT_DTO.getIDStudent())
        .build();

      ENROLLMENT_DAO.updateOne(updatedEnrollment);
    });
  }

  @Test
  public void testDeleteOneEnrollment() {
    assertDoesNotThrow(() -> {
      createOneTestEnrollment();
      EnrollmentDTO createdEnrollment = ENROLLMENT_DAO.getOne(
        new FilterEnrollment(
          ENROLLMENT_DTO.getIDStudent(),
          ENROLLMENT_DTO.getIDCourse()
        )
      );
      Assertions.assertNotNull(createdEnrollment);

      deleteOneTestEnrollment();
      createdEnrollment = ENROLLMENT_DAO.getOne(
        new FilterEnrollment(
          ENROLLMENT_DTO.getIDStudent(),
          ENROLLMENT_DTO.getIDCourse()
        )
      );
      Assertions.assertNull(createdEnrollment);
    });
  }
}
