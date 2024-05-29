package application;

import java.util.ArrayList;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.collections.FXCollections;

public class Main extends Application {

	private static final double GOLDEN_RATIO = 1.618;

	private static final GUI ELEMENTS = new GUI();

	private static final Admin ADMIN = new Admin();

	private User USER;

	private Customer CUSTOMERS = new Customer();

	private Company COMPANIES = new Company();

	@Override
	public void start(Stage login_stage) {

		// ====//

		// TITLE//
		Text LOGIN_TEXT = new Text("Log in to PayMaster");
		LOGIN_TEXT.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		// ====//

		// STAGES//

		// SCENES//
		Scene LOGIN_SCENE;

		// ====//

		// TEXTFIELDS_&_PROPERTIES//
		TextField USERNAME_TF = new TextField();
		USERNAME_TF.setPromptText("  username");
		USERNAME_TF.setStyle(ELEMENTS.TF("200"));

		PasswordField PASSWORD_F = new PasswordField();
		PASSWORD_F.setPromptText("  password");
		PASSWORD_F.setPrefWidth(50);
		PASSWORD_F.setStyle(ELEMENTS.TF("200"));

		TextField SHOW_PASS_TF = new TextField();
		SHOW_PASS_TF.setPromptText("  password");
		SHOW_PASS_TF.setPrefWidth(50);
		SHOW_PASS_TF.setStyle(ELEMENTS.TF("200"));
		SHOW_PASS_TF.setVisible(false);

		// ====//

		// IMAGES//
		Image SHOW_IMAGE = new Image("Show.png"); // .
		ImageView SHOW = ELEMENTS.IMAGE(SHOW_IMAGE, 20, 20);

		Image HIDE_IMAGE = new Image("Hide.png");// .
		ImageView HIDE = ELEMENTS.IMAGE(HIDE_IMAGE, 20, 20);

		Image LOCK_ICON_IMAGE = new Image("Lock.png");// .
		ImageView LOCK_ICON = ELEMENTS.IMAGE(LOCK_ICON_IMAGE, 20, 20);
		LOCK_ICON.setTranslateX(-105);

		Image USER_ICON_IMAGE = new Image("User.png");// .
		ImageView USER_ICON = ELEMENTS.IMAGE(USER_ICON_IMAGE, 20, 20);
		USER_ICON.setTranslateX(-105);

		Image LOGO_IMAGE = new Image("Logo.png");// .
		ImageView LOGO = ELEMENTS.IMAGE(LOGO_IMAGE, 130, 100);
		LOGO.setTranslateX(-10);
		LOGO.setTranslateY(-20);

		Image APP_LOGO = new Image("AppIcon.jpg");// .

		// ====//

		// ALERTS//
		Alert WRONG_INFO = new Alert(AlertType.WARNING);
		WRONG_INFO.setTitle("");
		WRONG_INFO.setHeaderText(null);
		WRONG_INFO.setContentText("wrong info");

		// ====//

		// LABELS//
		Label ACCOUNT_CREATION_LABEL = new Label("Don't have an account? ");
		ACCOUNT_CREATION_LABEL.setTranslateY(6);
		ACCOUNT_CREATION_LABEL.setTranslateX(8);
		ACCOUNT_CREATION_LABEL.setStyle("-fx-font-weight: 100;");

		// ====//

		// BUTTONS_&_PROPERTIES//
		Button LOGIN_BTN = new Button("Login");

		Button ACCOUNT_CREATION_BTN = new Button("Create an account");
		ACCOUNT_CREATION_BTN.setStyle("-fx-background-color: transparent;" + "-fx-border-color: transparent;"
				+ "-fx-text-fill: black;" + "-fx-underline: false;" + "-fx-font-weight: 700;");

		ToggleButton SHOW_PASS_BTN = new ToggleButton();
		SHOW_PASS_BTN.setStyle("-fx-background-color: transparent;");
		SHOW_PASS_BTN.setTranslateX(100);
		SHOW_PASS_BTN.setGraphic(SHOW);

		// ====//

		// STAGES_FROM_METHODS//

		final Stage USER_STAGE = USER_STAGE();
		final Stage ACCOUNT_CREATION_STAGE = ACCOUNT_CREATION_STAGE();
		final Stage CUSTOMER_USER_STAGE = CUSTOMER_USER_STAGE();
		final Stage COMPANY_USER_STAGE = COMPANY_USER_STAGE();

		// ====//

		// ACTION_HANDLING//
		LOGIN_BTN.setOnAction(e -> {
			if (ADMIN.search(USERNAME_TF.getText(), PASSWORD_F.getText()) == null) {
				WRONG_INFO.showAndWait();
			} else if (ADMIN.search(USERNAME_TF.getText(), PASSWORD_F.getText()).getUserID() % 10 == 0) {
				USER = (Admin) ADMIN.search(USERNAME_TF.getText(), PASSWORD_F.getText());
				Platform.setImplicitExit(false);
				login_stage.hide();
				USER_STAGE.show();
			} else if (ADMIN.search(USERNAME_TF.getText(), PASSWORD_F.getText()).getUserID() % 10 == 1) {
				USER = (Customer) ADMIN.search(USERNAME_TF.getText(), PASSWORD_F.getText());
				Platform.setImplicitExit(false);
				login_stage.hide();
				CUSTOMER_USER_STAGE.show();

			} else if (ADMIN.search(USERNAME_TF.getText(), PASSWORD_F.getText()).getUserID() % 10 == 2) {
				USER = (Company) ADMIN.search(USERNAME_TF.getText(), PASSWORD_F.getText());
				Platform.setImplicitExit(false);
				login_stage.hide();
				COMPANY_USER_STAGE.show();
			}

		});
		ACCOUNT_CREATION_BTN.setOnAction(e -> {
			Platform.setImplicitExit(false);
			login_stage.hide();
			ACCOUNT_CREATION_STAGE.show();

		});

		ACCOUNT_CREATION_BTN.setOnMouseEntered(e -> {
			ACCOUNT_CREATION_BTN.setStyle("-fx-background-color: transparent;" + "-fx-border-color: transparent;"
					+ "-fx-text-fill: darkBlue;" + "-fx-underline: true;" + "-fx-font-weight: 700;");
		});

		ACCOUNT_CREATION_BTN.setOnMouseExited(e -> {
			ACCOUNT_CREATION_BTN.setStyle("-fx-background-color: transparent;" + "-fx-border-color: transparent;"
					+ "-fx-text-fill: black;" + "-fx-underline: false;" + "-fx-font-weight: 700;");
		});

		SHOW_PASS_BTN.setOnAction(e -> {
			if (SHOW_PASS_BTN.isSelected()) {
				SHOW_PASS_BTN.setGraphic(HIDE);
				SHOW_PASS_TF.setText(PASSWORD_F.getText());
				PASSWORD_F.setVisible(false);
				SHOW_PASS_TF.setVisible(true);
			} else {
				SHOW_PASS_BTN.setGraphic(SHOW);
				PASSWORD_F.setText(SHOW_PASS_TF.getText());
				PASSWORD_F.setVisible(true);
				SHOW_PASS_TF.setVisible(false);
			}
		});

		// ====//

		// PANES_&_PROPERTIES//
		VBox LOGO_BOX = new VBox(LOGO, LOGIN_TEXT);
		LOGO_BOX.setAlignment(Pos.CENTER);

		HBox ACC_CREATION_BOX = new HBox(ACCOUNT_CREATION_LABEL, ACCOUNT_CREATION_BTN);
		ACC_CREATION_BOX.setTranslateX(55);

		StackPane USERNAME_PANE = new StackPane(USERNAME_TF, USER_ICON);
		StackPane PASS_PANE = new StackPane(LOCK_ICON, PASSWORD_F, SHOW_PASS_TF, SHOW_PASS_BTN);
		VBox PASSWORD_BOX = new VBox(5, PASS_PANE);

		HBox BTNS = new HBox(20, LOGIN_BTN);
		BTNS.setAlignment(Pos.CENTER);

		VBox PANE = new VBox(15, LOGO_BOX, USERNAME_PANE, PASSWORD_BOX, BTNS, ACC_CREATION_BOX);
		PANE.setAlignment(Pos.CENTER);
		PANE.setPadding(new Insets(20));
		PANE.setStyle(
				"-fx-background-color: linear-gradient(to right, #005073, #71c7ec);" + "-fx-background-radius: 20;");

		// ====//

		// SCENE_PROPERTIES_&_STAGE//
		LOGIN_SCENE = new Scene(PANE, 400, 400);
		LOGIN_SCENE.setFill(Color.TRANSPARENT);

		// ====//

		login_stage.initStyle(StageStyle.TRANSPARENT);
		login_stage.getIcons().add(APP_LOGO);
		login_stage.setTitle("Registration");
		login_stage.setScene(LOGIN_SCENE);
		login_stage.show();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Stage ACCOUNT_CREATION_STAGE() {
		// STAGES_&_SCENES//
		Stage acc_creation_stage = new Stage();

		Scene ACCOUNT_CREATION_SCENE;

		// TITLE//
		Text CREATE_ACC_TEXT = new Text("Get Started");
		CREATE_ACC_TEXT.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		Text CREATE_ACC_SUP_TEXT = new Text("Mastering Your Billing Needs");
		CREATE_ACC_SUP_TEXT.setFont(Font.font("Arial", FontWeight.BOLD, 20 / GOLDEN_RATIO));

		// TEXTFIELDS_&_PROPERTIES//
		TextField FNAME_TF = new TextField();
		FNAME_TF.setStyle(ELEMENTS.TF("150"));
		FNAME_TF.setPromptText(" First Name");

		TextField LNAME_TF = new TextField();
		LNAME_TF.setStyle(ELEMENTS.TF("150"));
		LNAME_TF.setPromptText(" Last Name");

		TextField USERNAME_TF = new TextField();
		USERNAME_TF.setStyle(ELEMENTS.TF("150"));
		USERNAME_TF.setPromptText(" Username");

		PasswordField PASSWORD_F = new PasswordField();
		PASSWORD_F.setPromptText(" Password");
		PASSWORD_F.setPrefWidth(50);
		PASSWORD_F.setStyle(ELEMENTS.TF("150"));

		TextField SHOW_PASS_TF = new TextField();
		SHOW_PASS_TF.setStyle(ELEMENTS.TF("150"));
		SHOW_PASS_TF.setPromptText(" password");
		SHOW_PASS_TF.setPrefWidth(50);
		SHOW_PASS_TF.setVisible(false);

		TextField BDATE_TF = new TextField();
		BDATE_TF.setPromptText(" Birth Date");
		BDATE_TF.setStyle(ELEMENTS.TF("150"));

		DatePicker BDATE_DP = new DatePicker();
		BDATE_DP.setStyle(ELEMENTS.TF("150"));
		BDATE_DP.setVisible(false);

		ObservableList<String> GENDER_LIST = FXCollections.observableArrayList("Male", "Female");

		ComboBox GENDER_CB = new ComboBox(GENDER_LIST);
		GENDER_CB.setStyle(ELEMENTS.TF("150") + "-fx-text-fill: gray;");

		// ====//

		// ALERTS//
		Alert ALREADY_USED = new Alert(AlertType.INFORMATION);
		ALREADY_USED.setTitle("");
		ALREADY_USED.setHeaderText(null);
		ALREADY_USED.setContentText("Already used");

		Alert INCOMPLETE_INFO = new Alert(AlertType.INFORMATION);
		INCOMPLETE_INFO.setTitle("");
		INCOMPLETE_INFO.setHeaderText(null);
		INCOMPLETE_INFO.setContentText("Please fill all the spaces");

		// ====//
		// IMAGES//

		Image SHOW_IMAGE = new Image("Show.png");// .
		ImageView SHOW = ELEMENTS.IMAGE(SHOW_IMAGE, 20, 20);

		Image HIDE_IMAGE = new Image("Hide.png");// .
		ImageView HIDE = ELEMENTS.IMAGE(HIDE_IMAGE, 20, 20);

		Image LOCK_ICON_IMAGE = new Image("Lock.png");// .
		ImageView LOCK_ICON = ELEMENTS.IMAGE(LOCK_ICON_IMAGE, 20, 20);
		LOCK_ICON.setTranslateX(-105);

		Image USER_ICON_IMAGE = new Image("User.png");// .
		ImageView USER_ICON = ELEMENTS.IMAGE(USER_ICON_IMAGE, 20, 20);
		USER_ICON.setTranslateX(-105);

		Image LOGO_IMAGE = new Image("Logo.png");// .
		ImageView LOGO = ELEMENTS.IMAGE(LOGO_IMAGE, 130, 100);
		LOGO.setTranslateX(-10);
		LOGO.setTranslateY(-20);

		Image BACKGROUND = new Image("Background 2.PNG.jpg");
		ImageView BACKGROUND_IMAGE = ELEMENTS.IMAGE(BACKGROUND, 300, 400);

		Image APP_LOGO = new Image("AppIcon.jpg");

		// ====//

		/// LABELS//
		Label GENDER_LABEL = new Label(" Gender");
		GENDER_LABEL.setStyle("-fx-text-fill:#A9A9A9");
		GENDER_LABEL.setTranslateX(-37);

		// ====//

		// SHAPES_&_PROPERTIES//
		Rectangle OVERLAY = new Rectangle(BACKGROUND_IMAGE.getFitWidth(), BACKGROUND_IMAGE.getFitHeight(),
				Color.rgb(255, 255, 255, 0.5));
		OVERLAY.setStyle("-fx-background-radius: 30");
		OVERLAY.setArcHeight(20);
		OVERLAY.setArcWidth(20);

		Rectangle BACKGROUND_REC = new Rectangle(BACKGROUND_IMAGE.getFitWidth(), BACKGROUND_IMAGE.getFitHeight());
		BACKGROUND_REC.setArcHeight(20);
		BACKGROUND_REC.setArcWidth(20);
		BACKGROUND_REC.setFill(new ImagePattern(BACKGROUND));

		// ====//

		// BUTTONS_&_PROPERTIES//
		Button SIGNUP_BTN = new Button("Sign Up");

		ToggleButton SHOW_PASS_BTN = new ToggleButton();
		SHOW_PASS_BTN.setStyle("-fx-background-color: transparent;");
		SHOW_PASS_BTN.setTranslateX(65);
		SHOW_PASS_BTN.setGraphic(SHOW);

		// ====//

		// ACTION_HANDLING_&_CONDTIONS//

		SIGNUP_BTN.setOnAction(e -> {
			if (FNAME_TF.getText().isEmpty() || LNAME_TF.getText().isEmpty() || USERNAME_TF.getText().isEmpty()
					|| PASSWORD_F.getText().isEmpty() || BDATE_TF.getText().isEmpty() || GENDER_CB.getValue() == null
					|| GENDER_CB.getValue().toString().isEmpty()) {

				INCOMPLETE_INFO.showAndWait();

			} else if (ADMIN.Main_Info_Checker(USERNAME_TF.getText())
					|| ADMIN.Main_Info_Checker(PASSWORD_F.getText())) {
				ALREADY_USED.showAndWait();
			} else {
				Customer NEW_CUSTOMER = new Customer(FNAME_TF.getText(), LNAME_TF.getText(), USERNAME_TF.getText(),
						PASSWORD_F.getText(), BDATE_TF.getText(), GENDER_CB.getValue().toString());
			}
		});

		BDATE_DP.setOnAction(event -> {
			BDATE_TF.setText(BDATE_DP.getEditor().getText());
			BDATE_DP.setVisible(false);
			BDATE_TF.setVisible(true);
		});

		BDATE_TF.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				BDATE_DP.setVisible(true);
				BDATE_TF.setVisible(false);
			}

		});

		GENDER_CB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				GENDER_LABEL.setVisible(false);
			} else {
				GENDER_LABEL.setVisible(true);
			}
		});

		SHOW_PASS_BTN.setOnAction(e -> {
			if (SHOW_PASS_BTN.isSelected()) {
				SHOW_PASS_BTN.setGraphic(HIDE);
				SHOW_PASS_TF.setText(PASSWORD_F.getText());
				PASSWORD_F.setVisible(false);
				SHOW_PASS_TF.setVisible(true);
			} else {
				SHOW_PASS_BTN.setGraphic(SHOW);
				PASSWORD_F.setText(SHOW_PASS_TF.getText());
				PASSWORD_F.setVisible(true);
				SHOW_PASS_TF.setVisible(false);
			}
		});

		// ====//

		// PANES_&_PROPERTIES//

		HBox NAMES = new HBox(15, FNAME_TF, LNAME_TF);
		NAMES.setPadding(new Insets(20));

		StackPane PASS_PANE = new StackPane(PASSWORD_F, SHOW_PASS_TF, SHOW_PASS_BTN);
		HBox USER_KEY_INFO = new HBox(15, USERNAME_TF, PASS_PANE);
		USER_KEY_INFO.setPadding(new Insets(20));

		StackPane GENDER_PANE = new StackPane(GENDER_LABEL, GENDER_CB);
		StackPane BDATE_PANE = new StackPane(BDATE_DP, BDATE_TF);
		HBox USER_EXTRA_INFO = new HBox(15, BDATE_PANE, GENDER_PANE);
		USER_EXTRA_INFO.setPadding(new Insets(20));

		VBox RIGHT = new VBox(15, NAMES, USER_KEY_INFO, USER_EXTRA_INFO, SIGNUP_BTN);
		RIGHT.setStyle("-fx-background-color: transparent;" + "-fx-min-height: 400px;" + "-fx-max-height: 400px;"
				+ "-fx-min-width: 300px;" + "-fx-max-width: 300px;" + "-fx-background-radius: 0 20 20 0;");
		RIGHT.setTranslateX(110);
		RIGHT.setTranslateY(40);
		RIGHT.setPadding(new Insets(10));

		VBox TEXT_PANE = new VBox(LOGO, CREATE_ACC_TEXT, CREATE_ACC_SUP_TEXT);
		TEXT_PANE.setAlignment(Pos.CENTER);
		TEXT_PANE.setTranslateX(-10);
		TEXT_PANE.setTranslateY(-10);

		StackPane BACKGROUND_PANE = new StackPane(BACKGROUND_REC, OVERLAY, TEXT_PANE);
		BACKGROUND_PANE.setStyle("-fx-background-color: transparent;");

		VBox LEFT = new VBox(BACKGROUND_PANE);
		LEFT.setTranslateX(-200);
		LEFT.setAlignment(Pos.CENTER);
		LEFT.setStyle("-fx-background-color: transparent;");

		Pane BACKGORUND_CORRECTOR = new Pane();
		BACKGORUND_CORRECTOR.setStyle("-fx-background-color: linear-gradient(to right, #189ad3, #005073);"
				+ "-fx-min-height: 400px;" + "-fx-max-height: 400px;" + "-fx-min-width: 422px;"
				+ "-fx-max-width: 422px;" + "-fx-background-radius: 0 20 20 0;");
		BACKGORUND_CORRECTOR.setTranslateX(130);

		StackPane PANE = new StackPane(LEFT, BACKGORUND_CORRECTOR, RIGHT);
		PANE.setPadding(new Insets(20));
		PANE.setStyle("-fx-background-radius: 20;" + "-fx-background-color: #005073;");

		// ====//

		// SCENE_PROPERTIES_&_STAGE//
		ACCOUNT_CREATION_SCENE = new Scene(PANE, 700, 400);
		ACCOUNT_CREATION_SCENE.setFill(Color.TRANSPARENT);

		// ====//

		// SCENE_EVENT_HANDLING//

		// ====//

		acc_creation_stage.setScene(ACCOUNT_CREATION_SCENE);
		acc_creation_stage.initStyle(StageStyle.TRANSPARENT);
		acc_creation_stage.getIcons().add(APP_LOGO);
		acc_creation_stage.setTitle("Registration");

		// ====//

		return acc_creation_stage;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Stage USER_STAGE() { // **this method takes user as a parmeter for profile elements usage**//

		// STAGES_&_SCENES//
		Stage acc_creation_stage = new Stage();

		Scene ACCOUNT_CREATION_SCENE;

		ObservableList<String> USER_TYPE = FXCollections.observableArrayList("Company", "Customer");

		ComboBox USER_CB = new ComboBox(USER_TYPE);
		USER_CB.setStyle(ELEMENTS.TF("150") + "-fx-text-fill: gray;");
		USER_CB.setTranslateX(260);
		USER_CB.setTranslateY(-180);
		USER_CB.setValue("Company");

		// ====//
		Image BACK_ARROW = new Image("Back Arrow.png");
		ImageView BACK_ARROW_IMAGE = ELEMENTS.IMAGE(BACK_ARROW, 25, 25);

		Image APP_LOGO = new Image("AppIcon.jpg");

		// ===///

		// BUTTONS_&_PROPERTIES//
		Button BACK_ARROW_BTN = new Button();
		BACK_ARROW_BTN.setStyle("-fx-background-color: transparent;");
		BACK_ARROW_BTN.setGraphic(BACK_ARROW_IMAGE);
		BACK_ARROW_BTN.setTranslateX(-330);
		BACK_ARROW_BTN.setTranslateY(-180);
		BACK_ARROW_BTN.setVisible(false);

		// ====//

		StackPane COMPANY = ELEMENTS.USER(2);
		COMPANY.setPadding(new Insets(20));
		COMPANY.setStyle("-fx-background-radius: 20;" + "-fx-background-color: #005073;");

		StackPane CUSTOMER = ELEMENTS.USER(1);
		CUSTOMER.setPadding(new Insets(20));
		CUSTOMER.setStyle("-fx-background-radius: 20;" + "-fx-background-color: #005073;");
		CUSTOMER.setVisible(false);

		StackPane PANE = new StackPane(CUSTOMER, COMPANY, USER_CB, BACK_ARROW_BTN);

		// ACTION_HANDLING_&_CONDTIONS//
		USER_CB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if ("Customer".equals(newValue)) {
				COMPANY.setVisible(false);
				CUSTOMER.setVisible(true);
			} else if ("Company".equals(newValue)) {
				COMPANY.setVisible(true);
				CUSTOMER.setVisible(false);
			}
		});

		if (USER_CB.getValue().equals("Customer")) {
			COMPANY.setVisible(false);
			CUSTOMER.setVisible(true);
		} else if (USER_CB.getValue().equals("Company")) {
			COMPANY.setVisible(true);
			CUSTOMER.setVisible(false);
		}
		// ====//

		// SCENE_PROPERTIES_&_STAGE//
		ACCOUNT_CREATION_SCENE = new Scene(PANE, 700, 400);
		ACCOUNT_CREATION_SCENE.setFill(Color.TRANSPARENT);

		// ====//

		// SCENE_EVENT_HANDLING//

		// ====//

		acc_creation_stage.setScene(ACCOUNT_CREATION_SCENE);
		acc_creation_stage.initStyle(StageStyle.TRANSPARENT);
		acc_creation_stage.getIcons().add(APP_LOGO);
		acc_creation_stage.setTitle("Registration");

		// ====//
		///////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////

		Stage Pstage = new Stage();

		// buttons
		Button manage_users = new Button("Manage Users");
		manage_users.setPrefSize(152, 33);
		manage_users.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;"
				+ "-fx-alignment: baseline-left;");

		Button reports_button = new Button("Reports");
		reports_button.setPrefSize(152, 33);
		reports_button.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");

		Button add_user = new Button("Add User");
		add_user.setPrefSize(250, 70);
		add_user.setStyle(ELEMENTS.BTN());

		Button edit_user = new Button("Edit User");
		edit_user.setPrefSize(250, 70);
		edit_user.setStyle(ELEMENTS.BTN());

		Button remove_user = new Button("Remove User");
		remove_user.setPrefSize(250, 70);
		remove_user.setStyle(ELEMENTS.BTN());

		Button reports_users = new Button("Users");
		reports_users.setPrefSize(250, 70);
		reports_users.setStyle(ELEMENTS.BTN());

		Button reports_bills = new Button("Bills");
		reports_bills.setPrefSize(250, 70);
		reports_bills.setStyle(ELEMENTS.BTN());

		Button reports_closed = new Button("Closed");
		reports_closed.setPrefSize(250, 70);
		reports_closed.setStyle(ELEMENTS.BTN());

		ObservableList<String> USER_TYPE_MAIN = FXCollections.observableArrayList("Companies", "Customers");

		ComboBox USER_CB_MAIN = new ComboBox(USER_TYPE_MAIN);
		// USER_CB_MAIN.setStyle(ELEMENTS.TF("150") + "-fx-text-fill: gray;");
		USER_CB_MAIN.setTranslateX(450);
		USER_CB_MAIN.setTranslateY(-260);
		USER_CB_MAIN.setValue("Customers");

		// label
		Label welcome_admin = new Label("Welcome " + "Admin to Beta!");
		welcome_admin.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

		Label welcome_admin_report = new Label("Welcome " + "Admin to Beta!");
		welcome_admin_report.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

		Label USER_NAME = new Label();

		// table
		ObservableList<Bill> DATA_BILL = FXCollections.observableArrayList();

		TableView<Bill> user_bills = new TableView<>();
		user_bills.setPrefHeight(600);
		user_bills.setPrefWidth(860);

		TableColumn<Bill, String> CorpNameCol = new TableColumn<>("Company Name");
		CorpNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCompanyUserName()));

		TableColumn<Bill, String> IssueDateCol = new TableColumn<>("Issue Date");
		IssueDateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBillCreationDate()));

		TableColumn<Bill, String> DueCol = new TableColumn<>("Due Date");
		DueCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBillDueDate()));

		TableColumn<Bill, String> ChargeCol = new TableColumn<>("Charge");
		ChargeCol.setCellValueFactory(
				data -> new SimpleStringProperty(Double.toString(data.getValue().getBillCharge())));

		TableColumn<Bill, String> StatusCol = new TableColumn<>("Status");
		StatusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBillStatus()));

		TableColumn<Bill, String> CorpIdCol = new TableColumn<>("ID");
		CorpIdCol.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getBillID())));

		user_bills.setVisible(false);

		user_bills.setItems(DATA_BILL);
		user_bills.getColumns().addAll(CorpNameCol, IssueDateCol, DueCol, ChargeCol, StatusCol, CorpIdCol);

		/////////////////////////////

		TableView report_table = new TableView();
		report_table.setPrefHeight(600);
		report_table.setPrefWidth(860);
		ObservableList<Customer> DATA = FXCollections.observableArrayList();

		TableView<Customer> user_table = new TableView<>();
		user_table.setPrefHeight(600);
		user_table.setPrefWidth(860);

		TableColumn<Customer, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFName()));

		TableColumn<Customer, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLName()));

		TableColumn<Customer, String> userNameCol = new TableColumn<>("User Name");
		userNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserName()));

		TableColumn<Customer, String> genderCol = new TableColumn<>("Gender");
		genderCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGender()));

		TableColumn<Customer, String> ageCol = new TableColumn<>("Birth Date");
		ageCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBirth_Date()));

		TableColumn<Customer, String> balanceCol = new TableColumn<>("Balance");
		balanceCol.setCellValueFactory(
				data -> new SimpleStringProperty(Double.toString(data.getValue().getCustomerBalance())));

		TableColumn<Customer, String> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getUserID())));

		TableColumn<Customer, Void> buttonCol = new TableColumn<>("");
		buttonCol.setCellFactory(param -> new TableCell<>() {
			private final Button button = new Button("Bills");

			{
				button.setOnAction(event -> {
					CUSTOMERS = getTableView().getItems().get(getIndex());
					user_bills.setVisible(true);
					user_table.setVisible(false);
					DATA_BILL.addAll(CUSTOMERS.listCustomerBills());
					System.out.println("Button clicked for " + CUSTOMERS.getFName() + " " + CUSTOMERS.getLName());
					USER_NAME.setText(CUSTOMERS.getUserName() + " bills' :");
					// Add your button action logic here
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
				super.updateItem(item, empty);
				if (empty) {
					setGraphic(null);
				} else {
					setGraphic(button);
				}
			}
		});

		ADMIN.setCustomer();
		DATA.clear();
		DATA.addAll(ADMIN.getCustomer());

		user_table.setItems(DATA);
		user_table.getColumns().addAll(firstNameCol, lastNameCol, userNameCol, ageCol, genderCol, balanceCol, idCol,
				buttonCol);

		///////////////////

		ObservableList<Company> DATA2 = FXCollections.observableArrayList();

		TableView<Company> user_table2 = new TableView<>();
		user_table.setPrefHeight(600);
		user_table.setPrefWidth(860);

		TableColumn<Company, String> userNameCorpCol = new TableColumn<>("UserName");
		userNameCorpCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserName()));

		TableColumn<Company, String> fieldCol = new TableColumn<>("field");
		fieldCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getField()));

		TableColumn<Company, String> idCorpCol = new TableColumn<>("ID");
		idCorpCol.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getUserID())));

		ADMIN.setCompany();
		DATA2.clear();
		DATA2.addAll(ADMIN.getCompany());

		user_table2.setItems(DATA2);
		user_table2.getColumns().addAll(userNameCorpCol, fieldCol, idCorpCol);

		user_table2.setVisible(false);

		HBox topButtonsPane = new HBox(add_user, edit_user, remove_user);
		HBox topButtonsReportPane = new HBox(reports_users, reports_bills, reports_closed);
		topButtonsPane.setPadding(new Insets(10));
		topButtonsPane.setSpacing(30);
		topButtonsReportPane.setPadding(new Insets(10));
		topButtonsReportPane.setSpacing(30);

		VBox leftPane = new VBox(manage_users, reports_button);
		leftPane.setPadding(new Insets(20));
		leftPane.setSpacing((10));
		leftPane.setStyle("-fx-background-color: #2E57DD ");

		StackPane TABLES = new StackPane(user_table, user_table2, user_bills);
		VBox users_rightPane = new VBox(welcome_admin, topButtonsPane, TABLES);
		VBox report_rightPane = new VBox(welcome_admin_report, topButtonsReportPane, report_table);
		report_rightPane.setPadding(new Insets(20));
		report_rightPane.setSpacing((10));
		users_rightPane.setPadding(new Insets(20));
		users_rightPane.setSpacing((10));

		BorderPane mainPane = new BorderPane();

		StackPane theright = new StackPane(users_rightPane, report_rightPane, USER_CB_MAIN, USER_NAME);

		leftPane.setPrefWidth(200);
		leftPane.setPrefHeight(696);

		users_rightPane.setPrefHeight(886);
		users_rightPane.setPrefWidth(1127);

		report_rightPane.setPrefHeight(886);
		report_rightPane.setPrefWidth(1127);

		mainPane.setLeft(leftPane);
		mainPane.setCenter(theright);

		VBox.setVgrow(leftPane, Priority.ALWAYS);
		VBox.setVgrow(theright, Priority.ALWAYS);

		// scene
		Scene scene = new Scene(mainPane, 500, 500);
		Pstage.setTitle("Admin");
		Pstage.setScene(scene);

		// actions

		BACK_ARROW_BTN.setOnAction(e -> {
			Platform.setImplicitExit(false);
			Pstage.show();
			acc_creation_stage.hide();
			BACK_ARROW_BTN.setVisible(false);
			user_table.setVisible(true);
			user_bills.setVisible(false);
			// RIGHT_ADD_PANE.setVisible(true);
			users_rightPane.setVisible(false);
			report_rightPane.setVisible(false);

		});

		add_user.setOnAction(e -> {
			Platform.setImplicitExit(false);
			Pstage.hide();
			acc_creation_stage.show();
			BACK_ARROW_BTN.setVisible(true);
			user_bills.setVisible(false);
			// RIGHT_ADD_PANE.setVisible(true);
			users_rightPane.setVisible(false);
			report_rightPane.setVisible(false);

		});

		edit_user.setOnAction(e -> {
			@SuppressWarnings("unused")
			Customer selected_Customer = user_table.getSelectionModel().getSelectedItem();

		});

		remove_user.setOnAction(e -> {
			if (USER_CB_MAIN.getValue().equals("Customers")) {
				Customer selected_Customer = user_table.getSelectionModel().getSelectedItem();
				if (selected_Customer != null) {

					DATA.remove(selected_Customer); // for real time deletion
					DATA.remove(ADMIN.search(selected_Customer.getUserName(), selected_Customer.getUserNumber())); // for
																													// file
																													// element
																													// deletion
					ADMIN.remove(selected_Customer.getUserName(), selected_Customer.getUserID());

				}
			} else if (USER_CB_MAIN.getValue().equals("Companies")) {
				Company selected_Company = user_table2.getSelectionModel().getSelectedItem();
				if (selected_Company != null) {

					DATA2.remove(selected_Company); // for real time deletion
					DATA2.remove(ADMIN.search(selected_Company.getUserName(), selected_Company.getUserNumber())); // for
																													// file
																													// element
																													// deletion
					ADMIN.remove(selected_Company.getUserName(), selected_Company.getUserID());

				}
			}

		});

		USER_CB_MAIN.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if ("Customers".equals(newValue)) {
				user_table2.setVisible(false);
				user_table.setVisible(true);
			} else if ("Companies".equals(newValue)) {
				user_table2.setVisible(true);
				user_table.setVisible(false);
			}
		});

		if (USER_CB_MAIN.getValue().equals("Customers")) {
			user_table2.setVisible(false);
			user_table.setVisible(true);
		} else if (USER_CB.getValue().equals("Companies")) {
			user_table2.setVisible(true);
			user_table.setVisible(false);
		}

		users_rightPane.setVisible(true);
		report_rightPane.setVisible(false);
		manage_users.setOnAction(e -> {
			ADMIN.setCustomer();
			ADMIN.setCompany();
			DATA.clear();
			DATA2.clear();
			DATA2.addAll(ADMIN.getCompany());
			DATA.addAll(ADMIN.getCustomer());
			users_rightPane.setVisible(true);
			report_rightPane.setVisible(false);

		});
		reports_button.setOnAction(e -> {
			users_rightPane.setVisible(false);
			report_rightPane.setVisible(true);

		});

		manage_users.setOnMouseEntered(e -> {
			manage_users.setStyle("-fx-background-color: #284BBE;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;"
					+ "-fx-alignment: baseline-left;");
		});

		manage_users.setOnMouseExited(e -> {
			manage_users.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;"
					+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");
		});

		reports_button.setOnMouseEntered(e -> {
			reports_button.setStyle("-fx-background-color: #284BBE;" + "-fx-text-fill: white;"
					+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");
		});

		reports_button.setOnMouseExited(e -> {
			reports_button.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;"
					+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");
		});

		///////////////////////////////////////////////////////////////////////////////

		return Pstage;
	}

	@SuppressWarnings("unchecked")
	public Stage COMPANY_USER_STAGE() {
		
		
		///////////////////////////////////////
		
		
		
		Stage Pstage = new Stage();
		
		//image
		
		 Image RETURN_BACK_IMAGE = new Image("Back Arrow.png");
	        ImageView RETURN_BACK = ELEMENTS.IMAGE(RETURN_BACK_IMAGE, 30, 30);
	        
	        
		
		// buttons
		Button manage_customers = new Button("Manage Customers");
		manage_customers.setPrefSize(152, 33);
		manage_customers.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");
		Button reports_button = new Button("Reports");
		reports_button.setPrefSize(62, 19);
		reports_button.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");
		reports_button.setTranslateX(30);

		Button bills = new Button("Bills");
		bills.setPrefSize(250, 70);
		bills.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;"
				+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");
		
		Button edit_bill = new Button("Edit Bill");
		edit_bill.setPrefSize(250, 70);
		edit_bill.setStyle("-fx-background-color: #D3D3D3;" + "-fx-text-fill: #6E879D;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 10;");
		
		Button remove_bill = new Button("Remove Bill");
		remove_bill.setPrefSize(250, 70);
		remove_bill.setStyle("-fx-background-color: #D3D3D3;" + "-fx-text-fill: #6E879D;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 10;");
		
		Button add_bills = new Button("Add Bill");
		add_bills.setPrefSize(250, 70);
		add_bills.setStyle("-fx-background-color: #D3D3D3;" + "-fx-text-fill: #6E879D;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 10;");

		Button dummy = new Button("dummy button");
		dummy.setPrefSize(250, 70);

//lebel
		Label welcome_company = new Label("Welcome " + "Company Name!");
		welcome_company.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

		Label welcome_Insideompany = new Label("Welcome " + "Company Name!");
		welcome_Insideompany.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

		Label welcome_company_report = new Label("Welcome " + "Company Name!");
		welcome_company_report.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

//table

		ObservableList<Bill> DATA_BILL = FXCollections.observableArrayList();

		TableView BillTable = new TableView();
		BillTable.setPrefHeight(600);
		BillTable.setPrefWidth(860);

		TableColumn<Bill, String> CustomerCol = new TableColumn<>("Customer");
		CustomerCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCustomerUserName()));

		TableColumn<Bill, String> ChargeCol = new TableColumn<>("Charge");
		ChargeCol.setCellValueFactory(
				data -> new SimpleStringProperty(Double.toString(data.getValue().getBillCharge())));

		TableColumn<Bill, String> IssueCol = new TableColumn<>("Issue Date");
		IssueCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBillCreationDate()));

		TableColumn<Bill, String> DueCol = new TableColumn<>("Due Date");
		DueCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBillDueDate()));

		TableColumn<Bill, String> StatusCol = new TableColumn<>("Status");
		StatusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBillStatus()));

		TableColumn<Bill, String> IdCol = new TableColumn<>("State");
		IdCol.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getBillID())));

		//COMPANIES.listCompanyBills();
		DATA_BILL.clear();
		
		BillTable.setItems(DATA_BILL);
		DATA_BILL.addAll(COMPANIES.listCompanyBills());

		BillTable.getColumns().addAll(CustomerCol, ChargeCol, IssueCol, DueCol, StatusCol, IdCol);

		///////////////////

		ObservableList<Customer> DATA_CUSTOMERS = FXCollections.observableArrayList();

		TableView<Customer> user_table = new TableView<>();
		user_table.setPrefHeight(600);
		user_table.setPrefWidth(860);

		TableColumn<Customer, String> firstNameCol = new TableColumn<>("First Name");
		firstNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFName()));

		TableColumn<Customer, String> lastNameCol = new TableColumn<>("Last Name");
		lastNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getLName()));

		TableColumn<Customer, String> userNameCol = new TableColumn<>("User Name");
		userNameCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserName()));

		TableColumn<Customer, String> genderCol = new TableColumn<>("Gender");
		genderCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGender()));

		TableColumn<Customer, String> ageCol = new TableColumn<>("Birth Date");
		ageCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBirth_Date()));

		TableColumn<Customer, String> balanceCol = new TableColumn<>("Balance");
		balanceCol.setCellValueFactory(
				data -> new SimpleStringProperty(Double.toString(data.getValue().getCustomerBalance())));

		TableColumn<Customer, String> idCol = new TableColumn<>("ID");
		idCol.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getUserID())));

		TableColumn<Customer, Void> buttonCol = new TableColumn<>("");
		buttonCol.setCellFactory(param -> new TableCell<>() {
			private final Button button = new Button("Bills");

			{
				button.setOnAction(event -> {
					CUSTOMERS = getTableView().getItems().get(getIndex());
					BillTable.setVisible(true);
					user_table.setVisible(false);
					for(Bill bill : COMPANIES.listCompanyBills()) {
						if(CUSTOMERS.getUserName().equals(bill.getCustomerUserName())) {
							DATA_BILL.add(bill);
						}
						
					}
					System.out.println("Button clicked for " + CUSTOMERS.getFName() + " " + CUSTOMERS.getLName());
					// Add your button action logic here
				});
			}

			@Override
			protected void updateItem(Void item, boolean empty) {
			    super.updateItem(item, empty);
			    if (empty || getItem() == null) {
			        setGraphic(null);
			    } else {
			        setGraphic(button);
			    }
			}

		});

		COMPANIES.setCorpCustomers();
		DATA_CUSTOMERS.clear();
		DATA_CUSTOMERS.addAll(COMPANIES.getCorpCustomers());

		user_table.setItems(DATA_CUSTOMERS);
		user_table.getColumns().addAll(firstNameCol, lastNameCol, userNameCol, ageCol, genderCol, balanceCol, idCol,buttonCol);

