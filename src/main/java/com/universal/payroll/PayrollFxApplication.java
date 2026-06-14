package com.universal.payroll;

import com.universal.payroll.config.FxmlView;
import com.universal.payroll.config.StageManager;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class PayrollFxApplication extends Application{

    private static Stage stage;

    private ConfigurableApplicationContext appContext;
    private StageManager stageManager;

    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;
        stageManager = appContext.getBean(StageManager.class, primaryStage);
        showLoginScene();
    }

    @Override
    public void init(){
        appContext = new SpringApplicationBuilder(PayrollApplication.class).run();
    }

    @Override
    public void stop(){
        appContext.close();
        stage.close();
    }

    private void showLoginScene(){
        stageManager.switchScene(FxmlView.LOGIN);
    }
}
