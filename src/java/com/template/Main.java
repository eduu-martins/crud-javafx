package com.template;

import com.template.controller.MainController;
import com.template.validator.BolaDeOuroValidator;
import com.template.validator.IBolaDeOuroValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        IBolaDeOuroValidator bolaDeOuroValidator = new BolaDeOuroValidator();

        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main.fxml"));
        loader.setControllerFactory(controllerClass );
        if(controllerClass == MainController.class){
        return new MainController(BolaDeOuroValidator);
        }
        try{
            return controllerClass.newInstance();
        } catch (Exception e){
            throw new RuntimeException(e);
        }


        Scene scene = new Scene(loader.load(), 1060, 702);

        stage.setTitle("AtividadeMVC - Vencedores Bola de Ouro");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}