//panes
		HBox topBTNbills = new HBox(add_bills, edit_bill, remove_bill);
		topBTNbills.setPadding(new Insets(10));
		topBTNbills.setSpacing(30);

		VBox leftPane = new VBox(manage_customers, reports_button, bills);
		leftPane.setPadding(new Insets(20));
		leftPane.setSpacing((10));
		leftPane.setStyle("-fx-background-color: #2E57DD ");
		
		VBox Company_rightPane = new VBox(welcome_company, user_table);
		VBox insideCompany_rightPane = new VBox(welcome_Insideompany, topBTNbills, BillTable);
		VBox report_rightPane = new VBox(welcome_company_report);
		report_rightPane.setPadding(new Insets(20));
		report_rightPane.setSpacing((10));
		Company_rightPane.setPadding(new Insets(20));
		Company_rightPane.setSpacing((10));
		insideCompany_rightPane.setPadding(new Insets(20));
		insideCompany_rightPane.setSpacing((10));
		

		  
	//buttons
	        Button RETURN_BACK_BTN = new Button();
	        RETURN_BACK_BTN.setPrefSize(30, 30);
	        RETURN_BACK_BTN.setGraphic(RETURN_BACK);
	        RETURN_BACK_BTN.setStyle("-fx-background-color: transparent;" + "-fx-border-width: 0;");

			//SCENE  
			       // Scene USER_INFO_SCENE = new Scene(USER_INFO_MAIN_PANE, 700, 400);

			//STAGE
			      //  Stage USER_INFO_STAGE = new Stage();
			      //  USER_INFO_STAGE.setTitle("Personal Information");
			      //  USER_INFO_STAGE.setScene(USER_INFO_SCENE);
			      //  USER_INFO_STAGE.show();

			       
	        
	    BorderPane billPane = ELEMENTS.PERSONALINFO_FORM(COMPANIES);
	    billPane.setTop(RETURN_BACK_BTN);
	    billPane.setVisible(false);
	    
	    
		BorderPane mainPane = new BorderPane();

		StackPane theright = new StackPane(Company_rightPane, insideCompany_rightPane, report_rightPane);

		leftPane.setPrefWidth(200);
		leftPane.setPrefHeight(696);
		Company_rightPane.setPrefHeight(886);
		Company_rightPane.setPrefWidth(1127);
		report_rightPane.setPrefHeight(886);
		report_rightPane.setPrefWidth(1127);
		mainPane.setLeft(leftPane);
		mainPane.setCenter(theright);

		VBox.setVgrow(leftPane, Priority.ALWAYS);
		VBox.setVgrow(theright, Priority.ALWAYS);
		
	    StackPane finalPane = new StackPane(mainPane,billPane); 


