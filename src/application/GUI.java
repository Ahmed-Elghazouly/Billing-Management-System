package application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUI {

	private static final GUI ELEMENTS = new GUI();

	private static final double GOLDEN_RATIO = 1.618;

	private static final Admin ADMIN = new Admin();

	ImageView IMAGE(Image IMAGE, int width, int height) {
		ImageView IMAGE_VIEW = new ImageView(IMAGE);
		IMAGE_VIEW.setFitWidth(width);
		IMAGE_VIEW.setFitHeight(height);
		return IMAGE_VIEW;
	}

	String TF(String width) {
		String TF = ("-fx-min-width: " + width + ";" + " -fx-min-height: 40px;" + "-fx-max-width: " + width + ";"
				+ "-fx-max-height: 40px;" + "-fx-background-color: transparent; " + // Transparent
				// background
				"-fx-border-width: 0 0 1 0; " + // Bottom border only
				"-fx-border-color: black;" + // Border color
				"-fx-font-family: 'Roboto'; " + "-fx-font-size: 12; ");
		return TF;

	}

	String BTN() {
		String BTN = ("-fx-background-color: #D3D3D3;" + "-fx-text-fill: #6E879D;" + "-fx-font-weight: bold;"
				+ "-fx-background-radius: 10;");

		return BTN;

	}

	String TF2(String width) {
		String TF2 = ("-fx-min-width: " + width + ";" + " -fx-min-height: 25px;" + "-fx-max-width: " + width + ";"
				+ "-fx-max-height: 25px;" + "-fx-background-color: #52658F; " + "-fx-font-size: 9pt; "
				+ "-fx-background-color: #2E3B4E; " + "-fx-text-fill: white; " + "-fx-border-width: 2px; "
				+ "-fx-border-radius: 10px; ");

		return TF2;
	}

	Alert ALERT(String MASSAGE) {
		Alert ALERT = new Alert(AlertType.INFORMATION);
		// NO_USERNAME.setTitle("Information Dialog");
		ALERT.setHeaderText(null);
		ALERT.setContentText("Please enter a " + MASSAGE + " to proceed");

		return ALERT;

	}

	StackPane ACC_CREATION_PANE() {
		// TITLE//
		Text CREATE_ACC_TEXT = new Text("Get Started");
		CREATE_ACC_TEXT.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		Text CREATE_ACC_SUP_TEXT = new Text("Mastering Your Billing Needs");
		CREATE_ACC_SUP_TEXT.setFont(Font.font("Arial", FontWeight.BOLD, 20 / GOLDEN_RATIO));

		// ====//

		// IMAGES//

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

		// ====//

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

		StackPane PANE = new StackPane(LEFT, BACKGORUND_CORRECTOR);

		return PANE;
	}

	@SuppressWarnings({ "unchecked", "unused" })
	StackPane USER(int i) {

		// IMAGES//
		Image SHOW_IMAGE = new Image("Show.png");// .
		ImageView SHOW = ELEMENTS.IMAGE(SHOW_IMAGE, 20, 20);

		Image HIDE_IMAGE = new Image("Hide.png");// .
		ImageView HIDE = ELEMENTS.IMAGE(HIDE_IMAGE, 20, 20);

		/// LABELS//
		Label GENDER_LABEL = new Label(" Gender");
		GENDER_LABEL.setStyle("-fx-text-fill:#A9A9A9");
		GENDER_LABEL.setTranslateX(-37);

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

		TextField COMPANY_FIELD = new TextField();
		COMPANY_FIELD.setStyle(ELEMENTS.TF("150"));
		COMPANY_FIELD.setPromptText(" field");
		COMPANY_FIELD.setPrefWidth(50);
		COMPANY_FIELD.setTranslateX(20);

		TextField BDATE_TF = new TextField();
		BDATE_TF.setPromptText(" Birth Date");
		BDATE_TF.setStyle(ELEMENTS.TF("150"));

		DatePicker BDATE_DP = new DatePicker();
		BDATE_DP.setStyle(ELEMENTS.TF("150"));
		BDATE_DP.setVisible(false);

		ObservableList<String> GENDER_LIST = FXCollections.observableArrayList("Male", "Female");

		ComboBox GENDER_CB = new ComboBox(GENDER_LIST);
		GENDER_CB.setStyle(ELEMENTS.TF("150") + "-fx-text-fill: gray;");

		ToggleButton SHOW_PASS_BTN = new ToggleButton();
		SHOW_PASS_BTN.setStyle("-fx-background-color: transparent;");
		SHOW_PASS_BTN.setTranslateX(65);
		SHOW_PASS_BTN.setGraphic(SHOW);

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
		// BUTTONS_&_PROPERTIES//
		Button SIGNUP_BTN = new Button("Sign Up");
		SIGNUP_BTN.setTranslateX(240);

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

		// ====//
		SIGNUP_BTN.setOnAction(e -> {
			if (i == 1 && (FNAME_TF.getText().isEmpty() || LNAME_TF.getText().isEmpty()
					|| USERNAME_TF.getText().isEmpty() || PASSWORD_F.getText().isEmpty() || BDATE_TF.getText().isEmpty()
					|| GENDER_CB.getValue() == null || GENDER_CB.getValue().toString().isEmpty())) {

				INCOMPLETE_INFO.showAndWait();

			} else if (i == 2 && (USERNAME_TF.getText().isEmpty() || PASSWORD_F.getText().isEmpty()
					|| COMPANY_FIELD.getText().isEmpty())) {

				INCOMPLETE_INFO.showAndWait();

			} else if (ADMIN.Main_Info_Checker(USERNAME_TF.getText())
					|| ADMIN.Main_Info_Checker(PASSWORD_F.getText())) {

				ALREADY_USED.showAndWait();

			} else if (i == 1) {
				ADMIN.AddCustomer(FNAME_TF.getText(), LNAME_TF.getText(), USERNAME_TF.getText(), PASSWORD_F.getText(),
						BDATE_TF.getText(), GENDER_CB.getValue().toString());
			} else if (i == 2) {
				ADMIN.AddCompany(USERNAME_TF.getText(), PASSWORD_F.getText(), COMPANY_FIELD.getText());
			}

			FNAME_TF.setText(null);
			LNAME_TF.setText(null);
			USERNAME_TF.setText(null);
			PASSWORD_F.setText(null);
			BDATE_TF.setText(null);
			GENDER_CB.setValue(null);
			COMPANY_FIELD.setText(null);

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
		if (i == 1) {
			VBox RIGHT = new VBox(15, NAMES, USER_KEY_INFO, USER_EXTRA_INFO, SIGNUP_BTN);
			RIGHT.setStyle("-fx-background-color: transparent;" + "-fx-min-height: 400px;" + "-fx-max-height: 400px;"
					+ "-fx-min-width: 300px;" + "-fx-max-width: 300px;" + "-fx-background-radius: 0 20 20 0;");
			RIGHT.setTranslateX(110);
			RIGHT.setTranslateY(40);
			RIGHT.setPadding(new Insets(10));

			StackPane CUSTOMER = ACC_CREATION_PANE();
			CUSTOMER.getChildren().addAll(RIGHT);
			CUSTOMER.setPadding(new Insets(20));

			return CUSTOMER;

		} else if (i == 2) {
			VBox RIGHT_COMPANY = new VBox(15, USER_KEY_INFO, COMPANY_FIELD, SIGNUP_BTN);
			RIGHT_COMPANY
					.setStyle("-fx-background-color: transparent;" + "-fx-min-height: 400px;" + "-fx-max-height: 400px;"
							+ "-fx-min-width: 300px;" + "-fx-max-width: 300px;" + "-fx-background-radius: 0 20 20 0;");
			RIGHT_COMPANY.setTranslateX(110);
			RIGHT_COMPANY.setTranslateY(40);
			RIGHT_COMPANY.setPadding(new Insets(10));

			StackPane COMPANY = ACC_CREATION_PANE();
			COMPANY.getChildren().addAll(RIGHT_COMPANY);
			COMPANY.setPadding(new Insets(20));
			COMPANY.setStyle("-fx-background-radius: 20;" + "-fx-background-color: #005073;");

			return COMPANY;

		}
		return null;

	}

	public BorderPane PERSONALINFO_FORM(Company corp) {

		// buttons

		Button EDIT_BTN = new Button("Add");
		EDIT_BTN.setPrefSize(70, 70);
		EDIT_BTN.setStyle("-fx-background-color: #D3D3D3;" + "-fx-font-weight: bold;" + "-fx-background-radius: 10;");

		// Labels
		Label FNAME_LBL = new Label("Customer Username :");
		FNAME_LBL.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

		Label LNAME_LBL = new Label("Bill Charge :");
		LNAME_LBL.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

		Label USERNAME_LBL = new Label("Creation Date:");
		USERNAME_LBL.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

		Label ID_LBL = new Label("Due Date :");
		ID_LBL.setStyle("-fx-background-color: transparent; " + "-fx-font-weight: bold;");

		// Textfields

		TextField FNAME_TXT = new TextField();
		FNAME_TXT.setPromptText("username");
		FNAME_TXT.setStyle(ELEMENTS.TF("150"));
		FNAME_TXT.setTranslateY(-10);

		TextField LNAME_TXT = new TextField();
		LNAME_TXT.setPromptText("charge");
		LNAME_TXT.setStyle(ELEMENTS.TF("150"));
		LNAME_TXT.setTranslateY(-10);

		DatePicker BDATE_DP = new DatePicker();
		BDATE_DP.setStyle(ELEMENTS.TF("150"));
		BDATE_DP.setVisible(false);
		BDATE_DP.setTranslateY(-10);

		TextField BDATE_TF = new TextField();
		BDATE_TF.setPromptText("creation");
		BDATE_TF.setStyle(ELEMENTS.TF("150"));
		BDATE_TF.setTranslateY(-10);

		DatePicker BDATE_DP2 = new DatePicker();
		BDATE_DP2.setStyle(ELEMENTS.TF("150"));
		BDATE_DP2.setVisible(false);
		BDATE_DP2.setTranslateY(-10);

		TextField BDATE_TF2 = new TextField();
		BDATE_TF2.setPromptText("due");
		BDATE_TF2.setStyle(ELEMENTS.TF("150"));
		BDATE_TF2.setTranslateY(-10);

//		    DatePicker BDATE_DP = new DatePicker();
//		    BDATE_DP.setStyle(ELEMENTS.TF("150"));
//		    BDATE_DP.setTranslateY(-10);
//		    BDATE_DP.setVisible(false);
//		    ObservableList<String> GENDER_LIST = FXCollections.observableArrayList("Male", "Female");
//		    ComboBox GENDER_CB = new ComboBox(GENDER_LIST);
//		    GENDER_CB.setStyle(ELEMENTS.TF("150") + "-fx-text-fill: gray;");
//		    GENDER_CB.setEditable(true);
		// PANES
		HBox FNAME_HBOX = new HBox(FNAME_LBL, FNAME_TXT);

		HBox LNAME_HBOX = new HBox(LNAME_LBL, LNAME_TXT);

		StackPane BDATE_PANE = new StackPane(BDATE_DP, BDATE_TF);
		
		StackPane BDATE_PANE2 = new StackPane(BDATE_DP2, BDATE_TF2);

		HBox NAMES = new HBox(FNAME_HBOX, LNAME_HBOX);
		NAMES.setPadding(new Insets(20));
		NAMES.setSpacing(30);

		HBox USERNAME_HBOX = new HBox(USERNAME_LBL, BDATE_PANE);

		HBox ID_HBOX = new HBox(ID_LBL, BDATE_PANE2);

		HBox USERNAME_ID = new HBox(USERNAME_HBOX, ID_HBOX);
		USERNAME_ID.setPadding(new Insets(20));
		USERNAME_ID.setSpacing(30);

		VBox PERSON_INFO = new VBox(NAMES, USERNAME_ID);
		PERSON_INFO.setAlignment(Pos.TOP_CENTER);
		PERSON_INFO.setPadding(new Insets(20));

		HBox EDIT_BTN_PANE = new HBox(EDIT_BTN);
		EDIT_BTN_PANE.setPadding(new Insets(20));
		EDIT_BTN_PANE.setAlignment(Pos.BOTTOM_RIGHT);

		BorderPane USER_INFO_MAIN_PANE = new BorderPane();
		USER_INFO_MAIN_PANE.setCenter(PERSON_INFO);
		USER_INFO_MAIN_PANE.setRight(EDIT_BTN_PANE);

		// ACTION HANDLING
		EDIT_BTN.setOnAction(e -> {
			corp.setUserName(corp.getUserName());
		    corp.addBill(FNAME_TXT.getText(), Integer.parseInt(LNAME_TXT.getText()), BDATE_DP.getEditor().getText(), BDATE_DP2.getEditor().getText());
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
		
		BDATE_DP2.setOnAction(event -> {
			BDATE_TF2.setText(BDATE_DP2.getEditor().getText());
			BDATE_DP2.setVisible(false);
			BDATE_TF2.setVisible(true);
		});

		BDATE_TF2.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				BDATE_DP2.setVisible(true);
				BDATE_TF2.setVisible(false);
			}

		});

		return USER_INFO_MAIN_PANE;
		// BDATE_DP.setOnAction(event -> {
//					BDATE_TXT.setText(BDATE_DP.getEditor().getText());
//					BDATE_DP.setVisible(false);
//					BDATE_TXT.setVisible(true);
//				});
		//
		// BDATE_TXT.setOnMouseClicked(e -> {
//					if (e.getButton() == MouseButton.PRIMARY) {
//						BDATE_DP.setVisible(true);
//						BDATE_TXT.setVisible(false);
//					}
		//
//				});
		// GENDER_CB.getSelectionModel().selectedItemProperty().addListener((observable,
		// oldValue, newValue) -> {
//					if (newValue != null) {
//						GENDER_LBL.setVisible(false);
//					} else {
//						GENDER_LBL.setVisible(true);
//					}
//				});

	}

}
