package com.programacion.cuatro;

import com.programacion.cuatro.Controllers.HomeController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class ProgramacionFourApplication extends Application {
    private ConfigurableApplicationContext applicationContext;



    @Override
    public void init() {

        applicationContext = new SpringApplicationBuilder(MainApplication.class).run();
    }

    @Override
    public void start(Stage stage) {

        applicationContext.publishEvent(new StageReadyEvent(stage,HomeController.class));
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    public static class StageReadyEvent extends ApplicationEvent {

        Object viewController;

        public  StageReadyEvent(String stage){
            super(stage);
        }

        public StageReadyEvent(Stage stage, Object viewController) {
            super(stage);
            this.viewController = viewController;
        }

        public Object getViewController(){
            return viewController;
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
    }

    @Bean
    public static FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
        // Would also work with javafx-weaver-core only:
        // return new FxWeaver(applicationContext::getBean, applicationContext::close);
        return new SpringFxWeaver(applicationContext);
    }

}