//scene
		Scene scene = new Scene(finalPane, 500, 500);
		Pstage.setTitle("Company");
		Pstage.setScene(scene);

		// actions
		//addBills.
		
		 RETURN_BACK_BTN.setOnAction(e -> {
			 mainPane.setVisible(true); 
			 billPane.setVisible(false);
	        });
		 
		 add_bills.setOnAction(e -> {
			 mainPane.setVisible(false); 
			 billPane.setVisible(true);
			 
		 });
		 
		Company_rightPane.setVisible(true);
		insideCompany_rightPane.setVisible(false);
		report_rightPane.setVisible(false);
		manage_customers.setOnAction(e -> {
			Company_rightPane.setVisible(true);
			report_rightPane.setVisible(false);
			insideCompany_rightPane.setVisible(false);
		});
		reports_button.setOnAction(e -> {
			Company_rightPane.setVisible(false);
			report_rightPane.setVisible(true);
			insideCompany_rightPane.setVisible(false);
		});
		bills.setOnAction(e -> {
			insideCompany_rightPane.setVisible(true);
			Company_rightPane.setVisible(false);
			report_rightPane.setVisible(false);
		});

		manage_customers.setOnMouseEntered(e -> {
			manage_customers.setStyle("-fx-background-color: #284BBE;" + "-fx-text-fill: white;"
					+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");
		});

		manage_customers.setOnMouseExited(e -> {
			manage_customers.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;"
					+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");
		});

		reports_button.setOnMouseEntered(e -> {
			reports_button.setStyle("-fx-background-color: #284BBE;" + "-fx-text-fill: white;"
					+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");
		});

		reports_button.setOnMouseExited(e -> {
			reports_button.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;"
					+ "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");
		});

		return Pstage;
	}
	
	public Stage CUSTOMER_USER_STAGE() {
		 Stage Pstage = new Stage();
			/*
			 * ArrayList<Bill> billList = new ArrayList<>();
			 * 
			 * // Create and populate the first bill Bill bill1 = new Bill();
			 * bill1.setBillID(); bill1.setBillCharge(100.0); bill1.setBillCreationDate(1,
			 * 1, 2022); bill1.setBillDueDate(1, 31, 2022); bill1.setBillStatus("unpaid");
			 * // Set other properties as needed bill1.setCustomerUserName("customer1");
			 * bill1.setCompanyUserName("Nokia");
			 * 
			 * // Create and populate the second bill Bill bill2 = new Bill();
			 * bill2.setBillID(); bill2.setBillCharge(150.0); bill2.setBillCreationDate(2,
			 * 1, 2022); bill2.setBillDueDate(2, 28, 2022); bill2.setBillStatus("paid"); //
			 * Set other properties as needed bill2.setCustomerUserName("customer2");
			 * bill2.setCompanyUserName("Samsung");
			 * 
			 * // Add bills to the ArrayList billList.add(bill1); billList.add(bill2);
			 */
	//Images
	        Image USER_IMAGE = new Image("User.png");
	        ImageView USER = ELEMENTS.IMAGE(USER_IMAGE, 20, 20);
	     //   Image SEARCH_IMAGE = new Image("SEARCH.jpeg");
	      //  ImageView SEARCH = ELEMENTS.IMAGE(SEARCH_IMAGE, 20, 20);

	//Buttons
	        Button BILLS_BTN = new Button("Bills");
	        BILLS_BTN.setPrefSize(152, 33);
	        BILLS_BTN.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");

//	        Button REPORTS_BTN = new Button("Reports");
//	        REPORTS_BTN.setPrefSize(152, 33);
//	        REPORTS_BTN.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");

	        Button ADD_COMPANY_BTN = new Button("Companies");
	        ADD_COMPANY_BTN.setPrefSize(152, 33);
	        ADD_COMPANY_BTN.setStyle("-fx-background-color: transparent;" + "-fx-text-fill: white;" + "-fx-font-weight: bold;" + "-fx-alignment: baseline-left;");

	        Button INFORMATION_BTN = new Button();
	        INFORMATION_BTN.setPrefSize(20, 20);
	        INFORMATION_BTN.setGraphic(USER);
	        INFORMATION_BTN.setStyle("-fx-background-color: white;" + "-fx-border-width: 0;");
	        INFORMATION_BTN.setTranslateX(20);
	        INFORMATION_BTN.setTranslateY(-20);

	//label
	        Label welcome_user = new Label("Welcome " + "User" + "!");
	        welcome_user.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

	        Label CUSTOMER_BALANCE = new Label("Customer " + "Balance :");
	        CUSTOMER_BALANCE.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;" + "-fx-font-size: 14px;");
	        
	        CUSTOMERS.setCustomerBalance();
	        Label CUSTOMER_BALANCE_VALUE = new Label(Double.toString(CUSTOMERS.getCustomerBalance()));
	        CUSTOMER_BALANCE_VALUE.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;" + "-fx-font-size: 14px;");

	        Label TIME_LBL = new Label("Months");
	        TIME_LBL.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");
	        TIME_LBL.setTranslateY(10);

	        Label CUSTOMER_INFO_LBL = new Label("\t" + "CUSTOMER NAME");
	        CUSTOMER_INFO_LBL.setTranslateY(-12.5);
	        CUSTOMER_INFO_LBL.setTextFill(Color.WHITE);
	        TIME_LBL.setStyle("-fx-background-color: transparent;" + "-fx-font-weight: bold;");

	//Textbox
	        TextField SEARCH_TXT = new TextField();
	        SEARCH_TXT.setPromptText("Search...");

	        ObservableList<String> TIME_LIST = FXCollections.observableArrayList("1 month", "2 months", "3 months");
	        ComboBox TIME_CB = new ComboBox(TIME_LIST);
	        TIME_CB.setStyle(ELEMENTS.TF("150") + "-fx-text-fill: gray;");

	//table
	        ObservableList<Company> DATA2 = FXCollections.observableArrayList();

	        TableView<Company> user_table2 = new TableView<>();
	        user_table2.setPrefHeight(600);
	        user_table2.setPrefWidth(860);

	        TableColumn<Company, String> userNameCorpCol = new TableColumn<>("UserName");
	        userNameCorpCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserName()));

	        TableColumn<Company, String> fieldCol = new TableColumn<>("field");
	        fieldCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getField()));

	        TableColumn<Company, String> idCorpCol = new TableColumn<>("ID");
	        idCorpCol.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getUserID())));

	        CUSTOMERS.setCompany();
	        DATA2.clear();
	        DATA2.addAll(CUSTOMERS.getCompany());

	        user_table2.setItems(DATA2);
	        user_table2.getColumns().addAll(userNameCorpCol, fieldCol, idCorpCol);

	        ObservableList<Bill> DATA = FXCollections.observableArrayList();
	        TableView USER_BILL_TABLE = new TableView();
	        USER_BILL_TABLE.setPrefHeight(600);
	        USER_BILL_TABLE.setPrefWidth(860);

