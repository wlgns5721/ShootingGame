import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

class PlaySoundThread extends Thread{
	game Game;
	String AudioFile;
	PlaySoundThread(String _AudioFile) {
		AudioFile = _AudioFile;
	}
	
	public void run() {
		try {
			final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
			clip.open(AudioSystem.getAudioInputStream(new File(AudioFile)));
			clip.addLineListener(new LineListener() {
			@Override
			public void update(LineEvent event) {
				if(event.getType()==LineEvent.Type.STOP) {
					clip.close();
				}
			}
			});
			clip.start();	
		}
		
		catch (Exception ex) {
			
		}
	}
}

class Sound {
	public String name;
	public Clip clip;
	
	Sound(String sound_name, Clip _clip) {
		name = sound_name;
		clip = _clip;
	}
}