package myshop;

import javafx.stage.Stage;

public class StageManager {
    
    private static StageManager instance;
    private static Stage primaryStage;
  
   
    private StageManager() {
    }
  
   
    public synchronized static StageManager getInstance() 
    {
        if (instance == null) 
        {
            instance = new StageManager();
        }
        return instance;
    }
 
   public Stage getPrimaryStage() {
      return primaryStage;
   }
 
   public void setPrimaryStage(Stage primaryStage) {
      StageManager.primaryStage = primaryStage;
   }  
}