//	        TableView REPORTS_BILL_TABLE = new TableView();
//	        REPORTS_BILL_TABLE.setPrefHeight(600);
//	        REPORTS_BILL_TABLE.setPrefWidth(860);

	        TableColumn<Bill, String> CompCol = new TableColumn<>("Company");
	        CompCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCompanyUserName()));

	        TableColumn<Bill, String> BillCol = new TableColumn<>("Bill");
	        BillCol.setCellValueFactory(data -> new SimpleStringProperty(Double.toString(data.getValue().getBillCharge())));

	        TableColumn<Bill, String> StatusCol = new TableColumn<>("Status");
	        StatusCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBillStatus()));

	        TableColumn<Bill, String> Bill_Id_Col = new TableColumn<>("Bill Id");
	        Bill_Id_Col.setCellValueFactory(data -> new SimpleStringProperty(Integer.toString(data.getValue().getBillID())));

	        TableColumn<Bill, Void> Pay_Btn_Col = new TableColumn<>("Pay");

	        Pay_Btn_Col.setCellFactory(param -> new TableCell<>() {
	            private final Button btn = new Button();

	            {

	                btn.setOnAction(event -> {
	                    Bill MAIN_BILL = getTableView().getItems().get(getIndex());
	                    Double result = Double.parseDouble(CUSTOMER_BALANCE_VALUE.getText()) - MAIN_BILL.getBillCharge();
	                    if(result<0){
	                    Alert INCOMPLETE_PAYMENT = new Alert(Alert.AlertType.INFORMATION);
			INCOMPLETE_PAYMENT.setTitle("");
			INCOMPLETE_PAYMENT.setHeaderText(null);
			INCOMPLETE_PAYMENT.setContentText("Cannot complete the payment!");
	                INCOMPLETE_PAYMENT.showAndWait(); 
	                    }
	                        else{
	                                        btn.setText("Paid");
	                    btn.setDisable(true);
	                    CUSTOMER_BALANCE_VALUE.setText(Double.toString(result));
	                     // Access the index of the current row
	                    int index = getIndex();

	                    // Update the status directly in the underlying data
	                    Bill updatedBill = getTableView().getItems().get(index);
	                    updatedBill.setBillStatus("paid");

	                    // Refresh the table to reflect the changes
	                    getTableView().refresh();
	                    }    
	                });

	            }

	            @Override
	            protected void updateItem(Void item, boolean empty) {
	                super.updateItem(item, empty);
	                if (empty) {
	                    setGraphic(null);
	                } else {
	                    Bill MAIN_BILL = getTableView().getItems().get(getIndex());
	                    btn.setText("Pay");  // Set initial text before any action
	                    if ("paid".equals(MAIN_BILL.getBillStatus())) {
	                        btn.setText("Paid");
	                        btn.setDisable(true);
	                    } else {
	                        btn.setDisable(false);
	                    }
	                    setGraphic(btn);
	                }
	            }
	        });

	        //////////////////////////
	        CUSTOMERS.listCustomerBills();
	        USER_BILL_TABLE.setItems(DATA);
	        DATA.addAll(CUSTOMERS.listCustomerBills());

	      //  REPORTS_BILL_TABLE.setItems(DATA);
	      //  REPORTS_BILL_TABLE.getColumns().addAll(CompCol, BillCol, StatusCol, Bill_Id_Col);

	//panes
	        BorderPane SECONDARY_PANE = PERSONALINFO_FORM();
	        

	        
	        VBox LEFT_BUTTONS_VBOX = new VBox(BILLS_BTN, ADD_COMPANY_BTN);
	        LEFT_BUTTONS_VBOX.setPadding(new Insets(20));
	        LEFT_BUTTONS_VBOX.setSpacing((10));
	        LEFT_BUTTONS_VBOX.setStyle("-fx-background-color: #2E57DD ");

	        HBox INFO_PANE = new HBox(INFORMATION_BTN, CUSTOMER_INFO_LBL);

	        VBox LEFT_WHOLE_PANE = new VBox(LEFT_BUTTONS_VBOX, INFO_PANE);
	        LEFT_WHOLE_PANE.setAlignment(Pos.BOTTOM_LEFT);
	        LEFT_WHOLE_PANE.setStyle("-fx-background-color: #2E57DD ");

	        HBox CUSTOMER_BALANCE_HBOX = new HBox(CUSTOMER_BALANCE, CUSTOMER_BALANCE_VALUE);
	        CUSTOMER_BALANCE_HBOX.setAlignment(Pos.BOTTOM_LEFT);
	        CUSTOMER_BALANCE_HBOX.setPadding(new Insets(40));

	        HBox SEARCH_PANE = new HBox(SEARCH_TXT);
	        SEARCH_PANE.setAlignment(Pos.TOP_RIGHT);

	        VBox CUSTOMER_RIGHTPANE = new VBox(SEARCH_PANE, USER_BILL_TABLE, CUSTOMER_BALANCE_HBOX);
	        CUSTOMER_RIGHTPANE.setPadding(new Insets(20));
	        CUSTOMER_RIGHTPANE.setSpacing(30);

	        HBox TIME_PANE = new HBox(TIME_LBL, TIME_CB);
	        TIME_PANE.setAlignment(Pos.TOP_RIGHT);

	      //  VBox REPORTS_RIGHTPANE = new VBox(TIME_PANE, REPORTS_BILL_TABLE);
	        //REPORTS_RIGHTPANE.setPadding(new Insets(20));
	        //REPORTS_RIGHTPANE.setSpacing(30);

	        StackPane RIGHTPANE = new StackPane(CUSTOMER_RIGHTPANE, user_table2);

	        BorderPane mainPane = new BorderPane();
	        mainPane.setLeft(LEFT_WHOLE_PANE);
	        mainPane.setCenter(RIGHTPANE);

	        LEFT_BUTTONS_VBOX.setPrefWidth(200);
	        LEFT_BUTTONS_VBOX.setPrefHeight(696);
	        CUSTOMER_RIGHTPANE.setPrefHeight(886);
	        CUSTOMER_RIGHTPANE.setPrefWidth(1127);
	    //    REPORTS_RIGHTPANE.setPrefHeight(886);
	    //    REPORTS_RIGHTPANE.setPrefWidth(1127);
	        user_table2.setPrefHeight(886);
	        user_table2.setPrefWidth(1127);

	        VBox.setVgrow(LEFT_BUTTONS_VBOX, Priority.ALWAYS);
	        VBox.setVgrow(RIGHTPANE, Priority.ALWAYS);

	//scene
	        Scene scene = new Scene(mainPane, 500, 500);
	        Pstage.setTitle("Customer");
	        Pstage.setScene(scene);
	        //Pstage.show();
	        
	        Scene USER_INFO_SCENE = new Scene(SECONDARY_PANE, 700, 400);

			//STAGE
			     final  Stage USER_INFO_STAGE = new Stage();
			        USER_INFO_STAGE.setTitle("Personal Information");
			        USER_INFO_STAGE.setScene(USER_INFO_SCENE);
			        //USER_INFO_STAGE.show();


	//ACTION HANDLING
	        CUSTOMER_RIGHTPANE.setVisible(true);
	     //   REPORTS_RIGHTPANE.setVisible(false);
	        user_table2.setVisible(false);
	        BILLS_BTN.setOnAction(e -> {
	            CUSTOMER_RIGHTPANE.setVisible(true);
	    //        REPORTS_RIGHTPANE.setVisible(false);
	            user_table2.setVisible(false);
	        });
