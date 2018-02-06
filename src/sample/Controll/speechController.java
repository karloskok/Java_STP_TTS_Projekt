package sample.Controll;

import com.darkprograms.speech.synthesiser.SynthesiserV2;
import com.darkprograms.speech.translator.GoogleTranslate;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class speechController implements Initializable
{

    public AnchorPane pan_user;
    public TextArea ta_speak;
    public ComboBox cb_languages;
    public Label user;
    public Label lab_translate;
    public Label lab_enter_text;
    public Button btn_speak;
    public Button btn_clear;
    public Label lab_main;

    SynthesiserV2 synthesizer=new SynthesiserV2("AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw");;
    final double HEIGHT=-444f;

    public void initialize(URL location, ResourceBundle resources)
    {

    }




    public void comboAction(ActionEvent event) throws IOException {

        System.out.println(cb_languages.getValue());
        String targetLanguage = new String();

        switch (cb_languages.getValue().toString())
        {
            case "CRO": targetLanguage="hr";
                break;
            case "ENG": targetLanguage="en";
                break;
            case "ESP": targetLanguage="es";
                break;
            case "FRA": targetLanguage="fr";
                break;
            case "CZE": targetLanguage="cs";
                break;
        }
        ta_speak.setText(GoogleTranslate.translate(targetLanguage, ta_speak.getText()));

    }

    public  void  ClearText() {

        ta_speak.setText("");
    }

    public void Speak(MouseEvent event)
    {
        System.out.println(ta_speak.getText());

        Thread thread = new Thread(() -> {

            try {
                AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(ta_speak.getText()));
                player.play();

            } catch (JavaLayerException | IOException e) {
                e.printStackTrace();
            }

            System.out.println("Successfully");

        });
        thread.setDaemon(false);
        thread.start();
    }

}
