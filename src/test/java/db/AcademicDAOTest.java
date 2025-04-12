package db;

import org.example.business.AcademicDTO;
import org.example.business.AccountDTO;
import org.example.db.AcademicDAO;
import org.example.db.AccountDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AcademicDAOTest {
  private final AcademicDAO ACADEMIC_DAO = new AcademicDAO();
  private final AccountDAO ACCOUNT_DAO = new AccountDAO();
  private final String ID = "00102";
  private final String EMAIL = "smith@uv.mx";
  private final String PASSWORD = "Password123";
  private final String NAME = "John";
  private final String PATERNAL_LAST_NAME = "Doe";
  private final String MATERNAL_LAST_NAME = "Smith";
  private final String ROLE = "PROFESSOR";
  private final AcademicDTO BASE_ACADEMIC_DTO = new AcademicDTO.AcademicBuilder()
    .setID(ID)
    .setEmail(EMAIL)
    .setName(NAME)
    .setPaternalLastName(PATERNAL_LAST_NAME)
    .setMaternalLastName(MATERNAL_LAST_NAME)
    .setRole(ROLE)
    .build();
  private final AccountDTO BASE_ACCOUNT_DTO = new AccountDTO(EMAIL, PASSWORD);

  private void createTestData() throws SQLException {
    ACCOUNT_DAO.create(BASE_ACCOUNT_DTO);
    ACADEMIC_DAO.create(BASE_ACADEMIC_DTO);
  }

  @AfterEach
  public void tearDown() throws SQLException {
    ACADEMIC_DAO.delete(ID);
    ACCOUNT_DAO.delete(EMAIL);
  }

  @Test
  public void testCreateAcademic() {
    assertDoesNotThrow(() -> {
      createTestData();

      AcademicDTO createdAcademic = ACADEMIC_DAO.get(ID);

      Assertions.assertNotNull(createdAcademic);
      Assertions.assertEquals(ID, createdAcademic.getID());
      Assertions.assertEquals(EMAIL, createdAcademic.getEmail());
      Assertions.assertEquals(NAME, createdAcademic.getName());
      Assertions.assertEquals(PATERNAL_LAST_NAME, createdAcademic.getPaternalLastName());
      Assertions.assertEquals(MATERNAL_LAST_NAME, createdAcademic.getMaternalLastName());
      Assertions.assertEquals(ROLE, createdAcademic.getRole());
    });
  }

  @Test
  public void testGetAllAcademics() {
    assertDoesNotThrow(() -> {
      createTestData();

      List<AcademicDTO> academicList = ACADEMIC_DAO.getAll();

      Assertions.assertNotNull(academicList);
      Assertions.assertFalse(academicList.isEmpty());
    });
  }

  @Test
  public void testGetAcademic() {
    assertDoesNotThrow(() -> {
      ACCOUNT_DAO.create(BASE_ACCOUNT_DTO);
      ACADEMIC_DAO.create(BASE_ACADEMIC_DTO);

      AcademicDTO academic = ACADEMIC_DAO.get(ID);

      Assertions.assertNotNull(academic);
    });
  }

  @Test
  public void testUpdateAcademic() {
    assertDoesNotThrow(() -> {
      createTestData();

      String updatedName = "John Halo";
      String updatedMaternalLastName = "Smithson";
      String updatedPaternalLastName = "Does";

      AcademicDTO updatedAcademic = new AcademicDTO.AcademicBuilder()
        .setID(ID)
        .setEmail(EMAIL)
        .setName(updatedName)
        .setPaternalLastName(updatedPaternalLastName)
        .setMaternalLastName(updatedMaternalLastName)
        .build();

      ACADEMIC_DAO.update(updatedAcademic);

      AcademicDTO retrievedAcademic = ACADEMIC_DAO.get(ID);

      Assertions.assertEquals(updatedMaternalLastName, retrievedAcademic.getMaternalLastName());
      Assertions.assertEquals(updatedPaternalLastName, retrievedAcademic.getPaternalLastName());
      Assertions.assertEquals(updatedName, retrievedAcademic.getName());
    });
  }

  @Test
  public void testDeleteAcademic() {
    assertDoesNotThrow(() -> {
      createTestData();

      ACCOUNT_DAO.delete(EMAIL);
      ACADEMIC_DAO.delete(ID);

      AcademicDTO deletedAcademic = ACADEMIC_DAO.get(ID);

      Assertions.assertNull(deletedAcademic);
    });
  }
}
