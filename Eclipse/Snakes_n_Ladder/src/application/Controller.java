package application;
import java.io.File;
import java.util.*;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Controller {
	@FXML
	private Button tempbutton ;
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
	int in=0;
	int in_p1=0;
	int in_p2 =0;
	int gg =0;
	int player_turn=1;
	int p1_pos =1;
	int p2_pos =1;
	int yaxis_counter = 1;
	int limit=11;
	
	@FXML
	void gamePlay()  {
			
		
		
			if (player_turn % 2 == 0 ) {	// for p1
				System.out.println("green turn");
				try{dice();}
				catch(Exception e) {e.printStackTrace();}
				
				if ( in_p1 == 0 && num ==1) {
					inboard(); //green gioti for p1
					in_p1++; 
				}
				else if (in_p1!=0){
					tempbutton.setDisable(true);
					//-------------------------------
					Thread goti_thread = new Thread(){
						
						public void run() {
							try {
								for ( int i  =0 ; i <num ; i++) {
									//System.out.println(num );
									
									/*
									 * p1_pos ++;
									 * 
									 * if ( yaxis_counter %2 != 0) {skiph();} if (p1_pos == 11 || p1_pos == 20 ||
									 * p1_pos == 30 || p1_pos == 40 || p1_pos == 50 || p1_pos == 60 || p1_pos == 70
									 * || p1_pos == 80 || p1_pos == 90 || p1_pos == 100 ) { //Thread.sleep(500);
									 * //Thread.sleep(500); yUp_green(); yaxis_counter++; continue; } if (
									 * yaxis_counter %2 == 0) {nskiph();}
									 * 
									 * System.out.println(p1_pos);
									 * 
									 * 
									 * Thread.sleep(500);
									 */
									
									p1_pos++;
									if (p1_pos%10 ==1) {
										yUp_green();
										yaxis_counter++;
										limit+=10;
									}
									if (yaxis_counter%2 != 0 &&p1_pos<=10) skiph();
									
									if (yaxis_counter%2 == 0 && p1_pos>limit) nskiph();
									Thread.sleep(500);
									
									System.out.println(p1_pos);
									
									
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							tempbutton.setDisable(false);
						}
					};
					goti_thread.start();
					if (p1_pos % 10 == 0 ) {
						
					}
			
					//-------------------------------
				}
				
				
			}
			else {     // for p2
				System.out.println("blue turn");
				try{dice();}
				catch(Exception e) {e.printStackTrace();}
				
				if ( in_p2 == 0 && num ==1) {
					bboard(); //green gioti for p1
					in_p2++;
				}
				else if (in_p2 !=0) {
					//-------------------------------
					Thread goti_thread = new Thread(){
						
						public void run() {
							try {
								for ( int i  =0 ; i <num ; i++) {
									//System.out.println(num );
									skiph_blue();
									
									
									Thread.sleep(500);
									 
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
			}
			
			player_turn++;
			
	}
	
	@FXML
	void dice_run() {
		
		try {
			dice();
			
			tempbutton.setDisable(true);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		if ( in == 0) inboard();
		in++;
		System.out.println("--"+num);
		Thread goti_thread = new Thread(){
			
			public void run() {
				try {
					for ( int i  =0 ; i <num ; i++) {
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
		File f = new File("src/application/dice"+num+".png");
		diceview.setImage(new Image(f.toURI().toString()));
		
		//System.out.println("--"+num+"--");
	}

	//--------------------------------------------
	@FXML
	void inboard(){ // for green goti

		TranslateTransition move = new TranslateTransition();
		move.setNode(green);
		move.setByX(29);
		move.setByY(-41);
		move.play();

	}
	@FXML
	void bboard(){ // for blue gotiya
		TranslateTransition mov = new TranslateTransition();
		mov.setNode(blue);
		mov.setByY(-39);
		mov.play();

	}
	@FXML
	void skiph(){ // for moving green horizontally
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(green);
		giti.setByX(34);
		giti.play();
		}
	void skiph_blue(){
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(blue);
		giti.setByX(34);
		giti.play();


	}
	@FXML
	void nskiph(){
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(green);
		giti.setByX(-34);
		giti.play();


	}
	@FXML
	void yUp_green(){
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(green);
		giti.setByY(-45);
		giti.play();

	}
	
	void yDown_green(){
		TranslateTransition giti = new TranslateTransition();
		giti.setNode(green);
		giti.setByY(45);
		giti.play();

	}


}
