package TicTacFX;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;

//This is a class for managing sound effect.
//AudioClip would work just fine in the main class.
//However, does to a quirk with the way MediaPlayer works, MediaPlayer must be a property or field.
//This is by design, but the means it cannot be created as a 'local' object.
//Instead of creating a field or global variable in the main class, I decided to just create a sound manager.

//AudioClip vs MediaPlayer
//AudioClips have less features than MediaPlayer objects
//Also, with AudioClip objects, the entire file is loaded into memory.
//For that reason AudioClips should only be used for short sounds.
//For things like music, MediaPlayer objects should be used.

//For audio files, the "root" folder is the project folder.
//That is one folder above the src folder.
//This is an interesting contrast to images and graphics where the root is the src folder.

public class SoundManager {

    //This is basically the way to setup an AudioClip and MediaPlayer object.
    private final MediaPlayer music =  new MediaPlayer(new Media(Paths.get("src/sounds/piano.mp3").toUri().toString()));
    private final AudioClip tweet = new AudioClip(Paths.get("src/sounds/tweet.mp3").toUri().toString());
    private final AudioClip hop = new AudioClip(Paths.get("src/sounds/hop.mp3").toUri().toString());
    boolean mute = false;


    //These are basic method to play the various sounds.
    public void startMusic()
    {
        music.play();
    }

    public void muteMusic() {
        if(mute){
            music.setMute(false);
            mute = false;
        }
        else{
            music.setMute(true);
            mute = true;
        }
    }

    public void hopSound()
    {
        hop.play();
    }

    public void tweetSound()
    {
        tweet.play();
    }

}
