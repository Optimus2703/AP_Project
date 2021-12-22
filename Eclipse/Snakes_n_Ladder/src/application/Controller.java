package application;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller {
	@FXML
	private Text greenwon;
	@FXML
	private Text bluewon;
	@FXML
	private ImageView winpopup;
	@FXML
	public Button replay;


	@FXML
	private Button tempbutton;
	@FXML
	private ImageView green;
	@FXML
	private ImageView blue;
	@FXML
	private ImageView diceview;

	@FXML
	void nul() {
		System.out.println("hI");
	}

	int num = 0;
	int in = 0;
	int in_p1 = 0;
	int in_p2 = 0;
	int gg = 0;
	int player_turn = 1;
	int p1_pos = 1;
	int p2_pos = 1;
	int yaxis_counter = 1;
	int yaxis_counter2 = 1;
	int limit = 11;
	private Stage welcom;
	private Scene login;
	private Parent root;

	@FXML
	void gamePlay() {


		if (p1_pos >= 100) {
			//---------

			//---------
			return;
		}
		if (p2_pos >= 100) {
			//--------

			//---------
			return;
		}

		if (player_turn % 2 == 0) {    // for p1
			System.out.println("green turn");
			try {
				dice();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (in_p1 == 0 && num == 1) {
				inboard(); //green gioti for p1
				in_p1++;
			} else if (in_p1 != 0) {
				if (num == 6) player_turn--;

				if (p1_pos + num > 100) return;
				tempbutton.setDisable(true);
				//-------------------------------
				Thread goti_thread = new Thread() {

					public void run() {
						//if (p1_pos+num >100) return;
						try {
							if ((num + p1_pos) == 100) {
								System.out.println("p1 win");
								show_winpage(2);

							}
							for (int i = 0; i < num; i++) {

								if (p1_pos == 100) {
									return;

								}
								p1_pos++;
								if (p1_pos % 10 == 1) {
									yUp_green();
									yaxis_counter++;
									Thread.sleep(500);

									continue;
								}
								if (yaxis_counter % 2 != 0) {
									skiph();
								} else if (yaxis_counter % 2 == 0) {
									nskiph();
								}

								Thread.sleep(500);

								//System.out.println(p1_pos);


							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						tempbutton.setDisable(false);
					}
				};
				goti_thread.start();


				//-------------------------------
			}


		} else {     // for p2
			System.out.println("blue turn");
			try {
				dice();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (in_p2 == 0 && num == 1) {
				bboard(); //green gioti for p1
				in_p2++;
			} else if (in_p2 != 0) {
				//-------------------------------
				if (num == 6) player_turn--;
				;
				if (p1_pos + num > 100) return;
				tempbutton.setDisable(true);
				Thread goti_thread2 = new Thread() {

					public void run() {
						try {

							for (int i = 0; i < num; i++) {
								if (p2_pos == 100) {
									return;

								}
								p2_pos++;
								//System.out.println(p2_pos);
								if (p2_pos % 10 == 1) {
									//System.out.println("i am here");
									yUp_blue();
									yaxis_counter2++;
									Thread.sleep(500);

									continue;
								}
								if ((yaxis_counter2) % 2 != 0) {
									skiph_blue();
								} else if (yaxis_counter2 % 2 == 0) {
									nskiph_blue();
								}

								Thread.sleep(500);

								//System.out.println(p2_pos);


							}
						} catch (Exception e) {
							e.printStackTrace();
						}
						tempbutton.setDisable(false);
					}
				};
				goti_thread2.start();


				//-------------------------------
			}
		}

		player_turn++;

	}
	void show_winpage(int i){
		winpopup.setOpacity(1);
		if (i ==0 ){
			bluewon.setOpacity(1);
			return;
		}
		greenwon.setOpacity(1);
	}
	@FXML
	void dice_run() {

		try {
			dice();

			tempbutton.setDisable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (in == 0) inboard();
		in++;
		System.out.println("--" + num);
		Thread goti_thread = new Thread() {

			public void run() {
				try {
					for (int i = 0; i < num; i++) {
						//System.out.println(num );
						skiph();
						Thread.sleep(500);

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				tempbutton.setDisable(false);
			}

		};
		goti_thread.start();


	}


	@FXML
	void dice() throws InterruptedException {
		Random ran = new Random();
		num = ran.nextInt(6) + 1;
		File f = new File("src/application/dice" + num + ".png");
		diceview.setImage(new Image(f.toURI().toString()));

		//System.out.println("--"+num+"--");
	}

	//--------------------------------------------
	@FXML
	void inboard() { // for green goti

		TranslateTransition move = new TranslateTransition();
		move.setNode(green);
		move.setByX(29);
		move.setByY(-41);
		move.play();

	}

	@FXML
	void bboard() { // for blue gotiya
		TranslateTransition mov = new TranslateTransition();
		mov.setNode(blue);
		mov.setByY(-39);
		mov.play();

	}

	@FXML
	void skiph() { // for moving green horizontally
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(green);
		giti.setByX(34);
		giti.play();
	}

	void skiph_blue() {
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(blue);
		giti.setByX(34);
		giti.play();


	}

	@FXML
	void nskiph() {
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(green);
		giti.setByX(-34);
		giti.play();


	}

	void nskiph_blue() {
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(blue);
		giti.setByX(-34);
		giti.play();


	}

	@FXML
	void yUp_green() {
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(green);
		giti.setByY(-45);
		giti.play();

	}

	void yUp_blue() {
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(blue);
		giti.setByY(-45);
		giti.play();

	}

	void yDown_green() {
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(green);
		giti.setByY(45);
		giti.play();

	}

	@FXML
	public void play(ActionEvent p) throws IOException {
		root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("start.fxml")));
		welcom = (Stage) ((Node) p.getSource()).getScene().getWindow();
		login = new Scene(root);
		welcom.setScene(login);
		welcom.show();


	}

	@FXML
	public void replay(ActionEvent p) throws IOException {
		root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
		welcom = (Stage) ((Node) p.getSource()).getScene().getWindow();
		login = new Scene(root);
		welcom.setScene(login);
		welcom.show();
	}
}