//	        REPORTS_BTN.setOnAction(e -> {
//	            CUSTOMER_RIGHTPANE.setVisible(false);
//	        //    REPORTS_RIGHTPANE.setVisible(true);
//	            user_table2.setVisible(false);
//	        });
	        ADD_COMPANY_BTN.setOnAction(e -> {
	            CUSTOMER_RIGHTPANE.setVisible(false);
	    //        REPORTS_RIGHTPANE.setVisible(false);
	            user_table2.setVisible(true);
	        });

	        INFORMATION_BTN.setOnAction(e -> {
	            Platform.setImplicitExit(false);
	            Pstage.hide();
	        	USER_INFO_STAGE.show();
	            PERSONALINFO_FORM();
	        });

	        ////////////////////////////////

	        ObservableList<Bill> originalData = FXCollections.observableArrayList(DATA);

	        FilteredList<Bill> filteredData = new FilteredList<>(originalData, p -> true);
	        USER_BILL_TABLE.setItems(filteredData);

	        SEARCH_TXT.textProperty().addListener((observable, oldValue, newValue) -> {
	            if (newValue == null || newValue.isEmpty()) {
	                // Reset the filteredData to show all entries when the search field is empty
	                filteredData.setPredicate(bill -> true);
	            } else {
	                filteredData.setPredicate(bill -> {
	                    // Convert both the bill's company username and the search term to lowercase for case-insensitive search
	                    String lowerCaseFilter = newValue.toLowerCase();
	                    String billCompany = bill.getCompanyUserName().toLowerCase();

	                    // Check if the company username contains the search term
	                    return billCompany.contains(lowerCaseFilter);
	                });
	            }

	            // Remove the mismatched rows from the underlying data
	            DATA.setAll(originalData.filtered(bill -> {
	                if (newValue == null || newValue.isEmpty()) {
	                    return true; // Show all entries if the search field is empty
	                }

	                String lowerCaseFilter = newValue.toLowerCase();
	                String billCompany = bill.getCompanyUserName().toLowerCase();
	                return billCompany.contains(lowerCaseFilter);
	            }));
	        });
	        
	      //images
	        Image RETURN_BACK_IMAGE = new Image("Back Arrow.png");
	        ImageView RETURN_BACK = ELEMENTS.IMAGE(RETURN_BACK_IMAGE, 30, 30);
	//buttons
	        Button RETURN_BACK_BTN = new Button();
	        RETURN_BACK_BTN.setPrefSize(30, 30);
	        RETURN_BACK_BTN.setGraphic(RETURN_BACK);
	        RETURN_BACK_BTN.setStyle("-fx-background-color: transparent;" + "-fx-border-width: 0;");

	        
	        RETURN_BACK_BTN.setOnAction(e -> {
	            Platform.setImplicitExit(false);
	            USER_INFO_STAGE.hide();
	            Pstage.show();
	        });
	        
	        SECONDARY_PANE.setTop(RETURN_BACK_BTN);


	        ///////////////////////////////
	        return Pstage;
	    }
	
	
	private BorderPane PERSONALINFO_FORM() {

		
		        ToggleButton EDIT_BTN = new ToggleButton("Edit");
		        EDIT_BTN.setPrefSize(70, 70);
		        EDIT_BTN.setStyle("-fx-background-color: #D3D3D3;" + "-fx-font-weight: bold;" + "-fx-background-radius: 10;");

		//Labels
		        Label FNAME_LBL = createAnimatedLabel("First name :");
		        Label LNAME_LBL = createAnimatedLabel("Last name :");
		        Label USERNAME_LBL = createAnimatedLabel("Username :");
		        Label ID_LBL = createAnimatedLabel("Id :");
		        Label BIRTHDATE_LBL = createAnimatedLabel("Birthdate :");
		        Label GENDER_LBL = createAnimatedLabel("Gender :");
		        Label BALANCE_LBL = createAnimatedLabel("Balance :");
		//Textfields

		        TextField FNAME_TXT = createAnimatedTextField("AHMED");
		        TextField LNAME_TXT = createAnimatedTextField("SAMER");
		        TextField USERNAME_TXT = createAnimatedTextField("SAMORTCHY");
		        TextField ID_TXT = createAnimatedTextField("ID BTA3AK");
		        TextField BALANCE_TXT = createAnimatedTextField("FLOSAK");
		        TextField BDATE_TXT = createAnimatedTextField("3ed meladak");
		        TextField GENDER_TXT = createAnimatedTextField("GENDERAK");

//		    DatePicker BDATE_DP = new DatePicker();
//		    BDATE_DP.setStyle(ELEMENTS.TF("150"));
//		    BDATE_DP.setTranslateY(-10);
//		    BDATE_DP.setVisible(false);
//		    ObservableList<String> GENDER_LIST = FXCollections.observableArrayList("Male", "Female");
//		    ComboBox GENDER_CB = new ComboBox(GENDER_LIST);
//		    GENDER_CB.setStyle(ELEMENTS.TF("150") + "-fx-text-fill: gray;");
//		    GENDER_CB.setEditable(true);
		//PANES
		        HBox FNAME_HBOX = new HBox(FNAME_LBL, FNAME_TXT);

		        HBox LNAME_HBOX = new HBox(LNAME_LBL, LNAME_TXT);

		        HBox NAMES = new HBox(FNAME_HBOX, LNAME_HBOX);
		        NAMES.setPadding(new Insets(20));
		        NAMES.setSpacing(30);

		        HBox USERNAME_HBOX = new HBox(USERNAME_LBL, USERNAME_TXT);

		        HBox ID_HBOX = new HBox(ID_LBL, ID_TXT);

		        HBox USERNAME_ID = new HBox(USERNAME_HBOX, ID_HBOX);
		        USERNAME_ID.setPadding(new Insets(20));
		        USERNAME_ID.setSpacing(30);

		        HBox BALANCE_HBOX = new HBox(BALANCE_LBL, BALANCE_TXT);

		        HBox BDATE_PANE = new HBox(BIRTHDATE_LBL, BDATE_TXT);

		        HBox GENDER_PANE = new HBox(GENDER_LBL, GENDER_TXT);
		        GENDER_PANE.setAlignment(Pos.BOTTOM_CENTER);

		        HBox BDATE_HBOX = new HBox(BIRTHDATE_LBL, BDATE_PANE);

		        HBox BALANCE_BDATE = new HBox(BALANCE_HBOX, BDATE_HBOX);
		        BALANCE_BDATE.setPadding(new Insets(20));
		        BALANCE_BDATE.setSpacing(30);

		        VBox PERSON_INFO = new VBox(NAMES, USERNAME_ID, BALANCE_BDATE, GENDER_PANE);
		        PERSON_INFO.setAlignment(Pos.TOP_CENTER);
		        PERSON_INFO.setPadding(new Insets(20));

		        HBox EDIT_BTN_PANE = new HBox(EDIT_BTN);
		        EDIT_BTN_PANE.setPadding(new Insets(20));
		        EDIT_BTN_PANE.setAlignment(Pos.BOTTOM_RIGHT);

		        BorderPane USER_INFO_MAIN_PANE = new BorderPane();
		        USER_INFO_MAIN_PANE.setCenter(PERSON_INFO);
		        USER_INFO_MAIN_PANE.setRight(EDIT_BTN_PANE);
		        
		        

		//SCENE  
		        
		//ACTION HANDLING
		        EDIT_BTN.setOnAction(e -> {
		            if (EDIT_BTN.isSelected()) {
		                EDIT_BTN.setText("Save edit");
		                FNAME_TXT.setEditable(true);
		                LNAME_TXT.setEditable(true);
		                USERNAME_TXT.setEditable(true);
		                
		            } else {
		                EDIT_BTN.setText("Edit");
		                FNAME_TXT.setEditable(false);
		                LNAME_TXT.setEditable(false);
		                USERNAME_TXT.setEditable(false);
		                //    BDATE_TXT.setEditable(false);
		                //    ID_TXT.setEditable(false);
		                //    BALANCE_TXT.setEditable(false);
		            }
		        });

		       
		//BDATE_DP.setOnAction(event -> {
//					BDATE_TXT.setText(BDATE_DP.getEditor().getText());
//					BDATE_DP.setVisible(false);
//					BDATE_TXT.setVisible(true);
//				});
		//
		//BDATE_TXT.setOnMouseClicked(e -> {
//					if (e.getButton() == MouseButton.PRIMARY) {
//						BDATE_DP.setVisible(true);
//						BDATE_TXT.setVisible(false);
//					}
		//
//				});
		//GENDER_CB.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//					if (newValue != null) {
//						GENDER_LBL.setVisible(false);
//					} else {
//						GENDER_LBL.setVisible(true);
//					}
//				});
		        return USER_INFO_MAIN_PANE;


		    }
	
	  private Label createAnimatedLabel(String text) {
	        Label label = new Label(text);
	        label.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

	        // Apply translation animation
	        applyTranslateAnimation(label);

	        return label;
	    }

	    private TextField createAnimatedTextField(String text) {
	        TextField textField = new TextField(text);
	        textField.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");
	        textField.setTranslateY(-10);
	        textField.setEditable(false);

	        // Apply translation animation
	        applyTranslateAnimation(textField);

	        return textField;
	    }

	    private void applyTranslateAnimation(Label label) {
	        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), label);
	        transition.setFromY(-50);
	        transition.setToY(0);
	        transition.play();
	    }

	    private void applyTranslateAnimation(TextField textField) {
	        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), textField);
	        transition.setFromY(-50);
	        transition.setToY(0);
	        transition.play();}
	

	
	



	public static void main(String[] args) {
		launch();
	}
}
