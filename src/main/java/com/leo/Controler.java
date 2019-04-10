package com.leo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;


public class Controler extends Main {

    public Button btnUno, btnSalir, btnReset;
    public Label lblUno;
    public TextField txtLetra;
    public Line BI,BD,PI,PD,Torso;
    public Pane dedPane, livPane, pnWin;
    public TextArea txtLetras;

    private char[] word;
    private String guess;
    private int body = 0;
    private boolean flag = true;
    private final ArrayList<String> letras = new ArrayList<>();

 @FXML
    private void initialize(){
        //selecciona una palabra aleatoria del enum(Palabras)
        Random random = new Random();
        Palabras randomX = Palabras.values()[random.nextInt(Palabras.values().length)];

        guess = randomX.toString();
        lblUno.setText(randomX.toString());
        lblUno.setText(lblUno.getText().replaceAll("[a-zA-Z]","_")); //convierte la palabra en *incognita*
        word = new char[(int) randomX.toString().chars().count()];//inicializa el char deacuerdo a la cantidad de letras de la palabra

        //string to char
        for (int i=0; i < lblUno.getText().chars().count(); i++)
            word[i] = lblUno.getText().charAt(i);

    }
    //evento del boton "Reset"
    public void onReset() {
        body = 0;
        dedPane.setVisible(false);
        livPane.setVisible(true);
        btnUno.setDisable(false);
        txtLetra.setDisable(false);
        btnUno.setDisable(false);
        txtLetra.setDisable(false);
        pnWin.setVisible(false);
        PI.setVisible(true);
        PD.setVisible(true);
        BI.setVisible(true);
        BD.setVisible(true);
        Torso.setVisible(true);
        txtLetras.setText("");

        initialize();
    }
    //evento del boton "Salir"
    public void onExit() {
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }
    //evento del boton "try"
    public void onClick(){

        //por si el textFiel esta en blanco
        if (txtLetra.getText().equals(""))
            return;

        char letra = txtLetra.getText().toLowerCase().charAt(0); //obtiene la letra (en minuscula) del textField
        //muestra las letras o palabras usadas hasta el momento
        letras.add(txtLetra.getText());
        txtLetras.setText(letras.toString());

        //Por si adivina la palabra completa
        if (txtLetra.getText().chars().count()!=1) {
            if (txtLetra.getText().toLowerCase().equals(guess)) {
                lblUno.setText(guess);
                win();
            } else {
                dead();
            }
            return; //return para que no se utilize la primer letra si se ingresa una palabra
        }

        //si adivina la letra, esta es agregada al array de characters
        for (int i=0; i < lblUno.getText().chars().count(); i++) {
            if (guess.charAt(i) == letra) {
                word[i] = letra;
                flag = false;
            }
        }
        //si no adivinó una letra flag = true y llama a dead()
        if (flag)
            dead();
        else
            flag = true;

        //mostrar el progreso de la palabra
        if (body!=6)
            lblUno.setText(String.copyValueOf(word));

        //dicta que se ganó el juego
        if (lblUno.getText().equals(guess) && body<6)
            win();

        //limpia y da focus al textField
        txtLetra.clear();
        txtLetra.requestFocus();

    }

    private void dead() {
        body++;

        switch (body) {
            case 1:
                PI.setVisible(false);
                break;
            case 2:
                PD.setVisible(false);
                break;
            case 3:
                BI.setVisible(false);
                break;
            case 4:
                BD.setVisible(false);
                break;
            case 5:
                Torso.setVisible(false);
                break;
            case 6:
                perdio();
                break;
            default:
                break;
        }
    }

    private void perdio() {
        dedPane.setVisible(true);
        livPane.setVisible(false);
        lblUno.setText(guess);
        btnUno.setDisable(true);
        txtLetra.setDisable(true);
        fin("perdio");
    }

    private void win() {
        btnUno.setDisable(true);
        txtLetra.setDisable(true);
        pnWin.setVisible(true);
        fin("win");
    }
}
