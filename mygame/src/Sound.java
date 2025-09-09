import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

class PlaySoundThread extends Thread{
	Game game;
	String audioFile;
	PlaySoundThread(String _AudioFile) {
		audioFile = _AudioFile;
	}
	
	public void run() {
		try {
			final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
			clip.open(AudioSystem.getAudioInputStream(new File(audioFile)));
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
	
	Sound(String soundName, Clip _clip) {
		name = soundName;
		clip = _clip;
	}
}