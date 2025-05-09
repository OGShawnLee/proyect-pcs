package org.example.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.business.dto.ProjectDTO;
import org.example.business.dao.ProjectDAO;
import org.example.gui.Modal;

import java.sql.SQLException;
import java.util.List;

public class ReviewProjectListController extends Controller {
  private static final ProjectDAO PROJECT_DAO = new ProjectDAO();
  @FXML
  private TableView<ProjectDTO> tableProject;
  @FXML
  private TableColumn<ProjectDTO, String> columnID;
  @FXML
  private TableColumn<ProjectDTO, String> columnEmail;
  @FXML
  private TableColumn<ProjectDTO, String> columnName;
  @FXML
  private TableColumn<ProjectDTO, String> columnMethodology;
  @FXML
  private TableColumn<ProjectDTO, String> columnSector;
  @FXML
  private TableColumn<ProjectDTO, String> columnState;
  @FXML
  private TableColumn<ProjectDTO, String> columnCreatedAt;

  private void loadProjectList() {
    columnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
    columnEmail.setCellValueFactory(new PropertyValueFactory<>("IDOrganization"));
    columnMethodology.setCellValueFactory(new PropertyValueFactory<>("methodology"));
    columnSector.setCellValueFactory(new PropertyValueFactory<>("sector"));
    columnState.setCellValueFactory(new PropertyValueFactory<>("state"));
    columnCreatedAt.setCellValueFactory(new PropertyValueFactory<>("createdAt"));

    try {
      List<ProjectDTO> projectList = PROJECT_DAO.getAll();
      ObservableList<ProjectDTO> observableProjectList = FXCollections.observableList(projectList);
      tableProject.setItems(observableProjectList);
    } catch (SQLException e) {
      Modal.displayError(
        "No ha sido posible cargar información de académicos debido a un error de sistema."
      );
    }
  }

  public void navigateToRegisterProjectPage() {
    navigateFromThisPageTo("Registrar Proyecto", "RegisterProjectPage");
  }

  public static void navigateToProjectListPage(Stage currentStage) {
    navigateTo(currentStage, "Listado de Proyectos" , "ReviewProjectListPage");
  }

  public void navigateToManageProjectPage(ProjectDTO currentProject) {
    navigateToManagePage(
      getScene(),
      "Modificar Proyecto",
      "ManageProjectPage",
      currentProject
    );
  }

  @FXML
  private void initialize() {
    loadProjectList();
    setRowDoubleClickHandler(
      tableProject,
      currentProject -> {
        navigateToManageProjectPage(currentProject);
        return null;
      }
    );
  }
}
